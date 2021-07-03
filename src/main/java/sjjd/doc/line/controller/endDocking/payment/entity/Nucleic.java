package sjjd.doc.line.controller.endDocking.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 核酸检测
 *
 * @author by xyw
 * @date 2021/5/6.
 */

@Data
public class Nucleic {

    @ApiModelProperty("划价Id")
    @JsonProperty("ChargeId")
    private String ChargeId;

    @ApiModelProperty("挂号核酸检测Id")
    @JsonProperty("RegisterId")
    private String RegisterId;

    @ApiModelProperty("开单医生")
    @JsonProperty("Doctor")
    private String Doctor;

    @ApiModelProperty("开单科室")
    @JsonProperty("DeptName")
    private String DeptName;

    @ApiModelProperty("金额")
    @JsonProperty("Cost")
    private String Cost;

    @ApiModelProperty("患者Id")
    @JsonProperty("PatientId")
    private String PatientId;
}
