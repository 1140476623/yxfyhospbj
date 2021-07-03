package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 门诊就诊记录
 *
 * @author by xyw
 * @date 2021/4/28.
 */

@Data
public class OutpatientRecord {


    @ApiModelProperty("病人Id")
    @JsonProperty("PatientId")
    private String PatientId;

    @ApiModelProperty("姓名")
    @JsonProperty("PatientName")
    private String PatientName;
    @ApiModelProperty("性别")
    @JsonProperty("PatientSex")
    private String PatientSex;

    @ApiModelProperty("身份证号")
    @JsonProperty("IdCardNo")
    private String IdCardNo;

    @ApiModelProperty("挂号单号")
    @JsonProperty("RegisterNo")
    private String RegisterNo;

    @ApiModelProperty("科室")
    @JsonProperty("RegisterDept")
    private String RegisterDept;

    @ApiModelProperty("医生")
    @JsonProperty("RegisterDoc")
    private String RegisterDoc;

    @ApiModelProperty("挂号时间")
    @JsonProperty("RegisterTime")
    private String RegisterTime;

    @ApiModelProperty("金额")
    @JsonProperty("ChargeMoney")
    private String ChargeMoney;

    @ApiModelProperty("明细")
    @JsonProperty("ChargeItemList")
    private List<ChargeItem> ChargeItemList;

}
