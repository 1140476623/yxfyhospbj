package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by xyw
 * @date 2021/4/29.
 */

@Data
public class InpRegFeeList {

    @ApiModelProperty("计价科室Id")
    @JsonProperty("DeptId")
    private String DeptId;

    @ApiModelProperty("计价科室名称")
    @JsonProperty("DeptName")
    private String DeptName;

    @ApiModelProperty("计价医生Id")
    @JsonProperty("DoctorId")
    private String DoctorId;

    @ApiModelProperty("计价医生名称")
    @JsonProperty("DoctorName")
    private String DoctorName;

    @ApiModelProperty("计价时间")
    @JsonProperty("ChargeTime")
    private String ChargeTime;

    @ApiModelProperty("项目ID")
    @JsonProperty("ItemId")
    private String ItemId;

    @ApiModelProperty("费用类型")
    @JsonProperty("ChargeItem")
    private String ChargeItem;

    @ApiModelProperty("编码")
    @JsonProperty("ItemCode")
    private String ItemCode;

    @ApiModelProperty("名称")
    @JsonProperty("ItemName")
    private String ItemName;

    @ApiModelProperty("规格")
    @JsonProperty("Spec")
    private String Spec;

    @ApiModelProperty("单位")
    @JsonProperty("Unit")
    private String Unit;

    @ApiModelProperty("项目单价")
    @JsonProperty("Price")
    private String Price;

    @ApiModelProperty("数量")
    @JsonProperty("Num")
    private String Num;

    @ApiModelProperty("金额")
    @JsonProperty("Money")
    private String Money;

}
