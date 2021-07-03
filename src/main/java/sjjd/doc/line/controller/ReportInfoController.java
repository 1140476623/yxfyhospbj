package sjjd.doc.line.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.controller.endDocking.report.RespPdfUrl;
import sjjd.doc.line.pojo.ReportInfo;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.pojo.TableResponse;
import sjjd.doc.line.service.IReportInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 后台报告页面展示
 * @param:
 * @author: xyw
 * @date: 2021/5/8
 */
@Controller
@RequestMapping("/reportInfo")
@Slf4j
public class ReportInfoController {

    @Resource
    private IReportInfoService reportInfoService;

    @Value("${web.server-ip}")
    private String ip;


    //报告记录页面
    @RequestMapping("/showReportInfo")
    public String showPayInfo(ModelMap map) {
        map.addAttribute("pagelogo", "reportInfoshowReportInfo");
        return "reportInfoPage";
    }

    @RequestMapping("/reportInfoList")
    @ResponseBody
    public TableResponse<List<ReportInfo>> reportInfoList(
            Integer page, Integer limit, String adName) {
        QueryWrapper<ReportInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(adName)) {
            queryWrapper.and(wrapper -> wrapper
                    .like("patient_name", adName)
                    .or()
                    .like("doc_name", adName));
        }
        queryWrapper.orderByDesc("report_time");
        TableResponse<List<ReportInfo>> tableResponse = new TableResponse<>();
        Page<ReportInfo> payInfoPage = new Page<>(page, limit);
        IPage<ReportInfo> payInfoList = reportInfoService.page(payInfoPage, queryWrapper);
        tableResponse.setCode("0");
        tableResponse.setCount(payInfoList.getTotal() + "");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(payInfoList.getRecords());
        return tableResponse;
    }

    @RequestMapping("/getReportPdf")
    @ResponseBody
    public ResponseResult<RespPdfUrl> getReportPdf(String id) {
        ResponseResult<RespPdfUrl> result = new ResponseResult<>();
        ReportInfo reportInfo = reportInfoService.getById(id);
        if (reportInfo != null && StringUtils.isNotEmpty(reportInfo.getReportUrl())) {
            RespPdfUrl pdfUrl = new RespPdfUrl();
            pdfUrl.setPdfUrl(ip + reportInfo.getReportUrl());
            result.setState(200);
            result.setMessage("操作成功");
            result.setData(pdfUrl);
            return result;
        }
        result.setState(0);
        result.setMessage("当前暂无报告详情");
        return result;
    }

}
