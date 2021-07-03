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
import sjjd.doc.line.pojo.RegisterInfo;
import sjjd.doc.line.pojo.TableResponse;
import sjjd.doc.line.service.IRegisterInfoService;

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
@RequestMapping("/regInfo")
public class RegisterInfoController {

    @Autowired
    private IRegisterInfoService registerInfoService;


    /**
     * 挂号记录管理
     *
     * @param page
     * @param limit
     * @param adName
     * @param docDept
     * @param docName
     * @param numCome
     * @return
     */
    @RequestMapping("/getRegRecordList")
    @ResponseBody
    public TableResponse<List<RegisterInfo>> getRegRecordList(
            Integer page, Integer limit, String adName, String docDept,
            String docName, String numCome) {
        QueryWrapper<RegisterInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(adName)) {
            queryWrapper.and(wrapper -> wrapper
                    .like("resident_name", adName)
                    .or()
                    .like("doc_name", adName));
        }
        queryWrapper.like(StringUtils.isNotEmpty(docDept), "dept_name", docDept);
        queryWrapper.eq(StringUtils.isNotEmpty(numCome), "num_come", numCome);
        queryWrapper.orderByDesc("register_date");
        Page<RegisterInfo> reportPage = new Page<>(page, limit);
        IPage<RegisterInfo> reportPageList = registerInfoService.page(reportPage, queryWrapper);
        TableResponse<List<RegisterInfo>> tableResponse = new TableResponse<>();
        tableResponse.setCode("0");
        tableResponse.setCount(reportPageList.getTotal() + "");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(reportPageList.getRecords());
        return tableResponse;
    }

    @RequestMapping("/exportRegRecord")
    @ResponseBody
    public TableResponse<List<RegisterInfo>> exportRegRecord(
            String adName, String docDept, String docName, String numCome) {
        QueryWrapper<RegisterInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(adName != null && !adName.isEmpty(), "resident_name", adName);
        queryWrapper.like(docDept != null && !docDept.isEmpty(), "dept_name", docDept);
        queryWrapper.like(docName != null && !docName.isEmpty(), "doc_name", docName);
        queryWrapper.eq(numCome != null && !numCome.isEmpty(), "num_come", numCome);
        List<RegisterInfo> list = registerInfoService.list(queryWrapper);
        TableResponse<List<RegisterInfo>> tableResponse = new TableResponse<>();
        tableResponse.setCode("200");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(list);
        return tableResponse;
    }

    //挂号记录页面
    @RequestMapping("/showRegRecord")
    public String showRegRecord(ModelMap map) {
        map.addAttribute("pagelogo", "regInfoshowRegRecord");
        return "regRecordPage";
    }

}
