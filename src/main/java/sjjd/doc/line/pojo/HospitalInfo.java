package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by xyw
 * @date 2021/3/24.
 */

@Data
public class HospitalInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @ApiModelProperty("项目名称")
    private String title;

    @ApiModelProperty("项目别名")
    private String subTitle;

    @ApiModelProperty("项目介绍内容")
    private String content;

    @ApiModelProperty("操作时间")
    private String operTime;

    @ApiModelProperty("操作人")
    private String operUser;

    @ApiModelProperty("项目类型区分医院介绍")
    private String type;

//    @ApiModelProperty("项目类型名称")
//    @TableField(exist = false)
//    private String typeName;


}
