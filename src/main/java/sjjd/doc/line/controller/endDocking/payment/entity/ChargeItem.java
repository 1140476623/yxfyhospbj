package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊就诊记录  费用明细
 *
 * @author by xyw
 * @date 2021/4/28.
 */

@Data
public class ChargeItem {


    @ApiModelProperty("项目名称")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty("项目单位")
    @JsonProperty("Unit")
    private String Unit;

    @ApiModelProperty("项目规格")
    @JsonProperty("Spec")
    private String Spec;

    @ApiModelProperty("数量")
    @JsonProperty("Num")
    private String Num;

    @ApiModelProperty("项目单价")
    @JsonProperty("Price")
    private String Price;

    @ApiModelProperty("支付类型")
    @JsonProperty("PayType")
    private String PayType;

    @ApiModelProperty("金额")
    @JsonProperty("Money")
    private String Money;
}
