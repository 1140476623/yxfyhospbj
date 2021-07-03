package sjjd.doc.line.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.Node;
import sjjd.doc.line.pojo.NodeExample;
import sjjd.doc.line.pojo.ResponseResult;
import sjjd.doc.line.service.IAdminService;
import sjjd.doc.line.service.INodeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/Node")
public class NodeController {
    @Resource
    private INodeService nodeService;

    @Resource
    private IAdminService adminService;

    @RequestMapping("/NodeList")
    @ResponseBody
    public ResponseResult<List<JSONObject>> NodeList(String roleid) {

        ResponseResult<List<JSONObject>> jsons = new ResponseResult<List<JSONObject>>();
        NodeExample getnodeAll = new NodeExample();
        getnodeAll.createCriteria().andIdIsNotNull();
        List<Node> nodes = nodeService.selectByExample(getnodeAll);
        List<Integer> roids = nodeService.getNodeId(Integer.parseInt(roleid));

        List<JSONObject> jsonobjs = new ArrayList<JSONObject>();
        for (Node node : nodes) {
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("id", node.getId());
            jsonobj.put("name", node.getNodeName());
            jsonobj.put("pId", node.getParentId());
            jsonobjs.add(jsonobj);
        }
        for (JSONObject jsonObject : jsonobjs) {
            for (Integer roid : roids) {
                if (roid.equals(jsonObject.get("id"))) {

                    jsonObject.put("checked", 1);
                }
            }
        }
        jsons.setState(1);
        jsons.setMessage("成功");
        jsons.setData(jsonobjs);
        return jsons;
    }

}
