package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChargeDetList {

    @ApiModelProperty("患者Id")
    @JsonProperty("PatientId")
    private String PatientId;

    @ApiModelProperty("挂号Id")
    @JsonProperty("RegisterId")
    private String RegisterId;

    @ApiModelProperty("划价Id")
    @JsonProperty("ChargeId")
    private String ChargeId;

    @ApiModelProperty("划价明细Id")
    @JsonProperty("ChargeDetId")
    private String ChargeDetId;

    @ApiModelProperty("项目ID")
    @JsonProperty("ItemId")
    private String ItemId;

    @ApiModelProperty("项目编码")
    @JsonProperty("ItemCode")
    private String ItemCode;

    @ApiModelProperty("项目名称")
    @JsonProperty("ItemName")
    private String ItemName;

    @ApiModelProperty("项目规格")
    @JsonProperty("Spec")
    private String Spec;

    @ApiModelProperty("项目单位")
    @JsonProperty("Unit")
    private String Unit;

    @ApiModelProperty("项目单价")
    @JsonProperty("Price")
    private String Price;

    @ApiModelProperty("划价数量")
    @JsonProperty("Num")
    private String Num;

    @ApiModelProperty("划价金额")
    @JsonProperty("Money")
    private String Money;


}
