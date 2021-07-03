package sjjd.doc.line.controller.endDocking.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sjjd.doc.line.util.HttpClientUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author by xyw
 * @date 2021/4/27.
 */

@Api(value = "接口测试", tags = "接口测试")
@RequestMapping("/selfHelp/test")
@RestController
@Slf4j
@ApiIgnore
public class TestController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;


    @ResponseBody
    @ApiOperation(value = "接口测试", httpMethod = "POST")
    @RequestMapping("/Test")
    public void Test(String CardNo, String CardType) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CardNo", CardNo);
        jsonObject.put("CardType", CardType);

        String decrypt = HttpClientUtils.callWebStr(hisUrl, hisMethod, "getCardInfo", jsonObject.toString());
        System.out.println(decrypt);
    }


//    @RequestMapping("/GetPatInfo")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "CardNo", value = "卡号", dataType = "String"),
//            @ApiImplicitParam(name = "CardType", value = "卡类型", dataType = "String"),
//    })
//    @ApiOperation(value = "获取病人信息", httpMethod = "POST")
//    public ResponseResult<PatientInfo> GetPatInfo(String CardNo, String CardType) {
//        ResponseResult<PatientInfo> result = new ResponseResult<>();
//        Map<String, String> map = new HashMap<>();
//        map.put("CardNo", CardNo);
//        map.put("CardType", CardType);
//        String reqXml = XmlUtils.mapToXml(map);
//        String decrypt = HttpClientUtils.sendPost("GetPatInfoRequest", reqXml);
//        if (StringUtils.isEmpty(decrypt)) {
//            log.info("远程调用失败");
//            result.setState(0);
//            result.setMessage("远程调用失败");
//            return result;
//        }
//        log.info("远程调用返回的数据:{}", decrypt);
//        JSONObject jsonObject = JSONObject.parseObject(XML.toJSONObject(decrypt).toString()).getJSONObject("Response");
//        String resultCode = jsonObject.getString("ResultCode");
//        if (!"0".equals(resultCode)) {
//            String errorMsg = jsonObject.getString("ErrorMsg");
//            log.info("获取病人信息失败:{}", errorMsg);
//            result.setState(0);
//            result.setMessage("获取病人信息失败");
//            return result;
//        }
//        PatientInfo patInfo = jsonObject.getObject("PatInfo", PatientInfo.class);
//        if (patInfo == null) {
//            log.info("暂无病人信息");
//            result.setState(0);
//            result.setMessage("暂无病人信息");
//            return result;
//        }
//        log.info("获取病人信息成功");
//        result.setState(200);
//        result.setMessage("获取病人信息成功");
//        result.setData(patInfo);
//        return result;
//    }

}
