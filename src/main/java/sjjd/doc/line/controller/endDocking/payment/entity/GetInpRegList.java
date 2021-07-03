package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 病人的住院信息
 *
 * @author by xyw
 * @date 2021/3/18.
 */

@Data
public class GetInpRegList {

    @ApiModelProperty("住院Id")
    @JsonProperty("InpRegId")
    private String InpRegId;

    @ApiModelProperty("住院号")
    @JsonProperty("InpRegNo")
    private String InpRegNo;

    @ApiModelProperty("患者Id")
    @JsonProperty("PatientId")
    private String PatientId;

    @ApiModelProperty("患者姓名")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty("患者性别")
    @JsonProperty("Sex")
    private String Sex;


    @ApiModelProperty("出生日期")
    @JsonProperty("BirthDay")
    private String BirthDay;


    @ApiModelProperty("联系电话")
    @JsonProperty("LinkTel")
    private String LinkTel;

    @ApiModelProperty("户口地址")
    @JsonProperty("Address")
    private String Address;

    @ApiModelProperty("病案号")
    @JsonProperty("InId")
    private String InId;

    @ApiModelProperty("入院科室")
    @JsonProperty("InDeptName")
    private String InDeptName;

    @ApiModelProperty("住院医生")
    @JsonProperty("InpDoctorName")
    private String InpDoctorName;

    @ApiModelProperty("主治医生")
    @JsonProperty("DoctorName")
    private String DoctorName;
    @ApiModelProperty("主任医生")
    @JsonProperty("DirectorName")
    private String DirectorName;
    @ApiModelProperty("床位号")
    @JsonProperty("BedOrder")
    private String BedOrder;
    @ApiModelProperty("护理等级")
    @JsonProperty("LevelName")
    private String LevelName;
    @ApiModelProperty("入院诊断")
    @JsonProperty("InDiagnoseName")
    private String InDiagnoseName;
    @ApiModelProperty("入院时间")
    @JsonProperty("InTime")
    private String InTime;

    @ApiModelProperty("出院时间yyyy-MM-dd(未出院为空)")
    @JsonProperty("OutTime")
    private String OutTime;

    @ApiModelProperty("预交金总额")
    @JsonProperty("AllMoney")
    private String AllMoney;


    @ApiModelProperty("预交金余额")
    @JsonProperty("PrepayMoney")
    private String PrepayMoney;
    @ApiModelProperty("状态1 在院 2 出院")
    @JsonProperty("State")
    private String State;

}
