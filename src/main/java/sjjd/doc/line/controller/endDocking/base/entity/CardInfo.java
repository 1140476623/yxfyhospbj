package sjjd.doc.line.controller.endDocking.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 绑定卡信息
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "患者绑定卡信息", description = "")
public class CardInfo {

    @ApiModelProperty("卡类型")
    private String cardType;

    @ApiModelProperty("卡号")
    private String cardNo;

    @ApiModelProperty("卡状态：0-无效；1-正常")
    private String cardState;
}
