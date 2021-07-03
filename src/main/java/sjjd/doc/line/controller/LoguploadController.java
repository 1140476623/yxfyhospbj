package sjjd.doc.line.controller;

import io.netty.channel.socket.nio.NioSocketChannel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IFacilityService;
import sjjd.doc.line.util.BinUtil;
import sjjd.doc.line.util.FileUtil;
import sjjd.doc.line.util.NettySocketHolder;
import sjjd.doc.line.util.SocketByteSend;
import sjjd.doc.line.websocket.WebSocketServer;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "日志", tags = "日志接口")
@RequestMapping("/upload")
@RestController
@Slf4j
public class LoguploadController {

    @Value("${web.upload-path}")
    private String paths;

    @Autowired
    private IFacilityService facilityService;


    @ApiOperation(value = "日志上传", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件", dataType = "file"),
            @ApiImplicitParam(name = "macid", value = "macid", dataType = "String"),
            @ApiImplicitParam(name = "sessionId", value = "sessionId", dataType = "String")
    })
    @RequestMapping("/logs")
    public ResponseResult<Void> logs(@RequestParam("files") MultipartFile[] video
            , @RequestParam("macId") String macid
            , @RequestParam("sessionId") String sessionId) throws IOException {
        ResponseResult<Void> result = new ResponseResult<Void>();
        String macstr = macid.replaceAll(":", "").trim();
        File file = new File(paths + "logs/" + macstr);
        if (!file.exists()) {
            file.mkdirs();
        }
        //删除此目录下所有文件及文件夹
        FileUtil.deleteFile(file);
        JSONObject jsonObjects = new JSONObject();
        jsonObjects.put("type", "logs");
        List<JSONObject> str = new ArrayList<JSONObject>();
        if (video.length >= 1) {
            for (MultipartFile muFile : video) {
                if ("setting.txt".equals(muFile.getOriginalFilename())) {
                    continue;
                }
                FileUtil.fileupload(muFile.getBytes(), file.getPath() + "/", muFile.getOriginalFilename());
                File file1 = new File(paths + "logs/" + macstr + "/" + muFile.getOriginalFilename());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", muFile.getOriginalFilename());
                jsonObject.put("link", BinUtil.fileToBinStr(file1));
                str.add(jsonObject);
            }
        }
        jsonObjects.put("data", str);
        WebSocketServer.sendInfo(jsonObjects.toString(), sessionId);
        result.setState(1);
        result.setMessage("操作成功");
        return result;
    }

    @ApiIgnore
    @RequestMapping("/uploadLog")
    public ResponseResult<Void> uploadLog(Integer fid, String sessionId) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            Facility fac = facilityService.selectByPrimaryKey(fid);
            if (!fac.getFacState().equals("1")) {
                rr.setState(0);
                rr.setMessage("未上线设备不能获取日志");
                return rr;
            }
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
                jsonobj.put("type", "logs");
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

}
