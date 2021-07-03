package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 住院预缴充值返回
 *
 * @author by xyw
 * @date 2021/5/8.
 */

@Data
public class RespInpRegPrepay {

    @ApiModelProperty("病人Id")
    @JsonProperty("PatientId")
    private String PatientId;

    @ApiModelProperty("住院id")
    @JsonProperty("InpRegId")
    private String InpRegId;

    @ApiModelProperty("预缴金额")
    @JsonProperty("PrepayMoney")
    private String PrepayMoney;
}
