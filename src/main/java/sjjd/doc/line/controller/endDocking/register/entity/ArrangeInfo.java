package sjjd.doc.line.controller.endDocking.register.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "排班信息", description = "")
public class ArrangeInfo {
    @ApiModelProperty("排班ID")
    private String arrangeId;

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

    @ApiModelProperty("医生简介")
    private String description;

    @ApiModelProperty("医生头像（Pdf 头像路径 或者 base64的 字符串）")
    private String doctorPhoto;

    @ApiModelProperty("出诊日期")
    private String arrangeDate;

    @ApiModelProperty("出诊时间1上午2下午3晚间4全天")
    private String arrangeTime;

    @ApiModelProperty("出诊诊室")
    private String arrangeRoom;

    @ApiModelProperty("挂号名称")
    private String registerName;

    @ApiModelProperty("挂号金额")
    private String registerFee;

    @ApiModelProperty("剩余号源")
    private String regNum;

    @ApiModelProperty("总号源")
    private String allNum;

    @ApiModelProperty("状态（0 禁用 1 正常）")
    private String state;
}
