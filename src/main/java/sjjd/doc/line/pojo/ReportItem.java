package sjjd.doc.line.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 检验报告数据项
 * @param:
 * @author: xyw
 * @date: 2021/3/1
 */
@Data
public class ReportItem {

    @ApiModelProperty("序号")
    private String serialNo;

    @ApiModelProperty("简写(编码)")
    private String engName;

    @ApiModelProperty("中文名称")
    private String itemName;

    @ApiModelProperty("结果")
    private String result;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("参考值")
    private String reference;
}
