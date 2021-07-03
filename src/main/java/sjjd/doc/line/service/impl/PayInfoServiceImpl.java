package sjjd.doc.line.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sjjd.doc.line.config.wxpay.HttpsClient;
import sjjd.doc.line.config.wxpay.WxPayConfig;
import sjjd.doc.line.config.wxpay.WxPayConstants;
import sjjd.doc.line.config.wxpay.WxPayUtils;
import sjjd.doc.line.mapper.PayInfoMapper;
import sjjd.doc.line.pojo.PayInfo;
import sjjd.doc.line.service.IPayInfoService;
import sjjd.doc.line.util.Bease64ToImage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-18
 */
@Service
@Slf4j
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {

    /**
     * 生成 二维码字符串
     *
     * @param order
     * @return
     */
    public String wxPayUrl(PayInfo order) {
        HashMap<String, String> data = new HashMap<>();
        String outTradeNo = order.getOutTradeNo();
        String signType = WxPayConstants.SING_MD5;
        try {
            //公众账号ID
            data.put("appid", WxPayConfig.appID);
            //商户号
            data.put("mch_id", WxPayConfig.mchID);
            //随机字符串
            data.put("nonce_str", WxPayUtils.getNonceStr());
            //商品描述
            data.put("body", order.getSubject());
            //交易订单号
            data.put("out_trade_no", order.getOutTradeNo());
            //标价币种
            data.put("fee_type", "CNY");
            //标价金额
            data.put("total_fee", String.valueOf(order.getTotalFee()));
            //用户的IP
            data.put("spbill_create_ip", order.getClintIp());
            //支付过后的回调通知地址
            data.put("notify_url", WxPayConfig.notifyUrl);
            //交易类型
            data.put("trade_type", WxPayConstants.TRADE_TYPE);
            //签名类型
            data.put("sign_type", signType);
            //二维码过期时间  --3分钟
            data.put("time_expire", getOrderExpireTime(3 * 60 * 1000L));
            //商品id
            data.put("product_id", order.getFacilityCode());
            //签名 签名中加入key
            data.put("sign", WxPayUtils.getSignature(data, WxPayConfig.key, signType));

            String requestXML = WxPayUtils.mapToXml(data);
            String responseString = HttpsClient.httpsRequestReturnString(WxPayConstants.PAY_UNIFIED_ORDER,
                    HttpsClient.METHOD_POST, requestXML);
            //解析返回的xml
            Map<String, String> resultMap = WxPayUtils.processResponseXml(responseString, signType);
            log.info("返回map值是：{}", resultMap);
            String returnCode = resultMap.get(WxPayConstants.RETURN_CODE);
            if (WxPayConstants.SUCCESS.equals(returnCode)) {
                String resultCode = resultMap.get(WxPayConstants.RESULT_CODE);
                if (WxPayConstants.SUCCESS.equals(resultCode)) {
                    //根据链接生成base64位的二维码
                    try {
                        String codeUrl = resultMap.get("code_url");
                        log.info("\n订单号：{}生成二维码成功", outTradeNo);
                        return Bease64ToImage.barcode2Base64(codeUrl);
                    } catch (Exception e) {
                        log.info(e.toString());
                        log.info("\n订单号：{}生成二维码失败(base64转码失败)", outTradeNo);
                    }
                } else {
                    String errCodeDes = resultMap.get("err_code_des");
                    log.info("\n订单号：{}生成二维码失败，{}", outTradeNo, errCodeDes);
                }
            } else {
                String returnMsg = resultMap.get("return_msg");
                log.info("\n(订单号：{}生成微信支付码(通信)失败:{}", outTradeNo, returnMsg);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            log.error("\n订单号：{}生成微信支付码失败(系统异常))", order.getOutTradeNo(), e);
        }
        return null;
    }


    /**
     * 微信查询方法
     *
     * @param orderNo
     * @return
     * @throws Exception
     */
    public boolean wxOrderQuery(String orderNo) {
        String signType = WxPayConstants.SING_MD5;
        HashMap<String, String> data = new HashMap<String, String>();
        //公众账号ID
        data.put("appid", WxPayConfig.appID);
        //商户号
        data.put("mch_id", WxPayConfig.mchID);
        //随机字符串
        data.put("nonce_str", WxPayUtils.getNonceStr());
        //商户订单号
        data.put("out_trade_no", orderNo);
        //签名类型
        data.put("sign_type", signType);
        //签名 签名中加入key
        try {

            data.put("sign", WxPayUtils.getSignature(data, WxPayConfig.key, signType));
            String requestXML = WxPayUtils.mapToXml(data);
            String responseString = HttpsClient.httpsRequestReturnString(
                    WxPayConstants.PAY_ORDER_QUERY, HttpsClient.METHOD_POST, requestXML);
            //解析返回的xml
            Map<String, String> resultMap = WxPayUtils.processResponseXml(responseString, signType);
            log.info("\n订单查询结果集：" + resultMap);
            String returnCode = resultMap.get(WxPayConstants.RETURN_CODE);
            if (WxPayConstants.SUCCESS.equals(returnCode)) {
                String resultCode = resultMap.get(WxPayConstants.RESULT_CODE);
                if (WxPayConstants.SUCCESS.equals(resultCode)) {
                    //返回支付状态
                    String tradeState = resultMap.get("trade_state");
                    //支付状态信息
                    String tradeStateDesc = resultMap.get("trade_state_desc");
                    //订单支付成功
                    if (WxPayConstants.SUCCESS.equals(tradeState)) {
                        log.info("\n订单支付成功", tradeStateDesc);
                        return false;
                    } else {
                        log.info("订单未支付成功,{}", tradeStateDesc);
                        return true;
                    }
                } else {
                    log.info("订单查询失败,{}", resultMap.get("err_code_des"));
                    return true;
                }
            } else {
                log.info("订单查询失败,{}", resultMap.get("return_msg"));
                return true;
            }
        } catch (Exception e) {
            log.info("订单查询失败(系统异常)");
            System.out.println(e.toString());
            return true;
        }
    }

    /**
     * 设置微信二维码失效时间，并返回具体失效的时间点
     *
     * @param expire 二维码的有效时间，单位是毫秒
     * @return
     */
    private static String getOrderExpireTime(Long expire) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        Date afterDate = new Date(now.getTime() + expire);
        return sdf.format(afterDate);
    }

}
