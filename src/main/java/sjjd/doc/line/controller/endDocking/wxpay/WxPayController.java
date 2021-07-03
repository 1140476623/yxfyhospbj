package sjjd.doc.line.controller.endDocking.wxpay;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sjjd.doc.line.config.wxpay.WxPayConfig;
import sjjd.doc.line.config.wxpay.WxPayConstants;
import sjjd.doc.line.config.wxpay.WxPayUtils;
import sjjd.doc.line.pojo.PayInfo;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IPayInfoService;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xyw
 * @version 1.0
 * @description 微信扫码支付接口
 * @date 2020/07/29
 */
@RestController
@RequestMapping("/selfHelp/wxPay")
@Slf4j
@Api(value = "微信支付接口", tags = "微信支付接口")
public class WxPayController {


    @Resource
    private IPayInfoService payInfoService;

    @Value("${pickPath}")
    private String pickPath;


    @RequestMapping("/registerPay")
    @ApiOperation(value = "扫码支付-预下单(用户主扫)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fee", value = "费用", dataType = "String"),
            @ApiImplicitParam(name = "patientId", value = "身份证", dataType = "String"),
            @ApiImplicitParam(name = "patientName", value = "病人姓名", dataType = "String"),
            @ApiImplicitParam(name = "subject", value = "费用类别(挂号，缴费)", dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "科室名", dataType = "String"),
            @ApiImplicitParam(name = "terminalIp", value = "终端IP", dataType = "String"),
            @ApiImplicitParam(name = "terminalNo", value = "终端编号", dataType = "String"),
            @ApiImplicitParam(name = "doctorName", value = "医生名", dataType = "String")
    })
    public ResponseResult<RespNativePay> registerPay(String fee, String patientId, String subject,
                                                     String patientName, String deptName, String terminalIp,
                                                     String doctorName, String terminalNo) {
        ResponseResult<RespNativePay> result = new ResponseResult<>();

        String outTradeNo = WxPayUtils.mchOrderNo();   //订单号（唯一）
        PayInfo payInfo = new PayInfo();
        payInfo.setResidentName(patientName);
        payInfo.setResidentId(patientId);
        payInfo.setOutTradeNo(outTradeNo);
        payInfo.setFacilityCode(terminalNo);
        payInfo.setClintIp(terminalIp);
        payInfo.setPayStatus(PayInfo.STATUS_PAYING_APPLY);
        payInfo.setTotalFee(new BigDecimal(fee).multiply(new BigDecimal(100))
                .setScale(0, BigDecimal.ROUND_HALF_UP));   //单位 为分
        payInfo.setSubject(subject);
        payInfo.setDocName(doctorName);
        payInfo.setDeptName(deptName);
        payInfo.setType(subject);
        payInfo.setCreateTime(LocalDateTime.now());

        String payUrl = payInfoService.wxPayUrl(payInfo);    //生产二维码字符串
        if (StringUtils.isEmpty(payUrl)) {
            result.setState(0);
            result.setMessage("生成二维码失败");
            log.info("\n订单号：{}生成二维码失败", outTradeNo);
            return result;
        }

        //微信是以分为单位,重新换算成元保存到数据库
        payInfo.setTotalFee(new BigDecimal(fee));
        //生产二维码成功后把待缴费订单保存到数据库
        payInfoService.save(payInfo);

        RespNativePay openNativePay = new RespNativePay();
        openNativePay.setCodeUrl(payUrl);
        openNativePay.setOutTradeNo(outTradeNo);

        result.setData(openNativePay);
        result.setState(200);
        result.setMessage("生成二维码成功");
        log.info("\n订单号：{}生成二维码成功", outTradeNo);
        return result;
    }

    @ApiOperation(value = "轮询查询订单支付情况", httpMethod = "POST")
    @ApiImplicitParam(name = "outTradeNo", value = "订单号", dataType = "String")
    @RequestMapping("/orderQuery")
    public ResponseResult<Void> orderQuery(String outTradeNo) {
        ResponseResult<Void> result = new ResponseResult<>();
        if (payInfoService.wxOrderQuery(outTradeNo)) {
            log.info("订单:{}还未支付成功", outTradeNo);
            result.setState(0);
            result.setMessage("订单还未支付成功");
            return result;
        }
        PayInfo payInfo = new PayInfo();
        payInfo.setPayType("1");
        payInfo.setPayStatus(PayInfo.STATUS_PAYING_SUCCESS);
        payInfo.setUpdateTime(LocalDateTime.now());
        payInfoService.update(payInfo,
                new QueryWrapper<PayInfo>().eq("out_trade_no", outTradeNo));

        log.info("订单:{}支付成功", outTradeNo);
        result.setState(200);
        result.setMessage("订单支付成功");
        return result;
    }


    @ApiOperation(value = "退款", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fee", value = "费用(元)", dataType = "String"),
            @ApiImplicitParam(name = "reason", value = "退款原因", dataType = "String"),
            @ApiImplicitParam(name = "outTradeNo", value = "订单号", dataType = "String")
    })
    @RequestMapping("/openRefund")
    @ApiIgnore
    public ResponseResult<JSONObject> openRefund(String fee, String reason, String outTradeNo) {
        ResponseResult<JSONObject> result = new ResponseResult<JSONObject>();
        //封装退款参数
        HashMap<String, String> data = new HashMap<>();
        //公众账号ID
        data.put("appid", WxPayConfig.appID);
        //商户号
        data.put("mch_id", WxPayConfig.mchID);
        //随机字符串
        data.put("nonce_str", WxPayUtils.getNonceStr());
        //商户订单号
        data.put("out_trade_no", outTradeNo);
        //商户退款单号
        data.put("out_refund_no", outTradeNo);
        //订单金额
        data.put("total_fee", new BigDecimal(fee).multiply(new BigDecimal(100))
                .setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        //退款金额
        data.put("refund_fee", new BigDecimal(fee).multiply(new BigDecimal(100))
                .setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        //退款原因
        data.put("refund_desc", reason);
        //签名类型
        data.put("sign_type", WxPayConstants.SING_MD5);
        //签名 签名中加入key
        StringEntity se = null;
        try {
            data.put("sign", WxPayUtils.getSignature(
                    data, WxPayConfig.key, WxPayConstants.SING_MD5));
            String requestXML = WxPayUtils.mapToXml(data);
            se = new StringEntity(requestXML, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            // JAVA使用证书文件
            log.info("\n加载证书===开始");
            // 指定读取证书格式为PKCS12
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            File file = new File(pickPath + "apiclient_cert.p12");
            FileInputStream instream = new FileInputStream(file);
            log.info("\ninstream" + instream);
            try {
                // 指定PKCS12的密码(商户ID)
                keyStore.load(instream, WxPayConfig.mchID.toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                instream.close();
            }
            log.info("\n加载证书===结束");
            //ssl双向验证发送http请求报文
            log.info("\nSSL验证报文===开始");
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WxPayConfig.mchID.toCharArray()).build();
            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslSF).build();
            HttpPost httppost = new HttpPost(WxPayConstants.PAY_ORDER_REFUND);
            httppost.setEntity(se);
            //定义响应实例对象
            CloseableHttpResponse responseEntry;
            // 读入响应流中字符串的引用
            String xmlStr2;
            responseEntry = httpclient.execute(httppost); // 发送请求
            HttpEntity entity = responseEntry.getEntity();// 获得响应实例对象
            // 读取响应流的内容
            if (entity != null) {
                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(
                        entity.getContent(), WxPayConstants.DEFAULT_CHARSET));
                while ((xmlStr2 = bufferedReader.readLine()) != null) {
                    sb2.append(xmlStr2);
                }
            }
            log.info("\nSSL验证报文===结束");
            Map<String, String> map = WxPayUtils.processResponseXml(
                    sb2.toString(), WxPayConstants.SING_MD5);
            log.info("\n申请退款接口返回的结果集====>" + map);
            //return_code为微信返回的状态码，SUCCESS表示申请退款成功，
            // return_msg 如非空，为错误原因 签名失败 参数格式校验错误
            String returnCode = map.get("return_code");
            if (WxPayConstants.SUCCESS.equalsIgnoreCase(returnCode)) {
                //return_code为SUCCESS的时候,才有以下返回值
                String resultCode = map.get("result_code");
                if (WxPayConstants.SUCCESS.equalsIgnoreCase(resultCode)) {
                    // 修改订单状态为申请退款
                    //根据支付结果修改数据库订单状态
                    PayInfo order = new PayInfo();
                    //更改支付状态为已退款
                    order.setPayStatus(PayInfo.STATUS_REFUND);
                    //退款原因
                    order.setReason(reason);
                    order.setUpdateTime(LocalDateTime.now());
                    UpdateWrapper<PayInfo> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("out_trade_no", outTradeNo);
                    //更新数据库订单
                    payInfoService.update(order, updateWrapper);
                    log.info("\n退款申请成功");
                    result.setState(200);
                    result.setMessage("退款申请成功");
                    return result;
                } else {
                    String errCodeDes = map.get("err_code_des");
                    log.info("\n退款申请失败：{}", errCodeDes);
                    // 失败处理
                    result.setState(0);
                    result.setMessage("退款申请失败！, " + errCodeDes);
                    return result;
                }
            } else {
                String returnMsg = map.get("return_msg");
                log.info("\n退款申请失败：{}", returnMsg);
                // 失败处理
                result.setState(0);
                result.setMessage("退款申请失败:" + returnMsg);
                return result;
            }
        } catch (Exception e) {
            log.info("\n退款申请失败(系统异常)");
            result.setState(0);
            result.setMessage("退款申请失败(系统异常)");
            return result;
        }
    }

}
