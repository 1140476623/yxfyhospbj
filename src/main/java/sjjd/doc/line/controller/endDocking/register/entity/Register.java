package sjjd.doc.line.controller.endDocking.register.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "挂号信息", description = "")
public class Register {
    @ApiModelProperty("科室ID")
    private String registerId;

    @ApiModelProperty("排班ID")
    private String arrangeId;

    @ApiModelProperty("就诊序号")
    private String arrangeNo;

    @ApiModelProperty("患者ID")
    private String patientId;

    @ApiModelProperty("患者姓名")
    private String patientName;

    @ApiModelProperty("患者性别")
    private String patientSex;

    @ApiModelProperty("科室ID")
    private String deptId;

    @ApiModelProperty("科室名称")
    private String deptName;

    @ApiModelProperty("医生ID")
    private String doctorId;

    @ApiModelProperty("医生姓名")
    private String doctorName;

    @ApiModelProperty("医生职称")
    private String doctorTitle;

    @ApiModelProperty("就诊日期")
    private String arrangeDate;

    @ApiModelProperty("就诊时间")
    private String arrangeTime;

    @ApiModelProperty("就诊诊室")
    private String arrangeRoom;

    @ApiModelProperty("挂号名称")
    private String registerName;

    @ApiModelProperty("挂号金额")
    private String registerFee;
}
