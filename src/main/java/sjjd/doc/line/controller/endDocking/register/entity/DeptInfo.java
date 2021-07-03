package sjjd.doc.line.controller.endDocking.register.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author by xyw
 * @date 2021/4/28.
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "科室信息", description = "")
public class DeptInfo {
    @ApiModelProperty("科室ID")
    private String deptId;

    @ApiModelProperty("科室名称")
    private String deptName;
}
