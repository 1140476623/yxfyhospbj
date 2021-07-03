package sjjd.doc.line.controller.endDocking.payment.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GetChargeList {

    @ApiModelProperty("患者Id")
    @JsonProperty("PatientId")
    private String PatientId;

    @ApiModelProperty("挂号Id")
    @JsonProperty("RegisterId")
    private String RegisterId;

    @ApiModelProperty("划价Id")
    @JsonProperty("ChargeId")
    private String ChargeId;

    @ApiModelProperty("费用类型")
    @JsonProperty("ChargeType")
    private String ChargeType;

    @ApiModelProperty("划价科室Id")
    @JsonProperty("DeptId")
    private String DeptId;

    @ApiModelProperty("划价科室名称")
    @JsonProperty("DeptName")
    private String DeptName;

    @ApiModelProperty("划价医生Id")
    @JsonProperty("DoctorId")
    private String DoctorId;

    @ApiModelProperty("划价医生名称")
    @JsonProperty("DoctorName")
    private String DoctorName;

    @ApiModelProperty("划价总金额")
    @JsonProperty("ChargeMoney")
    private String ChargeMoney;

    @ApiModelProperty("划价时间")
    @JsonProperty("CharegeTime")
    private String CharegeTime;

    @ApiModelProperty("状态")
    @JsonProperty("State")
    private String State;


}
