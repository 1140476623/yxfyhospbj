package sjjd.doc.line.controller.endDocking.payment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sjjd.doc.line.controller.endDocking.payment.entity.GetInpRegList;
import sjjd.doc.line.controller.endDocking.payment.entity.InpRegFeeList;
import sjjd.doc.line.controller.endDocking.payment.entity.RespInpRegPrepay;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IReportDayService;
import sjjd.doc.line.util.DateUtils;
import sjjd.doc.line.util.HttpClientUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by xyw
 * @date 2021/4/25.
 */

@Api(value = "住院业务", tags = "住院业务")
@RequestMapping("/selfHelp/prePayment")
@RestController
@Slf4j
public class HospitalController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @Resource
    private IReportDayService dayService;

    @ApiOperation(value = "患者通过住院号或扫描二维码获取在院的信息", httpMethod = "POST")
    @RequestMapping("/getInpRegByHospNo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人ID", dataType = "String"),
            @ApiImplicitParam(name = "startDate", value = "开始日期yyyy-MM-dd", dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束日期yyyy-MM-dd", dataType = "String"),
            @ApiImplicitParam(name = "inpRegNo", value = "住院号", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型1-刷卡查询,2-住院号", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "类型1-在院,2-所有", dataType = "String"),
    })
    public ResponseResult<List<GetInpRegList>> getInpRegByHospNo(String patientId, String startDate,
                                                                 String endDate, String inpRegNo,
                                                                 String type, String state) {
        ResponseResult<List<GetInpRegList>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        String method;
        if ("1".equals(type)) {
            method = "GetInpRegList";
            input.put("PatientId", patientId);
            input.put("StartDate", startDate);
            input.put("EndDate", DateUtils.addDay(endDate, 1));
            log.info("通过刷卡获取在院的信息入参：{}", input.toString());
        } else {
            method = "GetInHospInfo";
            input.put("InpRegNo", inpRegNo);
            log.info("通过住院号获取在院的信息入参：{}", input.toString());
        }
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, method, input.toString());
        //String jsonstrs="{  \"state\":1, \"message\":\"查询成功！\", \"data\":[{\"InpRegId\":\"0002020194001\",\"InpRegNo\":\"0002020194001\",\"PatientId\":\"6002588221\",\"Name\":\"琼吉\",\"Sex\":\"2\",\"BirthDay\":\"1996/5/8\",\"LInkTel\":\"13989992860\",\"Address\":\"西藏贡嘎县江塘镇保村4组\",\"InId\":\"0002020194\",\"InDeptName\":\"妇产科病房\",\"InpDoctorName\":\"\",\"DoctorName\":\"央宗\",\"DirectorName\":\"\",\"BedOrder\":\"4\",\"LevelName\":\"\",\"InDiagnoseName\":\"单胎活产\",\"InTime\":\"2020/11/26 19:23:22\",\"OutTime\":\"2020/12/1 12:35:03\",\"AllMoney\":3442.30,\"PrepayMoney\":0,\"State\":2},{\"InpRegId\":\"0002020194002\",\"InpRegNo\":\"0002020194002\",\"PatientId\":\"6002588221\",\"Name\":\"琼吉\",\"Sex\":\"2\",\"BirthDay\":\"1996/5/8\",\"LInkTel\":\"13989992860\",\"Address\":\"西藏贡嘎县江塘镇保村4组\",\"InId\":\"0002020194\",\"InDeptName\":\"外科病房\",\"InpDoctorName\":\"\",\"DoctorName\":\"旦增晋美\",\"DirectorName\":\"\",\"BedOrder\":\"13\",\"LevelName\":\"\",\"InDiagnoseName\":\"急性阑尾炎\",\"InTime\":\"2021/1/26 14:47:43\",\"OutTime\":\"2021/2/1 12:09:29\",\"AllMoney\":438.91,\"PrepayMoney\":0,\"State\":1}] }";
        JSONObject jsonObjectss = JSONObject.parseObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无住院信息");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无住院信息");
            return result;
        }
        List<GetInpRegList> inpRegLists = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            GetInpRegList inpRegList = JSON.parseObject(JSON.toJSONString(jsonObject), GetInpRegList.class);
            inpRegList.setSex("1".equals(inpRegList.getSex()) ? "男" : "女");
            inpRegLists.add(inpRegList);
        }
        if (CollectionUtils.isEmpty(inpRegLists)) {
            result.setState(0);
            result.setMessage("暂无住院信息");
            return result;
        }
        if ("1".equals(state)) {
            List<GetInpRegList> collect = inpRegLists.stream().filter(s -> "1".equals(s.getState()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                result.setState(0);
                result.setMessage("暂无在院信息");
                return result;
            }
            result.setState(200);
            result.setMessage("获取在院信息成功");
            result.setData(collect);
            return result;
        }
        result.setState(200);
        result.setMessage("获取住院信息成功");
        result.setData(inpRegLists);
        return result;
    }

    @ApiOperation(value = "住院预交金充值", httpMethod = "POST")
    @RequestMapping("/ExeInpRegPrepay")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String"),
            @ApiImplicitParam(name = "inpRegId", value = "住院id", dataType = "String"),
            @ApiImplicitParam(name = "payMoney", value = "充值金额", dataType = "String"),
            @ApiImplicitParam(name = "agtOrdNum", value = "交易流水号", dataType = "String"),
            @ApiImplicitParam(name = "operatorId", value = "终端编号", dataType = "String")
    })
    @ResponseBody
    public ResponseResult<List<RespInpRegPrepay>> ExeInpRegPrepay(String patientId, String inpRegId,
                                                                  String payMoney, String agtOrdNum,
                                                                  String operatorId) {
        ResponseResult<List<RespInpRegPrepay>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("InpRegId", inpRegId);
        input.put("PayMoney", payMoney);
        input.put("AgtOrdNum", agtOrdNum);
        input.put("PayType", "2");
        input.put("PayTime", DateUtils.dateFormat.format(new Date()));
        input.put("OperatorId", operatorId);
        log.info("确认预交入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "ExeInpRegPrepay", input.toString());
        JSONObject jsonObjectss = JSONObject.parseObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("住院预缴失败");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("住院预缴失败");
            return result;
        }
        List<RespInpRegPrepay> list = new ArrayList<>();
        for (JSONObject json : jsonObjects1) {
            RespInpRegPrepay regPrepay = JSON.parseObject(JSON.toJSONString(json), RespInpRegPrepay.class);
            list.add(regPrepay);
        }
        result.setState(200);
        result.setMessage("住院预缴成功");
        result.setData(list);
        return result;
    }


    @ApiOperation(value = "获取住院费用清单", httpMethod = "POST")
    @RequestMapping("/GetInpRegPrepayList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人ID", dataType = "String"),
            @ApiImplicitParam(name = "inpRegId", value = "住院ID", dataType = "String")
    })
    public ResponseResult<List<InpRegFeeList>> GetInpRegPrepayList(String patientId, String inpRegId) {
        ResponseResult<List<InpRegFeeList>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("InpRegId", inpRegId);

        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "GetInpRegFeeList", input.toString());
        //String jsonstrs = "{  \"state\":1, \"message\":\"查询成功！\", \"data\":[{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/8 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/8 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/9 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/9 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/10 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/10 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/11 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/11 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/12 23:55:02\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/12 23:55:02\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/13 23:55:00\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/13 23:55:00\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/14 23:55:00\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/14 23:55:00\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/15 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/15 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/16 23:55:00\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/16 23:55:00\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/17 23:55:01\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/17 23:55:01\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/18 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/18 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/19 23:55:02\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/19 23:55:02\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/20 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/20 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/21 23:55:01\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/21 23:55:01\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/22 23:55:01\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/22 23:55:01\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/23 23:55:00\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/23 23:55:00\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/24 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/24 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/25 23:55:02\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/25 23:55:02\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/26 23:55:01\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/26 23:55:01\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/27 23:55:01\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/27 23:55:01\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/28 23:55:04\",\"ItemId\":\"110200005\",\"ChargeItemName\":\"诊查费\",\"ItemCode\":\"110200005\",\"ItemName\":\"住院诊查费\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":9,\"Num\":1,\"Money\":9},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DoctorId\":\"system\",\"DoctorName\":\"\",\"ChargeTime\":\"2021/4/28 23:55:04\",\"ItemId\":\"110900001c\",\"ChargeItemName\":\"床位费\",\"ItemCode\":\"110900001c\",\"ItemName\":\"普通病房床位费(3人间)\",\"Spec\":\"\",\"Unit\":\"日\",\"Price\":25,\"Num\":1,\"Money\":25}] }";
        JSONObject jsonObjectss = JSONObject.parseObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无住院费用清单");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无住院费用清单");
            return result;
        }
        List<InpRegFeeList> list = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            InpRegFeeList feeList = JSON.parseObject(JSON.toJSONString(jsonObject), InpRegFeeList.class);
            list.add(feeList);
        }
        result.setState(200);
        result.setMessage("获取住院费用清单成功");
        result.setData(list);
        return result;

    }

}
