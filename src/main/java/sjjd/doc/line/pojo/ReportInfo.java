package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author by xyw
 * @date 2020/12/10.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReportInfo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("患者卡号")
    @TableField("patient_id")
    private String enetpatientId;

    @ApiModelProperty("检验Id-LIS唯一标识一次检验申请Id")
    @TableField("assay_id")
    private String applyId;

    @ApiModelProperty("患者姓名")
    private String patientName;

    @ApiModelProperty("患者性别")
    @TableField("patient_sex")
    private String sex;

    @ApiModelProperty("患者年龄")
    @TableField("patient_age")
    private String age;

    @ApiModelProperty("报告时间-yyyy-MM-dd HH:mm:ss")
    @TableField("report_time")
    private String checkDate;

    @ApiModelProperty("报告路径")
    private String reportUrl;

    @ApiModelProperty("项目名称")
    @TableField("item_name")
    private String examItem;

    @ApiModelProperty("报告类别-1.检查 2.检验")
    private String reportType = "检查";

    @ApiModelProperty("报告状态-1.已出 2.未出 3.已打印")
    private String reportState;
}
