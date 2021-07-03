package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author by xyw
 * @date 2021/5/6.
 */

@Data
@TableName("department")
public class DeptIntro implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "HIS科室Id")
    private String deptId;

    @ApiModelProperty(value = "科室名称")
    private String deptName;

    @ApiModelProperty(value = "科室描述")
    private String description;

    @ApiModelProperty(value = "科室类型")
    private String deptType;

    @ApiModelProperty(value = "启用禁用状态1启用 0禁用")
    private String state;
}
