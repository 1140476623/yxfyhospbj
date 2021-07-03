package sjjd.doc.line.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sjjd.doc.line.pojo.*;
import sjjd.doc.line.service.IDictionariesService;
import sjjd.doc.line.service.IFacRoomService;
import sjjd.doc.line.service.IFacilityService;
import sjjd.doc.line.service.IPrintInfoService;
import sjjd.doc.line.util.*;
import sjjd.doc.line.websocket.WebSocketServer;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/facility")
@Controller
public class FacilityController {

    @Resource
    private IFacilityService facilityService;

    @Value("${web.upload-path}")
    private String paths;

    @Value("${web.server-ip}")
    private String ipUrl;

    @Resource
    private IFacRoomService facRoomService;


    @Resource
    private IPrintInfoService printInfoService;

    @Resource
    private IDictionariesService dictionariesService;


    //设备列表
    @ApiIgnore
    @RequestMapping("/showFacility")
    public String showFacility(ModelMap map) {
        map.addAttribute("pagelogo", "facilityshowFacility");
        return "facilityPage";
    }


    //门牌屏列表
    @ApiIgnore
    @RequestMapping("/showdoorplate")
    public String showdoorplate(ModelMap map) {
        map.addAttribute("pagelogo", "facilityshowdoorplate");
        return "doorplatePage";
    }

    //签到机列表
    @ApiIgnore
    @RequestMapping("/showother")
    public String showother(ModelMap map) {
        map.addAttribute("pagelogo", "facilityshowother");
        return "otherPage";
    }

    //呼叫器列表
    @ApiIgnore
    @RequestMapping("/showocall")
    public String showcall(ModelMap map) {
        map.addAttribute("pagelogo", "facilityshowocall");
        return "callPage";
    }

    @ApiIgnore
    @RequestMapping("/facilityList")
    @ResponseBody
    public TableResponse<List<Facility>> docList(Integer page, Integer limit, String facDid, String register, String state, String type, HttpSession session) {

        TableResponse<List<Facility>> table = new TableResponse<List<Facility>>();
        FacilityExample facilityExample = new FacilityExample();
        FacilityExample.Criteria cre = facilityExample.createCriteria();

        List<String> orgCode = new ArrayList<String>();
        String count = facilityService.selectFacnum(facDid, register, state, type, orgCode);

        List<Facility> facilities = facilityService.selectFacList(page, limit, facDid, register, state, type, orgCode);
        if (!facilities.isEmpty()) {
            for (Facility facility : facilities) {
                QueryWrapper<PrintInfo> queryWrapper1 = new QueryWrapper<>();
                String dentity = facility.getFacMacid();
                queryWrapper1.eq(StringUtils.isNotEmpty(dentity), "terminal_mac", dentity);
                List<PrintInfo> list1 = printInfoService.list(queryWrapper1);
                for (PrintInfo printInfo : list1) {
                    Dictionaries idTypeCode = dictionariesService.getOne(new QueryWrapper<Dictionaries>()
                            .eq("type", "11")
                            .eq(printInfo.getPrintType() != null, "code", printInfo.getPrintType()));
                    if (idTypeCode != null) {
                        printInfo.setPrintType(idTypeCode.getNames());
                    }
                }
                facility.setPrintInfo(list1);
            }

        }
        table.setCode("0");
        table.setCount(count);
        table.setData(facilities);
        table.setMsg("成功");

        return table;
    }

    @ApiIgnore
    @RequestMapping("/exportFile")
    @ResponseBody
    public ResponseResult<List<String>> exportFile(Integer[] id) {

        ResponseResult<List<String>> rr = new ResponseResult<List<String>>();
        List<String> list = new ArrayList<String>();
        try {

            File jsonFile = new File(paths + "register/" + "entryptCode");
            if (jsonFile.exists()) {

                jsonFile.delete();//删除文件
            }

            FacilityExample getfac = new FacilityExample();
            List<Object> imgtextList = new ArrayList<Object>();

            for (Integer integer : id) {


                Facility facs = facilityService.selectByPrimaryKey(integer);
                Map<String, Object> imgtext = new HashMap<String, Object>();
                if (!jsonFile.exists() && !jsonFile.isDirectory()) {
                    imgtext.put("terminal", facs.getFacEncrypt());
                    imgtext.put("mac", facs.getFacMacid());
                    imgtextList.add(imgtext);
                }
            }
            JSONArray jsObj = JSONArray.fromObject(imgtextList);
            String jsonString1 = jsObj.toString();
            CreateFileUtil.createJsonFile(jsonString1, paths + "register/", "entryptCode");
            list.add(ipUrl + "/register/" + "entryptCode");
            rr.setState(1);
            rr.setMessage("获取成功");
            rr.setData(list);

        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("获取失败");
        }
        return rr;

    }

    @ApiIgnore
    @RequestMapping("/leadFile")
    @ResponseBody
    public ResponseResult<Void> leadFile(MultipartFile file) throws IOException {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        FileUtil.fileupload(file.getBytes(), paths, file.getOriginalFilename());
        File files = new File(paths + file.getOriginalFilename());
        String strsa = FileUtils.readFileToString(files, "UTF-8");
//        try {
        List<Map> maps = (List) JSONArray.fromObject(strsa);
        for (Map map : maps) {
            if (map.get("mac") == null) {
                rr.setState(0);
                rr.setMessage("文件格式错误");
                return rr;
            }
            FacilityExample getfac = new FacilityExample();
            getfac.createCriteria().andFacMacidEqualTo((String) map.get("mac"));
            Facility fac = new Facility();
            fac.setFacMacid(map.get("mac") + "");
            fac.setFacDecode(map.get("terminal") + "");
            facilityService.updateByExampleSelective(fac, getfac);

            FacilityExample facilityExample = new FacilityExample();
            facilityExample.createCriteria().andFacMacidEqualTo(map.get("mac") + "");
            List<Facility> facilities1 = facilityService.selectByExample(facilityExample);
            if (!facilities1.isEmpty() && facilities1.size() > 0) {
                String clientId = facilities1.get(0).getFacClientid();
                if (clientId != null && !clientId.isEmpty()) {
                    NioSocketChannel nio = NettySocketHolder.get(clientId);
                    JSONObject jsonobj = new JSONObject();
                    jsonobj.put("register_code", map.get("terminal") + "");
                    jsonobj.put("type", "register");
                    SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());

                }
            }

        }
        rr.setState(1);
        rr.setMessage("修改成功");
//        } catch (Exception e) {
//            rr.setState(0);
//            rr.setMessage("文件格式错误");
//        }
        return rr;
    }


    @ApiIgnore
    @RequestMapping("/facRelate")
    @ResponseBody
    public ResponseResult<Void> facRelate(String id, String roid) {

        ResponseResult<Void> rr = new ResponseResult<Void>();
        FacRoomExample facRoomExample = new FacRoomExample();
        facRoomExample.createCriteria().andFidEqualTo(id);
        facRoomService.deleteByExample(facRoomExample);
        FacRoom facRoom = new FacRoom();
        facRoom.setFid(id);
        facRoom.setRid(roid);
        facRoomService.insertSelective(facRoom);
        rr.setState(1);
        rr.setMessage("操作成功");
        return rr;
    }


    @RequestMapping("/addfacility")
    @ResponseBody
    @ApiOperation(value = "设备信息录入", httpMethod = "POST")
    public Object addfacility(Facility facility) {

        ResponseResult<Void> rr = new ResponseResult<Void>();
        facility.setFacState("1");
        JSONObject json = new JSONObject();
        try {
            if (facility.getFacMacid() == null) {
                rr.setState(0);
                rr.setMessage("注册失败,macId不能为空");
                return rr;
            }
            FacilityExample getMacID = new FacilityExample();
            getMacID.createCriteria().andFacMacidEqualTo(facility.getFacMacid());
            List<Facility> facilitys = facilityService.selectByExample(getMacID);
            if (!facilitys.isEmpty() || facilitys.size() > 0) {
                if (facility.getFacEncrypt().equals("")) {
                    facility.setFacEncrypt(null);
                }
                facilityService.updateByExampleSelective(facility, getMacID);
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("state", 1);
                jsonobj.put("message", "信息修改成功");
                jsonobj.put("facDecode", facilitys.get(0).getFacDecode());
                jsonobj.put("facId", facilitys.get(0).getId());
                return jsonobj;
            }

            facilityService.insertSelective(facility);
            facilitys = facilityService.selectByExample(getMacID);
            json.put("state", 1);
            json.put("message", "信息修改成功");
            json.put("facDecode", facilitys.get(0).getFacDecode());
            json.put("facId", facility.getId());
        } catch (Exception e) {
            json.put("state", 0);
            json.put("message", "注册失败");
        }
        return json;
    }

    @ApiIgnore
    @RequestMapping("/sendScreen")
    @ResponseBody
    public ResponseResult<Void> sendScreen(Integer fid, String sessionId) {

        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            FacilityExample getClientId = new FacilityExample();
            Facility fac = facilityService.selectByPrimaryKey(fid);
            if (!fac.getFacState().equals("1")) {
                rr.setState(0);
                rr.setMessage("未上线设备不能截屏");
                return rr;
            }
//            if (!fac.getFacRegister().equals("1")) {
//                rr.setState(0);
//                rr.setMessage("未注册设备不能截屏");
//                return rr;
//            }
            if (!fac.getFacManage().equals("1")) {
                rr.setState(0);
                rr.setMessage("该设备未启用");
                return rr;
            }
            String clientId = fac.getFacClientid();
            if (clientId != null && !clientId.isEmpty()) {
                NioSocketChannel nio = NettySocketHolder.get(clientId);
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id", clientId);
                jsonobj.put("type", "screen");
                jsonobj.put("sessionId", sessionId);
                SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());
            }
            rr.setState(1);
            rr.setMessage("操作成功");
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("操作失败");
        }
        return rr;
    }


    @ApiIgnore
    @RequestMapping("/screenShot")
    @ResponseBody
    public ResponseResult<Void> screenShot(String beaseStr, String sessionId, String macId) throws IOException {

        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            FacilityExample getfac = new FacilityExample();
            getfac.createCriteria().andFacMacidEqualTo(macId);
            List<Facility> facili = facilityService.selectByExample(getfac);

            if (CollectionUtils.isNotEmpty(facili)) {
                Facility facs = facili.get(0);
                String oldLink = null;

                if (StringUtils.isNotEmpty(facs.getShotLink())) {
                    oldLink = facs.getShotLink().substring(facs.getShotLink().lastIndexOf("/") + 1);
                }

                File file = new File(paths + "screen/" + oldLink);
                if (file.exists()) {
                    file.delete();
                }
                byte[] b1 = Bease64ToImage.base64topng(beaseStr);
                String imgFilePath1 = Bease64ToImage.filePath(paths + "screen", b1);
                String imgurl = imgFilePath1.substring(imgFilePath1.lastIndexOf("/"));
                WebSocketServer.sendInfo(ipUrl + "/screen" + imgurl, sessionId);
                Facility upfac = new Facility();
                upfac.setId(facs.getId());
                upfac.setShotLink("/SjjdfyHosLine/screen" + imgurl);
                facilityService.updateByPrimaryKeySelective(upfac);
                rr.setState(1);
                rr.setMessage("上传成功");
            } else {
                rr.setState(0);
                rr.setMessage("上传失败");
            }
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("上传失败");
        }
        return rr;

    }


    @ApiIgnore
    @RequestMapping("/restart")
    @ResponseBody
    public ResponseResult<Void> restart(Integer[] id) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        for (Integer integer : id) {
            Facility facility = facilityService.selectByPrimaryKey(integer);
            if (!facility.getFacState().equals("1")) {
                rr.setState(0);
                rr.setMessage("未上线设备不能操作");
                return rr;
            }
            if (!facility.getFacRegister().equals("1")) {
                rr.setState(0);
                rr.setMessage("未注册设备不能操作");
                return rr;
            }
            if (!facility.getFacManage().equals("1")) {
                rr.setState(0);
                rr.setMessage("该设备未启用");
                return rr;
            }
            String clientId = facility.getFacClientid();
            if (clientId != null && !clientId.isEmpty()) {
                NioSocketChannel nio = NettySocketHolder.get(clientId);
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id", clientId);
                jsonobj.put("type", "restart");
                SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());
            }
        }
        rr.setState(1);
        rr.setMessage("操作成功");
        return rr;
    }

    @ApiIgnore
    @RequestMapping("/timing")
    @ResponseBody
    public ResponseResult<Void> timing(@RequestParam(value = "id[]") Integer[] id, String starTime, String endTime) throws ParseException {
        ResponseResult<Void> rr = new ResponseResult<Void>();

        for (Integer integer : id) {
            Facility facility = facilityService.selectByPrimaryKey(integer);
            if (!facility.getFacState().equals("1")) {
                rr.setState(0);
                rr.setMessage("未上线设备不能操作");
                return rr;
            }
            if (!facility.getFacRegister().equals("1")) {
                rr.setState(0);
                rr.setMessage("未注册设备不能操作");
                return rr;
            }
            if (!facility.getFacManage().equals("1")) {
                rr.setState(0);
                rr.setMessage("该设备未启用");
                return rr;
            }

            Facility facilitys = new Facility();
            facilitys.setUptime(starTime);
            facilitys.setOfftime(endTime);
            facilitys.setId(integer);
            facilityService.updateByPrimaryKeySelective(facilitys);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("starTime", starTime);
            jsonObject.put("endTime", endTime);

            String clientId = facility.getFacClientid();
            if (clientId != null && !clientId.isEmpty()) {
                NioSocketChannel nio = NettySocketHolder.get(clientId);
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id", clientId);
                jsonobj.put("type", "timing");
                jsonobj.put("data", jsonObject);
                SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());
            }


        }
        rr.setState(1);
        rr.setMessage("操作成功");
        return rr;
    }


    @ApiIgnore
    @RequestMapping("/facVoice")
    @ResponseBody
    public ResponseResult<Voice> facVoice(@RequestParam(value = "id[]") Integer[] id, String voice) {

        ResponseResult<Voice> rr = new ResponseResult<Voice>();
        for (Integer integer : id) {
            Facility facility = facilityService.selectByPrimaryKey(integer);
            if (!facility.getFacState().equals("1")) {
                rr.setState(0);
                rr.setMessage("未上线设备不能操作");
                return rr;
            }
            if (!facility.getFacRegister().equals("1")) {
                rr.setState(0);
                rr.setMessage("未注册设备不能操作");
                return rr;
            }
            if (!facility.getFacManage().equals("1")) {
                rr.setState(0);
                rr.setMessage("该设备未启用");
                return rr;
            }


            Facility facilitys = new Facility();
            facilitys.setId(integer);
            facilitys.setVoiceSize(voice);
            facilityService.updateByPrimaryKeySelective(facilitys);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("size", voice);


            String clientId = facility.getFacClientid();
            if (clientId != null && !clientId.isEmpty()) {
                NioSocketChannel nio = NettySocketHolder.get(clientId);
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id", clientId);
                jsonobj.put("type", "voiceSize");
                jsonobj.put("data", jsonObject);
                SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());

            }
        }

        rr.setState(1);
        rr.setMessage("操作成功");
        return rr;

    }

    @ApiIgnore
    @RequestMapping("/modiflyManage")
    @ResponseBody
    public ResponseResult<Void> modiflyMannage(String facManage, Integer id) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            Facility facility = new Facility();
            facility.setId(id);
            facility.setFacManage(facManage);
            facilityService.updateByPrimaryKeySelective(facility);
            rr.setState(1);
            rr.setMessage("修改成功");
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("修改失败");
        }
        return rr;
    }

    @ApiIgnore
    @RequestMapping("/voiceManage")
    @ResponseBody
    public ResponseResult<Void> voiceManage(String id, String facManage) {

        ResponseResult<Void> rr = new ResponseResult<Void>();
        Facility facility = facilityService.selectByPrimaryKey(Integer.parseInt(id));
        if (!facility.getFacState().equals("1")) {
            rr.setState(0);
            rr.setMessage("未上线设备不能操作");
            return rr;
        }
        if (!facility.getFacRegister().equals("1")) {
            rr.setState(0);
            rr.setMessage("未注册设备不能操作");
            return rr;
        }
        if (!facility.getFacManage().equals("1")) {
            rr.setState(0);
            rr.setMessage("该设备未启用");
            return rr;
        }
        Facility facilitys = new Facility();
        facilitys.setId(Integer.parseInt(id));
        facilitys.setIsVoice(facManage);
        facilityService.updateByPrimaryKeySelective(facilitys);
        String clientId = facility.getFacClientid();
        if (clientId != null && !clientId.isEmpty()) {
            NioSocketChannel nio = NettySocketHolder.get(clientId);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("id", clientId);
            jsonobj.put("type", "voiceSwitch");
            jsonobj.put("flag", facManage);
            SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());
        }
        rr.setState(1);
        rr.setMessage("操作成功");
        return rr;
    }


    @ApiIgnore
    @RequestMapping("/upgrade")
    @ResponseBody
    public ResponseResult<Void> upgrade(@RequestParam("files") MultipartFile file, String idz) throws IOException {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            String[] id = idz.split(",");
            if (file != null && !file.isEmpty()) {
                File files = new File(paths + "update/");
                File[] fi = files.listFiles();
                for (File file2 : fi) {
                    if (file2.getName().equals(file.getOriginalFilename())) {
                        file2.delete();
                    }
                }
                FileUtil.fileupload(file.getBytes(), paths + "update/", file.getOriginalFilename());
                for (String ids : id) {
                    Facility fac = facilityService.selectByPrimaryKey(Integer.parseInt(ids));
                    String clientId = fac.getFacClientid();

                    JSONObject jsonobj = new JSONObject();
                    jsonobj.put("type", "upgrade");
                    jsonobj.put("id", clientId);
                    jsonobj.put("link", "/yxfyhospbj/update/" + file.getOriginalFilename());
                    if (clientId != null && !clientId.isEmpty()) {
                        NioSocketChannel nio = NettySocketHolder.get(clientId);
                        SocketByteSend.NettySendSocket(nio, JSONObject.fromObject(jsonobj).toString());
                    }

                }
                rr.setState(1);
                rr.setMessage("操作成功");
                return rr;
            } else {
                rr.setState(0);
                rr.setMessage("必须选择更新包");
                return rr;
            }
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("操作失败");
        }
        return rr;
    }


    /**
     * 修改设备的放置位置
     */
    //综合屏关联列表
    @RequestMapping("/saveAddress")
    @ApiIgnore
    @ResponseBody
    public ResponseResult saveAddress(@RequestParam("terminaliDentity") String terminaliDentity,
                                      @RequestParam("newAddress") String newAddress) {
        ResponseResult result = new ResponseResult();
        FacilityExample facilityExample = new FacilityExample();
        facilityExample.createCriteria().andFacMacidEqualTo(terminaliDentity);
        Facility facility = new Facility();
        facility.setFacAddres(newAddress);
        Integer update = facilityService.updateByExampleSelective(facility, facilityExample);
        if (update.equals(1)) {
            result.setState(200);
            result.setMessage("保存位置成功");
            return result;
        }
        result.setState(0);
        result.setMessage("保存位置失败");
        return result;
    }


}
