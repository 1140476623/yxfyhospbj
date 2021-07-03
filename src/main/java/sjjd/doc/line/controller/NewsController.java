package sjjd.doc.line.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sjjd.doc.line.pojo.HospitalInfo;
import sjjd.doc.line.pojo.News;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.pojo.TableResponse;
import sjjd.doc.line.service.IHospitalInfoService;
import sjjd.doc.line.service.INewsService;
import sjjd.doc.line.util.DateUtils;
import sjjd.doc.line.util.FileUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新闻动态健康宣教后台发布
 *
 * @author by xyw
 * @date 2021/3/23.
 */

@Slf4j
@RequestMapping("/news")
@Controller
@ApiIgnore
public class NewsController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${web.upload-path}")
    private String paths;

    @Value("${server.servlet.context-path}")
    private String projectName;

    @Value("${web.server-ip}")
    private String serverIp;

    @Resource
    private INewsService newsService;

    @Resource
    private IHospitalInfoService hospitalInfoService;


    //新闻动态健康宣教后台发布页面
    @RequestMapping("/showNews")
    public String news(ModelMap map) {
        map.addAttribute("pagelogo", "newsshowNews");
        map.addAttribute("day", "newsshowNews");
        return "newsPage";
    }


    @RequestMapping("/getNewsList")
    @ResponseBody
    public TableResponse<List<News>> getPayInfoList(Integer page, Integer limit, String adName) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(adName), "title", adName);
        queryWrapper.orderByDesc("top").orderByDesc("oper_time");
        TableResponse<List<News>> tableResponse = new TableResponse<>();
        Page<News> payInfoPage = new Page<>(page, limit);
        IPage<News> payInfoList = newsService.page(payInfoPage, queryWrapper);
        List<News> records = payInfoList.getRecords();
        tableResponse.setCode("0");
        tableResponse.setCount(payInfoList.getTotal() + "");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(records);
        return tableResponse;
    }


    /**
     * @description: 新闻信息更新和添加
     * @param: []
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/saveOrUpdateNews")
    @ResponseBody
    public ResponseResult<Void> saveNews(@RequestParam("title") String title,
                                         @RequestParam("newsId") String newsId,
                                         @RequestParam("content") String content) {
        ResponseResult<Void> result = new ResponseResult<>();
        log.info("新闻信息====:{}", StringUtils.isEmpty(newsId) ? "添加" : "更新");
        News news = new News();
        news.setOperTime(DateUtils.dateFormat.format(new Date()));
        if (StringUtils.isNotEmpty(newsId)) {
            news.setId(Integer.parseInt(newsId));
        }
        news.setOperTime(DateUtils.dateFormat.format(new Date()));
        news.setTitle(title);
        news.setContent(content);
        news.setOperUser(stringRedisTemplate.opsForValue().get("operName"));
//        if (!file.isEmpty()) {
//            File files = new File(paths + "titleImg");
//            FileUtil.fileupload(file.getBytes(), files.getPath() + "/", file.getOriginalFilename());
//            news.setImage(projectName + "/titleImg/" + file.getOriginalFilename());
//        }
        newsService.saveOrUpdate(news);
        result.setState(200);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * @description:
     * @param: []
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/delNews")
    @ResponseBody
    public ResponseResult<Void> delNews(Integer id) {
        ResponseResult<Void> result = new ResponseResult<>();
        log.info("删除新闻id:{}", id);
        if (StringUtils.isEmpty(String.valueOf(id))) {
            result.setState(0);
            result.setMessage("操作失败");
            return result;
        }
        newsService.removeById(id);
        result.setState(200);
        result.setMessage("操作成功");
        return result;
    }


    /**
     * @description: 是否置顶
     * @param: []
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public ResponseResult<Void> updateState(Integer id, String state, String top) {
        ResponseResult<Void> result = new ResponseResult<>();
        News news = newsService.getById(id);
        if (StringUtils.isEmpty(state)) {
            news.setTop(top);
            newsService.updateById(news);
            result.setState(200);
            result.setMessage("操作成功");
            return result;
        }
        news.setState(state);
        newsService.updateById(news);
        result.setState(200);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * @description: 富文本框上传图片
     * @param: [images]
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/image")
    @ResponseBody
    public JSONObject img(@RequestParam("images") List<MultipartFile> images) throws IOException {
        File file = new File(paths + "/news");
        JSONObject result = new JSONObject();
        List<JSONObject> list = new ArrayList<>();
        if (images.size() > 0) {
            for (MultipartFile muFile : images) {
                System.out.println(muFile.getOriginalFilename());
                FileUtil.fileupload(muFile.getBytes(), file.getPath() + "/", muFile.getOriginalFilename());
                File file1 = new File(file.getPath() + "/" + muFile.getOriginalFilename());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("alt", muFile.getOriginalFilename());
                jsonObject.put("url", serverIp + "/news/" + file1.getName());
                jsonObject.put("href", "");
                list.add(jsonObject);
            }
        }
        result.put("errno", 0);
        result.put("data", list);
        return result;
    }

    /**
     * @description: 富文本框上传视频
     * @param: [videos]
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/video")
    @ResponseBody
    public JSONObject voice(@RequestParam("videos") List<MultipartFile> videos) throws IOException {
        File file = new File(paths + "/news");
        JSONObject result = new JSONObject();
        List<JSONObject> list = new ArrayList<>();
        if (videos.size() > 0) {
            for (MultipartFile muFile : videos) {
                FileUtil.fileupload(muFile.getBytes(), file.getPath() + "/", muFile.getOriginalFilename());
                File file1 = new File(file.getPath() + "/" + muFile.getOriginalFilename());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("alt", muFile.getOriginalFilename());
                jsonObject.put("url", serverIp + "/news/" + file1.getName());
                jsonObject.put("href", "");
                list.add(jsonObject);
            }
        }
        result.put("errno", 0);
        result.put("data", list);
        return result;
    }

    //医院介绍信息后台页面
    @RequestMapping("/showHospital")
    public String showHospital(ModelMap map) {
        map.addAttribute("pagelogo", "newsshowHospital");
        return "hospitalPage";
    }

    /**
     * @description: 获取医院介绍，体检介绍，挂号须知，体检须知列表
     * @param: [page, limit]
     * @author: xyw
     * @date: 2021/3/24
     */
    @RequestMapping("/getHospitalInfo")
    @ResponseBody
    public TableResponse<List<HospitalInfo>> getHospitalInfo(Integer page, Integer limit) {
        TableResponse<List<HospitalInfo>> tableResponse = new TableResponse<>();
        Page<HospitalInfo> hospitalInfo = new Page<>(page, limit);
        IPage<HospitalInfo> hospitalInfoList = hospitalInfoService.page(hospitalInfo);
        List<HospitalInfo> list = hospitalInfoList.getRecords();
        tableResponse.setCode("0");
        tableResponse.setCount(hospitalInfoList.getTotal() + "");
        tableResponse.setMsg("操作成功");
        tableResponse.setData(list);
        return tableResponse;
    }

    /**
     * @description: 医院介绍，体检介绍，挂号须知，体检须知 信息修改
     * @param: []
     * @author: xyw
     * @date: 2021/3/23
     */
    @RequestMapping("/updateHosp")
    @ResponseBody
    public ResponseResult<Void> updateHosp(@RequestParam("title") String title,
                                           @RequestParam("hospId") String hospId,
                                           @RequestParam("content") String content) {
        ResponseResult<Void> result = new ResponseResult<>();
        HospitalInfo info = hospitalInfoService.getById(hospId);
        info.setTitle(title);
        info.setSubTitle(title);
        info.setContent(content);
        info.setOperTime(DateUtils.dateFormat.format(new Date()));
        info.setOperUser(stringRedisTemplate.opsForValue().get("operName"));
        boolean b = hospitalInfoService.updateById(info);
        if (b) {
            result.setState(200);
            result.setMessage("信息修改成功");
            return result;
        }
        result.setState(0);
        result.setMessage("信息修改成功");
        return result;
    }


}
