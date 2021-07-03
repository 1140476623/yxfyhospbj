package sjjd.doc.line.controller.endDocking.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sjjd.doc.line.controller.endDocking.payment.entity.Tariff;
import sjjd.doc.line.pojo.*;
import sjjd.doc.line.service.IDeptIntroService;
import sjjd.doc.line.service.IHospitalInfoService;
import sjjd.doc.line.service.INewsService;
import sjjd.doc.line.service.ITariffService;
import sjjd.doc.line.util.HttpClientUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/27.
 */

@Api(value = "基础数据查询", tags = "基础数据查询")
@Slf4j
@RestController
@RequestMapping("/selfHelp/baseInfo")
public class BaseInfoController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @Resource
    private INewsService newsService;

    @Resource
    private IHospitalInfoService hospitalInfoService;

    @Resource
    private IDeptIntroService deptIntroService;

    @Resource
    private ITariffService tariffService;

    @ResponseBody
    @ApiOperation(value = "物价查询（物价展示用）", httpMethod = "POST")
    @RequestMapping("/GetTariffList")
    public ResponseResult<List<Tariff>> GetTariffList() {
        ResponseResult<List<Tariff>> result = new ResponseResult<>();
        QueryWrapper<Tariff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_class", "西药").or().eq("item_class", "中成药");
        List<Tariff> list = tariffService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            result.setState(0);
            result.setMessage("暂无物价信息");
            return result;
        }
        result.setState(200);
        result.setMessage("获取物价成功");
        result.setData(list.size() > 100 ? list.subList(0, 100) : list);
        return result;
    }


    @RequestMapping("/GetNotice")
    @ApiOperation(value = "获取通知公告", httpMethod = "POST")
    public ResponseResult<List<News>> GetNotice() {
        ResponseResult<List<News>> result = new ResponseResult<>();
        List<News> list = newsService.list(new QueryWrapper<News>().eq("state", "1")
                .orderByDesc("top").orderByDesc("oper_time"));
        if (CollectionUtils.isEmpty(list)) {
            log.info("暂无通知公告");
            result.setState(0);
            result.setMessage("暂无通知公告");
            return result;
        }
        log.info("获取通知公告成功");
        result.setState(200);
        result.setMessage("获取通知公告成功");
        result.setData(list);
        return result;
    }


    @RequestMapping("/GetHospInfo")
    @ApiOperation(value = "获取医院单位介绍", httpMethod = "POST")
    public ResponseResult<HospitalInfo> GetHospInfo() {
        ResponseResult<HospitalInfo> result = new ResponseResult<>();
        List<HospitalInfo> list = hospitalInfoService.list();
        if (CollectionUtils.isEmpty(list)) {
            log.info("暂无医院单位介绍");
            result.setState(0);
            result.setMessage("暂无医院单位介绍");
            return result;
        }
        log.info("获取医院单位介绍成功");
        result.setState(200);
        result.setMessage("获取医院单位介绍成功");
        result.setData(list.get(0));
        return result;
    }

    @RequestMapping("/GetFeatureDept")
    @ApiOperation(value = "获取特色科室介绍", httpMethod = "POST")
    public ResponseResult<HospitalInfo> GetFeatureDept() {
        ResponseResult<HospitalInfo> result = new ResponseResult<>();
        List<HospitalInfo> list = hospitalInfoService.list();
        if (CollectionUtils.isEmpty(list)) {
            log.info("暂无特色科室介绍");
            result.setState(0);
            result.setMessage("暂无特色科室介绍");
            return result;
        }
        log.info("获取特色科室成功");
        result.setState(200);
        result.setMessage("获取特色科室成功");
        result.setData(list.get(1));
        return result;
    }


    @RequestMapping("/GetDeptInfo")
    @ApiOperation(value = "获取科室信息", httpMethod = "POST")
    public ResponseResult<List<DeptIntro>> GetDeptInfo() {
        ResponseResult<List<DeptIntro>> result = new ResponseResult<>();
        List<DeptIntro> list = deptIntroService.list(new QueryWrapper<DeptIntro>()
                .eq("state", "1"));
        if (CollectionUtils.isEmpty(list)) {
            log.info("暂无科室");
            result.setState(0);
            result.setMessage("暂无科室");
            return result;
        }
        log.info("获取科室成功");
        result.setState(200);
        result.setMessage("获取科室成功");
        result.setData(list);
        return result;
    }


    @RequestMapping("/GetDocInfo")
    @ApiOperation(value = "获取科室下医生介绍信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "科室id", dataType = "String"),
            @ApiImplicitParam(name = "doctorId", value = "医生id", dataType = "String")
    })
    public ResponseResult<List<DoctorIntro>> GetDocInfo(String deptId, String doctorId) {
        ResponseResult<List<DoctorIntro>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("DeptId", deptId == null ? "" : deptId);
        input.put("DoctorId", doctorId == null ? "" : doctorId);
        log.info("获取科室下医生信息入参：{}", input.toString());
        String resJson = HttpClientUtils.callWebStr(hisUrl, hisMethod,
                "getDoctorInfo", input.toString());
        JSONObject jsonObjectss = JSONObject.fromObject(resJson);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("当前科室暂无医生");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            log.info("当前科室暂无医生");
            result.setState(0);
            result.setMessage("当前科室暂无医生");
            return result;
        }
        List<DoctorIntro> list = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            DoctorIntro doctorIntro = JSON.parseObject(JSON.toJSONString(jsonObject), DoctorIntro.class);
            if (!"1".equals(doctorIntro.getIsExperts())) {
                doctorIntro.setDoctorSex("1".equals(doctorIntro.getDoctorSex()) ? "男" : "女");
                list.add(doctorIntro);
            }
        }
        result.setState(200);
        result.setMessage("获取当前科室医生信息成功");
        result.setData(list);
        return result;
    }


    @RequestMapping("/GetDocInfoIsExperts")
    @ApiOperation(value = "获取专家介绍信息", httpMethod = "POST")
    public ResponseResult<List<DoctorIntro>> GetDocInfoIsExperts() {
        ResponseResult<List<DoctorIntro>> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("DeptId", "");
        input.put("DoctorId", "");
        log.info("获取科室下医生信息入参：{}", input.toString());
        String resJson = HttpClientUtils.callWebStr(hisUrl, hisMethod,
                "getDoctorInfo", input.toString());
        JSONObject jsonObjectss = JSONObject.fromObject(resJson);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage("暂无专家介绍");
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            log.info("暂无专家介绍");
            result.setState(0);
            result.setMessage("暂无专家介绍");
            return result;
        }
        List<DoctorIntro> list = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects1) {
            DoctorIntro doctorIntro = JSON.parseObject(JSON.toJSONString(jsonObject), DoctorIntro.class);
            if ("1".equals(doctorIntro.getIsExperts())) {
                doctorIntro.setDoctorSex("1".equals(doctorIntro.getDoctorSex()) ? "男" : "女");
                list.add(doctorIntro);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            log.info("暂无专家介绍");
            result.setState(0);
            result.setMessage("暂无专家介绍");
            return result;
        }
        result.setState(200);
        result.setMessage("获取专家介绍成功");
        result.setData(list);
        return result;
    }

}
