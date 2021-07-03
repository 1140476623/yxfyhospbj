package sjjd.doc.line.controller.endDocking.payment.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.controller.endDocking.payment.entity.ChargeDetList;
import sjjd.doc.line.controller.endDocking.payment.entity.GetChargeList;
import sjjd.doc.line.controller.endDocking.payment.entity.Nucleic;
import sjjd.doc.line.controller.endDocking.payment.entity.OutpatientRecord;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.util.DateUtils;
import sjjd.doc.line.util.HttpClientUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/28.
 */

@Controller
@RequestMapping("/selfHelp/OutpatientPay")
@Slf4j
@Api(value = "门诊业务接口", tags = "门诊业务接口")
public class OutpatientPayController {


    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @ApiOperation(value = "待缴费记录查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "1-未缴费2-已缴费", dataType = "String")
    })
    @RequestMapping("/GetChargeList")
    @ResponseBody
    public ResponseResult<List<GetChargeList>> GetChargeList(String patientId, String state) {
        ResponseResult<List<GetChargeList>> result = new ResponseResult<List<GetChargeList>>();

        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("State", state);
        log.info("待缴费记录查询入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "GetChargeList", input.toString());
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无待缴费信息");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无待缴费信息");
            return result;
        }
        List<GetChargeList> getChargeLists = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            GetChargeList getChargeList = JSON.parseObject(JSON.toJSONString(jsonObject), GetChargeList.class);
            getChargeLists.add(getChargeList);
        }
        result.setState(200);
        result.setMessage("操作成功");
        result.setData(getChargeLists);
        return result;
    }


    @ApiOperation(value = "待缴费详情查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String"),
            @ApiImplicitParam(name = "registerId", value = "挂号id", dataType = "String"),
            @ApiImplicitParam(name = "chargeId", value = "划价id", dataType = "String"),
    })
    @RequestMapping("/GetChargeDetList")
    @ResponseBody
    public ResponseResult<List<ChargeDetList>> GetChargeDetList(String patientId, String registerId, String chargeId) {
        ResponseResult<List<ChargeDetList>> result = new ResponseResult<List<ChargeDetList>>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("RegisterId", registerId);
        input.put("ChargeId", chargeId);
        log.info("待缴费详情查询入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "GetChargeDetList", input.toString());
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无待缴费详情");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无待缴费详情");
            return result;
        }
        List<ChargeDetList> getChargeLists = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            ChargeDetList getChargeList = JSON.parseObject(JSON.toJSONString(jsonObject), ChargeDetList.class);
            getChargeLists.add(getChargeList);
        }
        result.setState(200);
        result.setMessage("待缴费详情查询成功");
        result.setData(getChargeLists);
        return result;
    }


    @ApiOperation(value = "确认缴费", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String"),
            @ApiImplicitParam(name = "registerId", value = "挂号id", dataType = "String"),
            @ApiImplicitParam(name = "chargeId", value = "划价id", dataType = "String"),
            @ApiImplicitParam(name = "payMoney", value = "缴费金额", dataType = "String"),
            @ApiImplicitParam(name = "agtOrdNum", value = "交易流水号", dataType = "String"),
            @ApiImplicitParam(name = "operatorId", value = "终端编号", dataType = "String")
    })
    @RequestMapping("/ExeCharge")
    @ResponseBody
    public ResponseResult<JSONObject> ExeCharge(String patientId, String registerId, String chargeId,
                                                String payMoney, String agtOrdNum, String operatorId) {
        ResponseResult<JSONObject> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("RegisterId", registerId);
        input.put("ChargeId", chargeId);
        input.put("PayMoney", payMoney);
        input.put("AgtOrdNum", agtOrdNum);
        input.put("PayType", "2");
        input.put("OperatorId", operatorId);
        input.put("PayTime", DateUtils.dateFormat.format(new Date()));
        log.info("确认缴费入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "ExeCharge", input.toString());
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("缴费失败");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("缴费失败");
            return result;
        }
        result.setState(200);
        result.setMessage("确认缴费成功");
        result.setData(jsonObjects1.get(0));
        return result;
    }


    @ApiOperation(value = "门诊就诊记录查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间 yyyy-MM-dd", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束啥时间 yyyy-MM-dd", dataType = "String"),
    })
    @RequestMapping("/OutpatientRecord")
    @ResponseBody
    public ResponseResult<List<OutpatientRecord>> OutpatientRecord(String patientId, String startTime, String endTime) {
        ResponseResult<List<OutpatientRecord>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        input.put("StartTime", startTime);
        input.put("EndTime", DateUtils.addDay(endTime, 1));
        log.info("门诊就诊记录查询入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "OutpatientRecord", input.toString());
        //String jsonstrs="{  \"state\":1, \"message\":\"查询成功！\", \"data\":[{\"PatientId\":\"6002115479\",\"PatientName\":\"张三\",\"PatientSex\":\"男\",\"IdCardNo\":\"510521197508153798\",\"RegisterNo\":\"1899552001\",\"RegisterDept\":\"儿科门诊\",\"RegisterDoc\":\"芳菲\",\"RegisterTime\":\"2021/4/21 19:33:28\",\"ChargeMoney\":0.70,\"ChargeItemList\":[{\"Name\":\"门诊挂号费\",\"Unit\":\"次\",\"Spec\":\"\",\"Num\":1,\"Price\":0.70,\"PayType\":\"微信\",\"Money\":0.70}]}] }";
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无门诊就诊记录");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无门诊就诊记录");
            return result;
        }
        List<OutpatientRecord> getChargeLists = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            OutpatientRecord getChargeList = JSON.parseObject(JSON.toJSONString(jsonObject), OutpatientRecord.class);
            getChargeLists.add(getChargeList);
        }
        result.setState(200);
        result.setMessage("操作成功");
        result.setData(getChargeLists);
        return result;
    }


    @ApiOperation(value = "一键核酸检测", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "病人id", dataType = "String")
    })
    @RequestMapping("/AddCovidNucleic")
    @ResponseBody
    public ResponseResult<Nucleic> AddCovidNucleic(String patientId) {
        ResponseResult<Nucleic> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("PatientId", patientId);
        log.info("一键核酸检测入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "AddCovidNucleic", input.toString());
        //String jsonstrs="{  \"state\":1, \"message\":\"开单成功\", \"data\":[{\"ChargeId\":\"D131157\",\"RegisterId\":\"117674005\",\"Doctor\":\"李萌\",\"DeptName\":\"核酸实验室\",\"Cost\":\"72\",\"PatientId\":\"6002601836\"}] }";
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("核酸检测开单失败");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            log.info("核酸检测开单失败");
            result.setState(0);
            result.setMessage("核酸检测开单失败");
            return result;
        }
        Nucleic nucleic = JSON.parseObject(JSON.toJSONString(jsonObjects1.get(0)), Nucleic.class);
        log.info("核酸检测开单成功");
        result.setState(200);
        result.setMessage("核酸检测开单成功");
        result.setData(nucleic);
        return result;
    }
}
