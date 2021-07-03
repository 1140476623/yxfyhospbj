package sjjd.doc.line.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by xyw
 * @date 2021/5/7.
 */

@Data
public class DoctorIntro {
    // "DeptId":"40200,30400,4020002","DoctorId":"2033","DoctorName":"查朵",
    // "DoctorSex":"2","DoctorTitle":"医师","DoctorPhoto":"","Description":"","State":1,"isExperts":"0"
    @ApiModelProperty(value = "HIS医生Id")
    private String doctorId;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "医生性别")
    private String doctorSex;

    @ApiModelProperty(value = "医生职称")
    private String doctorTitle;

    @ApiModelProperty(value = "医生简介")
    private String description;

    @ApiModelProperty(value = "头像")
    private String doctorPhoto;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "是否专家0-否 1-是")
    private String isExperts;
}
