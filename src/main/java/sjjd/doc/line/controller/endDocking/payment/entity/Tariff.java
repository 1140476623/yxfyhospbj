package sjjd.doc.line.controller.endDocking.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 物价公示
 *
 * @author by xyw
 * @date 2021/4/29.
 */

@Data
public class Tariff {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("项目编号")
    @JsonProperty("ItemCode")
    private String ItemCode;

    @ApiModelProperty("项目名称")
    @JsonProperty("ItemName")
    private String ItemName;

    @ApiModelProperty("项目规格")
    @JsonProperty("ItemSpec")
    private String ItemSpec;

    @ApiModelProperty("项目单位")
    @JsonProperty("ItemUnit")
    private String ItemUnit;

    @ApiModelProperty("项目单价")
    @JsonProperty("ItemPrice")
    private String ItemPrice;

    @ApiModelProperty("项目类型")
    @JsonProperty("ItemClass")
    private String ItemClass;

    @ApiModelProperty("生产厂家")
    @JsonProperty("Manufacturer")
    private String Manufacturer;

    @ApiModelProperty("医保等级")
    @JsonProperty("InsureLevel")
    private String InsureLevel;
}
