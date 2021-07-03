package sjjd.doc.line.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.PayInfo;
import sjjd.doc.line.pojo.TableResponse;
import sjjd.doc.line.service.IDictionariesService;
import sjjd.doc.line.service.IPayInfoService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-18
 */
@Controller
@RequestMapping("/payInfo")
public class PayInfoController {


    @Autowired
    private IPayInfoService payInfoService;


    @Autowired
    private IDictionariesService dictionariesService;

    @RequestMapping("/getPayInfoList")
    @ResponseBody
    public TableResponse<List<PayInfo>> getPayInfoList(
            Integer page, Integer limit, String adName,
            String docDept, String type, String payStatus) {
        QueryWrapper<PayInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(docDept), "dept_name", docDept);
        queryWrapper.like(StringUtils.isNotBlank(type), "pay_type", type);
        queryWrapper.eq(StringUtils.isNotBlank(payStatus), "pay_status", payStatus);
        if (StringUtils.isNotBlank(adName)) {
            queryWrapper.and(wrapper -> wrapper
                    .like("resident_name", adName)
                    .or()
                    .like("doc_name", adName));
        }
        queryWrapper.orderByDesc("create_time");
        TableResponse<List<PayInfo>> tableResponse = new TableResponse<>();
        Page<PayInfo> payInfoPage = new Page<>(page, limit);
        IPage<PayInfo> payInfoList = payInfoService.page(payInfoPage, queryWrapper);
        tableResponse.setCode("0");
        tableResponse.setCount(payInfoList.getTotal() + "");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(payInfoList.getRecords());
        return tableResponse;
    }


    @ResponseBody
    @RequestMapping("/exportPayInfo")
    public TableResponse<List<PayInfo>> exportPayInfo(
            String adName, String docDept, String type, String payStatus) {
        TableResponse<List<PayInfo>> result = new TableResponse<>();
        QueryWrapper<PayInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(adName != null && !adName.isEmpty(), "resident_name", adName);
        queryWrapper.like(docDept != null && !docDept.isEmpty(), "dept_name", docDept);
        queryWrapper.like(type != null && !type.isEmpty(), "pay_type", type);
        queryWrapper.eq(payStatus != null && !payStatus.isEmpty(), "pay_status", payStatus);
        List<PayInfo> list = payInfoService.list(queryWrapper);

        result.setCode("200");
        result.setMsg("操作成功");
        result.setData(list);
        return result;
    }


    //缴费记录记录页面
    @RequestMapping("/showPayInfo")
    public String showPayInfo(ModelMap map) {
        map.addAttribute("pagelogo", "payInfoshowPayInfo");
        return "payInfoPage";
    }

}
