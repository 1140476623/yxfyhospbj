package sjjd.doc.line.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.*;
import sjjd.doc.line.service.IPermissionNodeService;
import sjjd.doc.line.service.IRoleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionNodeService permissionNodeService;

    @RequestMapping("/roleList")
    @ResponseBody
    public TableResponse<List<Role>> RoleList(Integer page, Integer limit) {
        TableResponse<List<Role>> table = new TableResponse<List<Role>>();

        List<Role> role = roleService.getRoleAll(page, limit);
        table.setCode("0");
        table.setCount(roleService.countByExample(null) != 0 ? roleService.countByExample(null) + "" : "0");
        table.setMsg("成功");
        table.setData(role);
        return table;
    }

    //添加角色
    @RequestMapping("/addRole")
    @ResponseBody
    public ResponseResult<Void> addRoles(Role role) {
        //System.out.println(role);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        Role roles = new Role();
        roles.setRoExplan(role.getRoExplan());
        roles.setRoleName(role.getRoleName());
        roles.setRoUpper(role.getRoUpper());
        try {
            roleService.insertSelective(roles);
            rr.setState(1);
            rr.setMessage("添加成功");
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("添加失败");
        }
        return rr;
    }

    //重新分配权限
    @RequestMapping("/addpermission")
    @ResponseBody
    public ResponseResult<Void> addpermission(String roleid, String nodeId) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        Integer rid = Integer.parseInt(roleid);
        String[] nodeIds = nodeId.split(",");
        List<Integer> IntegernId = new ArrayList<Integer>();
        for (String noId : nodeIds) {
            IntegernId.add(Integer.parseInt(noId));
        }
        PermissionNodeExample delPer = new PermissionNodeExample();
        delPer.createCriteria().andRoleIdEqualTo(Integer.parseInt(roleid));
        permissionNodeService.deleteByExample(delPer);

        for (Integer integer : IntegernId) {
            PermissionNode per = new PermissionNode();
            per.setNodeId(integer);
            per.setRoleId(rid);
            permissionNodeService.insertSelective(per);
        }
        rr.setState(1);
        rr.setMessage("成功");
        return rr;
    }

    @RequestMapping("/showRole")
    public String showrole(ModelMap map) {
        map.addAttribute("pagelogo", "roleshowRole");
        return "rolePage";
    }

    @RequestMapping("/roleTypeList")
    @ResponseBody
    public ResponseResult<List<Role>> roleTypeList() {
        ResponseResult<List<Role>> rr = new ResponseResult<List<Role>>();
        try {
            RoleExample getRole = new RoleExample();
            getRole.createCriteria().andRoUpperEqualTo("0");
            List<Role> role = roleService.selectByExample(getRole);
            rr.setState(1);
            rr.setMessage("获取成功");
            rr.setData(role);
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("获取失败");
        }
        return rr;
    }


}

