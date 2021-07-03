package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 预缴记录
 *
 * @author by xyw
 * @date 2021/3/18.
 */

@Data
public class InpRegPrepayList {

    @ApiModelProperty("HIS交易流水号")
    @JsonProperty("HosTranNo")
    private String HosTranNo;


    @ApiModelProperty("充值方式")
    @JsonProperty("PayType")
    private String PayType;

    @ApiModelProperty("充值金额")
    @JsonProperty("PayMoney")
    private String PayMoney;

    @ApiModelProperty("余额")
    @JsonProperty("FreeCost")
    private String FreeCost;

    @ApiModelProperty("充值时间")
    @JsonProperty("PayTime")
    private String PayTime;

    @ApiModelProperty("操作员")
    @JsonProperty("Operator")
    private String Operator;

}
