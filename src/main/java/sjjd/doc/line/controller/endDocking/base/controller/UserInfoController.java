package sjjd.doc.line.controller.endDocking.base.controller;

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
import sjjd.doc.line.controller.endDocking.base.entity.CardInfo;
import sjjd.doc.line.controller.endDocking.base.entity.UserInfo;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.util.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/27.
 */

@Api(value = "患者档案查询建立", tags = "患者档案查询建立")
@Slf4j
@RestController
@RequestMapping("/selfHelp/userInfo")
public class UserInfoController {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @ResponseBody
    @ApiOperation(value = "档案查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardType", value = "卡类型: 1-身份证-3就诊卡(TP开头)5-电子健康卡", dataType = "String"),
            @ApiImplicitParam(name = "cardNo", value = "卡号", dataType = "String")
    })
    @RequestMapping("/getPatient")
    public ResponseResult<List<UserInfo>> getPatient(String cardNo, String cardType) {
        ResponseResult<List<UserInfo>> rr = new ResponseResult<>();
        List<UserInfo> data = new ArrayList<>();

        JSONObject param = new JSONObject();
        param.put("CardNo", cardNo);
        param.put("CardType", cardType);

        log.info("档案查询参数：" + param);
        String result = HttpClientUtils.callWebStr(hisUrl, hisMethod, "getCardInfo", param.toString());
        log.info("档案查询返回结果：" + result);
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
                List<JSONObject> userInfoJsons = jsonObjectResponseResult.getData();
                if (!ObjectUtils.isEmpty(userInfoJsons)) {
                    for (JSONObject userInfoJson : userInfoJsons) {
                        String patientId = userInfoJson.getString("PatientId");
                        String name = userInfoJson.getString("Name");
                        String sex = userInfoJson.getString("Sex");
                        String idCard = userInfoJson.getString("IdCard");
                        String birthday = userInfoJson.getString("BirthDay");
                        String linkTel = userInfoJson.getString("LinkTel");
                        String address = userInfoJson.getString("Address");
                        String state = userInfoJson.getString("State");
                        if ("0".equals(state)) {
                            //过滤冻结的账号
                            continue;
                        }
                        UserInfo userInfo = new UserInfo();
                        userInfo.setPatientId(patientId)
                                .setName(name)
                                .setSex("1".equals(sex) ? "男" : ("2".equals(sex) ? "女" : ""))
                                .setIdCard(idCard)
                                .setBirthday(birthday)
                                .setLinkTel(linkTel)
                                .setAddress(address)
                                .setState(state);

                        List<JSONObject> cardList = userInfoJson.getJSONArray("CardList");
                        List<CardInfo> cardInfoList = new ArrayList<>();
                        if (!ObjectUtils.isEmpty(cardList)) {
                            for (JSONObject cardInfoJson : cardList) {
                                String rcardType = cardInfoJson.getString("CardType");
                                String rcardNo = cardInfoJson.getString("CardNo");
                                String cardState = cardInfoJson.getString("CardState");
                                CardInfo cardInfo = new CardInfo();
                                cardInfo.setCardNo(rcardNo).setCardType(rcardType).setCardState(cardState);
                                cardInfoList.add(cardInfo);
                            }
                        }
                        userInfo.setCardList(cardInfoList);
                        data.add(userInfo);
                    }
                }
                rr.setState(200);
                rr.setData(data);
                return rr;
            }
        }
    }

    @ResponseBody
    @ApiOperation(value = "在线建档", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "患者姓名", dataType = "String", required = true),
            @ApiImplicitParam(name = "sex", value = "患者性别", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证号", dataType = "String", required = true),
            @ApiImplicitParam(name = "birthday", value = "出生日期", dataType = "String"),
            @ApiImplicitParam(name = "linkTel", value = "联系电话", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "户口地址", dataType = "String"),
            @ApiImplicitParam(name = "operatorId", value = "操作人员id", dataType = "String")
    })
    @RequestMapping("/createUser")
    public ResponseResult<UserInfo> createUser(String name, String sex, String idCard, String birthday,
                                               String linkTel, String address, String operatorId) {
        ResponseResult<UserInfo> rr = new ResponseResult<>();
        UserInfo userInfo = new UserInfo();

        JSONObject param = new JSONObject();
        param.put("OperatorId", operatorId);
        param.put("Name", name);
        param.put("Sex", sex);
        param.put("IdCard", idCard);
        param.put("CardType", 1);
        param.put("BirthDay", birthday);
        param.put("LinkTel", linkTel);
        param.put("Address", address);

        log.info("在线建档参数：" + param);
        String result = HttpClientUtils.callWebStr(hisUrl, hisMethod, "InsertCardInfo", param.toString());
        log.info("在线建档返回结果：" + result);
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
                List<JSONObject> data = jsonObjectResponseResult.getData();
                if (!ObjectUtils.isEmpty(data)) {
                    for (JSONObject userInfoJson : data) {
                        String patientId = userInfoJson.getString("PatientId");
                        String rname = userInfoJson.getString("Name");
                        String rsex = userInfoJson.getString("Sex");
                        String ridCard = userInfoJson.getString("IdCard");
                        String rbirthday = userInfoJson.getString("BirthDay");
                        String rlinkTel = userInfoJson.getString("LinkTel");
                        String raddress = userInfoJson.getString("Address");
                        String state = userInfoJson.getString("State");

                        userInfo.setPatientId(patientId)
                                .setName(rname)
                                .setSex("1".equals(rsex) ? "男" : ("2".equals(rsex) ? "女" : ""))
                                .setIdCard(ridCard)
                                .setBirthday(rbirthday)
                                .setLinkTel(rlinkTel)
                                .setAddress(raddress)
                                .setState(state);

                        List<JSONObject> cardList = userInfoJson.getJSONArray("CardList");
                        List<CardInfo> cardInfoList = new ArrayList<>();
                        if (!ObjectUtils.isEmpty(cardList)) {
                            for (JSONObject cardInfoJson : cardList) {
                                String rcardType = cardInfoJson.getString("CardType");
                                String rcardNo = cardInfoJson.getString("CardNo");
                                String cardState = cardInfoJson.getString("CardState");
                                CardInfo cardInfo = new CardInfo();
                                cardInfo.setCardNo(rcardNo).setCardType(rcardType).setCardState(cardState);
                                cardInfoList.add(cardInfo);
                            }
                        }
                        userInfo.setCardList(cardInfoList);
                    }
                }

                rr.setState(200);
                rr.setData(userInfo);
                return rr;
            }
        }
    }

}
