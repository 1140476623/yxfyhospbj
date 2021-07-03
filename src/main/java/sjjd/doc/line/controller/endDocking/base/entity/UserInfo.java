package sjjd.doc.line.controller.endDocking.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 档案信息
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "患者信息", description = "")
public class UserInfo {
    @ApiModelProperty("患者Id")
    private String patientId;

    @ApiModelProperty("患者姓名")
    private String name;

    @ApiModelProperty("患者性别")
    private String sex;

    @ApiModelProperty("证件号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("联系电话")
    private String linkTel;

    @ApiModelProperty("户口地址")
    private String address;

    @ApiModelProperty("患者状态：0-冻结；1-正常；")
    private String state;

    @ApiModelProperty("预交金余额")
    private String storageMoney;

    @ApiModelProperty("绑定卡信息")
    private List<CardInfo> cardList;

}
