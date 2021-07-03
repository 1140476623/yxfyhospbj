package sjjd.doc.line.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 报告内容
 * @param:
 * @author: xyw
 * @date: 2021/3/1
 */
@Data
public class ReportContent {

    @ApiModelProperty("患者id")
    private String patientId;

    @ApiModelProperty("患者姓名")
    private String patientName;

    @ApiModelProperty("患者年龄")
    private String patientAge;

    @ApiModelProperty("患者性别")
    private String patientSex;

    @ApiModelProperty("报告唯一id")
    private String assayId;

    @ApiModelProperty("科室")
    private String appDep;

    @ApiModelProperty("病床号")
    private String bedNum;

    @ApiModelProperty("病历号")
    private String recordNum;

    @ApiModelProperty("样本号")
    private String sampleNo;

    @ApiModelProperty("样本种类")
    private String sampleType;

    @ApiModelProperty("临床诊断")
    private String clinical;

    @ApiModelProperty("检验项目")
    private String checkItem;

//    @ApiModelProperty("样本编号")
//    private String serialNo;

    @ApiModelProperty("采集时间")
    private String appTime;

    @ApiModelProperty("接收时间")
    private String audTime;

    @ApiModelProperty("报告时间")
    private String checkTime;

    @ApiModelProperty("检验医生")
    private String checkDoctor;

    @ApiModelProperty("审核医生")
    private String audDoctor;

    @ApiModelProperty("送检医生")
    private String appDoctor;

    @ApiModelProperty("备注")
    private String mark;

    @ApiModelProperty("报告图片---4张或者无")
    private List<String> image;
    @ApiModelProperty("报告数据项明细")
    private List<ReportItem> items;

}
