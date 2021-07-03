package sjjd.doc.line.controller.endDocking.register.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sjjd.doc.line.controller.endDocking.register.entity.ArrangeInfo;
import sjjd.doc.line.controller.endDocking.register.entity.DeptInfo;
import sjjd.doc.line.controller.endDocking.register.entity.Register;
import sjjd.doc.line.pojo.RegisterInfo;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IRegisterInfoService;
import sjjd.doc.line.util.DateUtils;
import sjjd.doc.line.util.HttpClientUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/28.
 */
@Api(value = "挂号业务接口", tags = "挂号业务接口")
@Slf4j
@RestController
@RequestMapping("/selfHelp/Register")
public class RegisterController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @Resource
    private IRegisterInfoService registerInfoService;

    @ResponseBody
    @ApiOperation(value = "获取挂号科室", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "天数（查询天数，查询指定天数内的可挂号科室。如为空根据HIS 设置查询）", dataType = "String")
    })
    @RequestMapping("/getDeptInfo")
    public ResponseResult<List<DeptInfo>> getPatient(String day) {
        ResponseResult<List<DeptInfo>> rr = new ResponseResult<>();
        List<DeptInfo> data = new ArrayList<>();

        JSONObject param = new JSONObject();
        param.put("Day", day);

        log.info("获取挂号科室参数：" + param);
        String result = HttpClientUtils.callWebStr(hisUrl, hisMethod, "getArrangeDept", param.toString());
        log.info("获取挂号科室返回结果：" + result);
        if (ObjectUtils.isEmpty(result)) {
            rr.setState(0);
            rr.setMessage("未查到数据");
            return rr;
        } else {
            ResponseResult<List<JSONObject>> jsonObjectResponseResult = HttpClientUtils.parseResult2(result);
            if (jsonObjectResponseResult.getState() == 0) {// 失败
                rr.setState(0);
                rr.setMessage(jsonObjectResponseResult.getMessage());
                return rr;
            } else {
                List<JSONObject> data1 = jsonObjectResponseResult.getData();
                if (!ObjectUtils.isEmpty(data1)) {
                    for (JSONObject infoJson : data1) {
                        String deptId = infoJson.getString("DeptId");
                        String deptName = infoJson.getString("DeptName");

                        DeptInfo deptInfo = new DeptInfo();
                        deptInfo.setDeptId(deptId)
                                .setDeptName(deptName);

                        data.add(deptInfo);
                    }
                }

                rr.setState(200);
                rr.setData(data);
                return rr;
            }
        }
    }


    @ResponseBody
    @ApiOperation(value = "获取挂号号源", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "科室ID", dataType = "String"),
            @ApiImplicitParam(name = "doctorId", value = "医生ID", dataType = "String"),
            @ApiImplicitParam(name = "arrangeDate", value = "排班日期(yyyy-MM-dd)", dataType = "String")
    })
    @RequestMapping("/getArrangeInfo")
    public ResponseResult<List<ArrangeInfo>> getArrangeInfo(String deptId, String doctorId, String arrangeDate) {
        ResponseResult<List<ArrangeInfo>> rr = new ResponseResult<>();
        List<ArrangeInfo> data = new ArrayList<>();

        JSONObject param = new JSONObject();
        param.put("DeptId", deptId);
        param.put("DoctorId", doctorId);
        param.put("ArrangeDate", arrangeDate);

        log.info("获取挂号号源参数：" + param);
        String result = HttpClientUtils.callWebStr(hisUrl, hisMethod, "getArrangeInfo", param.toString());
        log.info("获取挂号号源返回结果：" + result);
        if (ObjectUtils.isEmpty(result)) {
            rr.setState(0);
            rr.setMessage("未查到数据");
            return rr;
        } else {
            ResponseResult<List<JSONObject>> jsonObjectResponseResult = HttpClientUtils.parseResult2(result);
            if (jsonObjectResponseResult.getState() == 0) {// 失败
                rr.setState(0);
                rr.setMessage(jsonObjectResponseResult.getMessage());
                return rr;
            } else {
                List<JSONObject> data1 = jsonObjectResponseResult.getData();
                if (!ObjectUtils.isEmpty(data1)) {
                    for (JSONObject infoJson : data1) {
                        String arrangeId = infoJson.getString("ArrangeId");
                        String deptId1 = infoJson.getString("DeptId");
                        String deptName = infoJson.getString("DeptName");
                        String doctorId1 = infoJson.getString("DoctorId");
                        String doctorName = infoJson.getString("DoctorName");
                        String doctorTitle = infoJson.getString("DoctorTitle");
                        String description = infoJson.getString("Description");
                        String doctorPhoto = infoJson.getString("DoctorPhoto");
                        String arrangeDate1 = infoJson.getString("ArrangeDate");
                        String arrangeTime = infoJson.getString("ArrangeTime");
                        String arrangeRoom = infoJson.getString("ArrangeRoom");
                        String regsiterName = infoJson.getString("RegsiterName");
                        String regsiterFee = infoJson.getString("RegsiterFee");
                        String regNum = infoJson.getString("RegNum");
                        String allNum = infoJson.getString("AllNum");
                        String state = infoJson.getString("State");

                        ArrangeInfo arrangeInfo = new ArrangeInfo();
                        arrangeInfo.setArrangeId(arrangeId)
                                .setDeptId(deptId1)
                                .setDeptName(deptName)
                                .setDoctorId(doctorId1)
                                .setDoctorName(doctorName)
                                .setDoctorTitle(doctorTitle)
                                .setDescription(description)
                                .setDoctorPhoto(doctorPhoto)
                                .setArrangeDate(arrangeDate1)
                                .setArrangeTime(arrangeTime)
                                .setArrangeRoom(arrangeRoom)
                                .setRegisterName(regsiterName)
                                .setRegisterFee(regsiterFee)
                                .setRegNum(regNum)
                                .setAllNum(allNum)
                                .setState(state);
                        data.add(arrangeInfo);
                    }
                }

                rr.setState(200);
                rr.setData(data);
                return rr;
            }
        }
    }

    @ResponseBody
    @ApiOperation(value = "挂号", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeId", value = "科室ID", dataType = "String"),
            @ApiImplicitParam(name = "patientId", value = "患者ID", dataType = "String"),
            @ApiImplicitParam(name = "payMoney", value = "支付金额", dataType = "String"),
            @ApiImplicitParam(name = "payType", value = "支付方式", dataType = "String"),
            @ApiImplicitParam(name = "agtOrdNum", value = "对应交易流水号（第三方扣费交易流水号）", dataType = "String"),
            @ApiImplicitParam(name = "payTime", value = "交易时间（yyyy-MM-dd HH:mm:ss）", dataType = "String"),
            @ApiImplicitParam(name = "operatorId", value = "操作id", dataType = "String")
    })
    @RequestMapping("/exeRegister")
    public ResponseResult<List<Register>> exeRegister(String arrangeId, String patientId, String payMoney,
                                                      String payType, String agtOrdNum,
                                                      String payTime, String operatorId) {
        ResponseResult<List<Register>> rr = new ResponseResult<>();
        List<Register> data = new ArrayList<>();

        JSONObject param = new JSONObject();
        param.put("ArrangeId", arrangeId);
        param.put("PatientId", patientId);
        param.put("PayMoney", payMoney);
        param.put("PayType", payType);
        param.put("AgtOrdNum", agtOrdNum);
        param.put("PayTime", payTime);
        param.put("OperatorId", operatorId);

        log.info("获取挂号号源参数：" + param);
        String result = HttpClientUtils.callWebStr(hisUrl, hisMethod, "ExeRegister", param.toString());
        log.info("获取挂号号源返回结果：" + result);
        if (ObjectUtils.isEmpty(result)) {
            rr.setState(0);
            rr.setMessage("未查到数据");
            return rr;
        } else {
            ResponseResult<List<JSONObject>> jsonObjectResponseResult = HttpClientUtils.parseResult2(result);
            if (jsonObjectResponseResult.getState() == 0) {// 失败
                rr.setState(0);
                rr.setMessage(jsonObjectResponseResult.getMessage());
                return rr;
            } else {
                List<JSONObject> data1 = jsonObjectResponseResult.getData();
                if (!ObjectUtils.isEmpty(data1)) {
                    for (JSONObject infoJson : data1) {
                        String registerId = infoJson.getString("RegisterId");
                        String arrangeId1 = infoJson.getString("ArrangeId");
                        String arrangeNo = infoJson.getString("ArrangeNo");
                        String patientId1 = infoJson.getString("PatientId");
                        String patientName = infoJson.getString("PatientName");
                        String patientSex = infoJson.getString("PatientSex");
                        String deptId = infoJson.getString("DeptId");
                        String deptName = infoJson.getString("DeptName");
                        String doctorId = infoJson.getString("DoctorId");
                        String doctorName = infoJson.getString("DoctorName");
                        String doctorTitle = infoJson.getString("DoctorTitle");
                        String arrangeDate = infoJson.getString("ArrangeDate");
                        String arrangeTime = infoJson.getString("ArrangeTime");
                        String arrangeRoom = infoJson.getString("ArrangeRoom");
                        String regsiterName = infoJson.getString("RegsiterName");
                        String regsiterFee = infoJson.getString("RegsiterFee");

                        Register register = new Register();
                        register.setRegisterId(registerId)
                                .setArrangeId(arrangeId1)
                                .setArrangeNo(arrangeNo)
                                .setPatientId(patientId1)
                                .setPatientName(patientName)
                                .setPatientSex(patientSex)
                                .setDeptId(deptId)
                                .setDeptName(deptName)
                                .setDoctorId(doctorId)
                                .setDoctorName(doctorName)
                                .setDoctorTitle(doctorTitle)
                                .setArrangeDate(arrangeDate)
                                .setArrangeTime(arrangeTime)
                                .setArrangeRoom(arrangeRoom)
                                .setRegisterName(regsiterName)
                                .setRegisterFee(regsiterFee);
                        data.add(register);
                    }
                }
                if (!ObjectUtils.isEmpty(data)) {
                    // 记录信息
                    for (Register register : data) {
                        RegisterInfo registerInfo = new RegisterInfo();
                        registerInfo.setRegisterNo(register.getRegisterId())
                                .setResidentName(register.getPatientName())
                                .setDeptName(register.getDeptName())
                                .setDocName(register.getDoctorName())
                                .setRegisterDate(DateUtils.getsimNow("yyyy-MM-dd HH:mm:ss", new Date()))
                                .setEventDate(register.getArrangeTime())
                                .setNumCome(operatorId);    //标注那台自助机
                        registerInfoService.save(registerInfo);
                    }
                }
                log.info("挂号成功");
                rr.setState(200);
                rr.setMessage("挂号成功");
                rr.setData(data);
                return rr;
            }
        }
    }

}
