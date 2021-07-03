package sjjd.doc.line.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.DeptIntro;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.pojo.TableResponse;
import sjjd.doc.line.service.IDeptIntroService;
import sjjd.doc.line.util.HttpClientUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生  科室介绍
 *
 * @author by xyw
 * @date 2021/4/26.
 */

@Controller
@RequestMapping("/baseIntro")
@ApiIgnore
@Slf4j
public class BaseIntroController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @Resource
    private IDeptIntroService deptIntroService;


    //科室同步
    @RequestMapping("/showDepart")
    public String news(ModelMap map) {
        map.addAttribute("pagelogo", "baseIntroshowDepart");
        return "departPage";
    }

    @RequestMapping("/departList")
    @ResponseBody
    public TableResponse<List<DeptIntro>> departList(Integer page, Integer limit, String depName) {
        TableResponse<List<DeptIntro>> tableResponse = new TableResponse<>();
        QueryWrapper<DeptIntro> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(depName), "dept_name", depName)
                .orderByDesc("state").orderByDesc("dept_type");
        IPage<DeptIntro> iPage = deptIntroService.page(new Page<>(page, limit), queryWrapper);
        tableResponse.setCode("0");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(iPage.getRecords());
        tableResponse.setCount(iPage.getTotal() + "");
        return tableResponse;
    }


    @ApiOperation(value = "科室同步", httpMethod = "POST")
    @RequestMapping("/departSyn")
    @ResponseBody
    public ResponseResult<Void> departSyn() {
        ResponseResult<Void> result = new ResponseResult<>();
        JSONObject input = new JSONObject();
        input.put("DeptId", "");
        log.info("科室同步入参：{}", input.toString());
        String jsonstrs = HttpClientUtils.callWebStr(hisUrl, hisMethod, "getDeptInfo", input.toString());
        //String jsonstrs="{  \"state\":1, \"message\":\"查询成功！\", \"data\":[{\"DeptId\":\"1030008\",\"DeptName\":\"信息科\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50600\",\"DeptName\":\"后勤库\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50700\",\"DeptName\":\"手术室药房\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60700\",\"DeptName\":\"病理科\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30500\",\"DeptName\":\"急诊部\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60800\",\"DeptName\":\"手术室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"20100\",\"DeptName\":\"供应室\",\"DeptType\":\"后勤服务部\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"20200\",\"DeptName\":\"成本控制科\",\"DeptType\":\"后勤服务部\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60900\",\"DeptName\":\"门诊治疗室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10100\",\"DeptName\":\"院部\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10900\",\"DeptName\":\"护理部\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10500\",\"DeptName\":\"医务科\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10600\",\"DeptName\":\"财务科\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10700\",\"DeptName\":\"收费室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"70100\",\"DeptName\":\"驾驶员\",\"DeptType\":\"其它科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"1070001\",\"DeptName\":\"门诊收费室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"1070002\",\"DeptName\":\"住院收费室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30100\",\"DeptName\":\"内科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30200\",\"DeptName\":\"外科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30300\",\"DeptName\":\"藏医科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30400\",\"DeptName\":\"妇产科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"4010002\",\"DeptName\":\"传染科病房\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"4020002\",\"DeptName\":\"妇产科病房\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50100\",\"DeptName\":\"西药库\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50200\",\"DeptName\":\"藏药库\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50300\",\"DeptName\":\"西药房\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50400\",\"DeptName\":\"藏药房\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60100\",\"DeptName\":\"检验科\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60200\",\"DeptName\":\"放射科\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60300\",\"DeptName\":\"胃镜室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60400\",\"DeptName\":\"心电图室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60500\",\"DeptName\":\"B超室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"60600\",\"DeptName\":\"彩超室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"10800\",\"DeptName\":\"病案室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50500\",\"DeptName\":\"卫材库\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30700\",\"DeptName\":\"五官科\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"1070003\",\"DeptName\":\"急诊收费室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"61000\",\"DeptName\":\"急诊治疗室\",\"DeptType\":\"医技科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30600\",\"DeptName\":\"儿科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50800\",\"DeptName\":\"加麻乡卫生院\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"50900\",\"DeptName\":\"加麻乡特日村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51000\",\"DeptName\":\"加麻乡金珠村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51100\",\"DeptName\":\"加麻乡扎西村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51200\",\"DeptName\":\"加麻乡白松村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51300\",\"DeptName\":\"加麻乡昌嘎村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51400\",\"DeptName\":\"琼结镇琼果村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51500\",\"DeptName\":\"琼结镇卫生院\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51600\",\"DeptName\":\"琼结镇雪康村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51700\",\"DeptName\":\"琼结镇仲堆村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51800\",\"DeptName\":\"琼结镇东嘎村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"51900\",\"DeptName\":\"下水乡卫生院\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52000\",\"DeptName\":\"下水乡下水村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52100\",\"DeptName\":\"下水乡久河村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52200\",\"DeptName\":\"下水乡措杰村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52300\",\"DeptName\":\"下水乡唐布齐村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52400\",\"DeptName\":\"拉玉乡卫生院\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52500\",\"DeptName\":\"拉玉乡日玛岗村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52600\",\"DeptName\":\"拉玉乡德庆村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52700\",\"DeptName\":\"拉玉乡强吉村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52800\",\"DeptName\":\"拉玉乡堆巴村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"52900\",\"DeptName\":\"拉玉乡白那村卫生室\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"20500\",\"DeptName\":\"抢救车（门）\",\"DeptType\":\"后勤服务部\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"20600\",\"DeptName\":\"抢救车（住）\",\"DeptType\":\"后勤服务部\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"20700\",\"DeptName\":\"清洁工\",\"DeptType\":\"后勤服务部\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"53200\",\"DeptName\":\"急救药品\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30800\",\"DeptName\":\"传染科门诊\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"53300\",\"DeptName\":\"藏医院\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"53400\",\"DeptName\":\"住院药房\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"90200\",\"DeptName\":\"总务科\",\"DeptType\":\"医辅科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"4020006\",\"DeptName\":\"五官科病房\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"53000\",\"DeptName\":\"卫生局\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"53100\",\"DeptName\":\"院内用药\",\"DeptType\":\"药剂科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"40400\",\"DeptName\":\"综合病区\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"40600\",\"DeptName\":\"妇产科病区\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"4040001\",\"DeptName\":\"综合病房\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"40500\",\"DeptName\":\"传染科病区\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"90100\",\"DeptName\":\"设备科\",\"DeptType\":\"医辅科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"40700\",\"DeptName\":\"急诊科病区\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"40800\",\"DeptName\":\"急诊科病房\",\"DeptType\":\"病区科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"30900\",\"DeptName\":\"总台\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"1070004\",\"DeptName\":\"体检收费室\",\"DeptType\":\"行政科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"31000\",\"DeptName\":\"总检\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1},{\"DeptId\":\"31100\",\"DeptName\":\"诊室一、二\",\"DeptType\":\"门诊科室\",\"Description\":\"\",\"DeptImg\":\"\",\"Tel\":\"\",\"Leader\":\"\",\"State\":1}] }";
        JSONObject jsonObjectss = JSONObject.fromObject(jsonstrs);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            result.setState(0);
            result.setMessage(jsonObjectss.get("message").toString());
            return result;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            result.setState(0);
            result.setMessage("暂无需要同步的科室");
            return result;
        }
        for (JSONObject jsonObject : jsonObjects1) {
            DeptIntro deptIntro = JSON.parseObject(JSON.toJSONString(jsonObject), DeptIntro.class);
            deptIntro.setState(null);
            QueryWrapper<DeptIntro> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dept_id", jsonObject.getString("DeptId"));
            deptIntroService.saveOrUpdate(deptIntro, queryWrapper);
        }
        result.setState(200);
        result.setMessage("同步科室成功");
        return result;
    }

    @RequestMapping("/modiflyManage")
    @ResponseBody
    public ResponseResult<Void> modiflyManage(String id, String depManage) {
        ResponseResult<Void> rr = new ResponseResult<>();

        DeptIntro byId = deptIntroService.getById(id);
        if (byId != null) {
            byId.setState(depManage);
            deptIntroService.updateById(byId);
        }
        rr.setState(200);
        rr.setMessage("操作成功");
        return rr;
    }
}
