package sjjd.doc.line.controller.endDocking.report;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.ReportInfo;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IReportInfoService;
import sjjd.doc.line.util.DateUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/28.
 */

@Controller
@RequestMapping("/selfHelp/Report")
@Slf4j
@Api(value = "报告打印业务接口", tags = "报告打印业务接口")
public class ReportController {

    @Resource(name = "hisJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Value("${pacsUrl}")
    private String pacsUrl;

    @Value("${web.upload-path}")
    private String path;

    @Resource
    private IReportInfoService reportInfoService;


    @ApiOperation(value = "获取患者Pacs检查报告列表", httpMethod = "GET")
    @RequestMapping("/getReportList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNo", value = "二维码内容", dataType = "String"),
            @ApiImplicitParam(name = "startDate", value = "查询开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "查询结束时间", dataType = "String"),
    })
    @ResponseBody
    public ResponseResult<List<ReportInfo>> getReportList(String cardNo, String startDate, String endDate) {
        ResponseResult<List<ReportInfo>> result = new ResponseResult<>();
        endDate = DateUtils.addDay(endDate, 1);//  结束时间加一天
        log.info("\n患者门诊卡号：{}，查询开始时间：{}，查询结束时间：{}", cardNo, startDate, endDate);
        //  查询pacs 视图sql
        String sql = "select  *  from  VIEW_GETPATIENTINFO  where  enetpatientid = ? " +
                " and  checkdate>=to_date(?,'yyyy-mm-dd')  " +
                " and  checkdate<=to_date(?,'yyyy-mm-dd') and  BGZT = '1' ";
        BeanPropertyRowMapper<ReportInfo> rowMapper = new BeanPropertyRowMapper<>(ReportInfo.class);
        List<ReportInfo> query;
        try {
            //远程链接  pacs 数据库  获取报告列表数据
            query = jdbcTemplate.query(sql, rowMapper, cardNo, startDate, endDate);
        } catch (Exception e) {
            result.setState(0);
            result.setMessage("连接Pacs数据库失败");
            log.info("获取检验报告列表时，连接Pacs数据库失败" + e.toString());
            return result;
        }
        if (CollectionUtils.isEmpty(query)) {
            log.info("暂无检查报告信息");
            result.setState(0);
            result.setMessage("暂无检查报告信息");
            return result;
        }
        for (ReportInfo reportInfo : query) {
            reportInfoService.saveOrUpdate(reportInfo, new QueryWrapper<ReportInfo>()
                    .eq("patient_id", reportInfo.getEnetpatientId())
                    .eq("assay_id", reportInfo.getApplyId()));
        }
        log.info("\n当前患者的报告列表：{}", query);
        result.setState(200);
        result.setMessage("获取检查报告列表成功");
        result.setData(query);
        return result;
    }


    @ApiOperation(value = "获取患者报告详情", httpMethod = "GET")
    @RequestMapping("/getReportPdf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "患者id", dataType = "String", required = true),
            @ApiImplicitParam(name = "applyId", value = "报告id(报告列表返回的applyid)", dataType = "String", required = true)
    })
    @ResponseBody
    public ResponseResult<RespPdfUrl> getReportPdf(String patientId, String applyId) {
        log.info("获取报告详情：\npatientId:{}\napplyId:{}", patientId, applyId);
        ResponseResult<RespPdfUrl> result = new ResponseResult<>();
        String tempApplyId = "";   // 用于保存单个报告id ,查询报告
        if (StringUtils.isNotEmpty(applyId)) {
            String[] split = applyId.split(",");
            tempApplyId = split[0];
        }
        String pdfFilePath = path + "reportInfo/" + applyId + ".pdf";
        try {
            String pacsUrlPath = pacsUrl + "?appid=" + tempApplyId + "&type=jpg";
            //String pacsUrlPath = "http://192.168.2.198:8080/yxfyhospbj/news/logo.png";
            URL url = new URL(pacsUrlPath);
            log.info("pacs请求路径：{}", pacsUrlPath);
            boolean convert = convert(url, pdfFilePath);
            if (convert) {
                log.info("报告图片转化pdf文件成功");
                String pdfPath = "/reportInfo/" + applyId + ".pdf";
                RespPdfUrl pdfUrl = new RespPdfUrl();
                pdfUrl.setPdfUrl("/yxfyhospbj" + pdfPath);  //前端展示
                ReportInfo reportInfo = new ReportInfo();
                reportInfo.setReportUrl(pdfPath);   //后台页面展示
                reportInfoService.update(reportInfo, new QueryWrapper<ReportInfo>()
                        .eq("patient_id", patientId).eq("assay_id", applyId));
                result.setState(200);
                result.setMessage("获取到报告详情成功");
                result.setData(pdfUrl);
                return result;
            }
            log.info("报告图片转化pdf文件失败");
            result.setState(0);
            result.setMessage("暂无获取到报告详情");
            return result;
        } catch (Exception e) {
            File file = new File(pdfFilePath);
            if (file.exists()) {
                log.info("生成失败，删除文件");
                file.delete();
            }
            log.info("请求pacs系统报告图片异常" + e.toString());
            result.setState(0);
            result.setMessage("暂无获取到报告详情");
            return result;
        }
    }

    private static boolean convert(URL url, String target) {
        Document document = new Document();
        //设置文档页边距
        document.setMargins(0, 0, 0, 0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            PdfWriter.getInstance(document, fos);
            //打开文档
            document.open();
            //获取图片的宽高
            Image image = Image.getInstance(url);
            //设置页面宽高与图片一致
            Rectangle rectangle = new Rectangle(image.getScaledWidth(), image.getScaledHeight());
            document.setPageSize(rectangle);
            //图片居中
            image.setAlignment(Image.ALIGN_CENTER);
            //新建一页添加图片
            document.newPage();
            document.add(image);
            return true;
        } catch (Exception ioe) {
            log.info("转化pdf异常" + ioe.toString());
        } finally {
            //关闭文档
            document.close();
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.info("转化pdf关流 finally 异常" + e.toString());
            }
        }
        return false;
    }
}
