package sjjd.doc.line.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjjd.doc.line.pojo.*;
import sjjd.doc.line.service.IAdminService;
import sjjd.doc.line.service.INodeService;
import sjjd.doc.line.service.IPermissionNodeService;
import sjjd.doc.line.service.IRoleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${web.server-ip}")
    private String ipUrl;

    @Value("${web.redirect.path}")
    private String redirectUrl;

    @Resource
    private IAdminService adminService;

    @Resource
    private INodeService nodeService;

    @Resource
    private IPermissionNodeService permissionNodeService;

    @Resource
    private IRoleService roleService;


    @RequestMapping("/showlogin")
    public String showlogin() {
        return "login";
    }

    //登录及验证
    @RequestMapping("/login")
    public String login(String adName, String adpassword, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        AdminExample getadmin = new AdminExample();
        getadmin.createCriteria().andAdNameEqualTo(adName).andIsDeleteEqualTo("1");
        List<Admin> admins = adminService.selectByExample(getadmin);

        if (adName == null) {
            model.addAttribute("message", "账号不能为空");
            return "login";
        }

        if (admins.isEmpty() || admins.size() < 0) {
            model.addAttribute("message", "用户名不存在");
            return "login";
        }


        Admin admin = admins.get(0);


        //一级节点
        List<Node> nodeparent = new ArrayList<Node>();

        //二级节点
        List<Node> nodeson = new ArrayList<Node>();

        String Md5Pas = DigestUtils.md5Hex(adpassword + "chumo028");
        if (Md5Pas.equals(admin.getAdPassword())) {
            model.addAttribute("message", "登录完成");
            PermissionNodeExample getNodeId = new PermissionNodeExample();
            getNodeId.createCriteria().andRoleIdEqualTo(admin.getrId());
            List<PermissionNode> nodes = permissionNodeService.selectByExample(getNodeId);
            List<Node> nodess = new ArrayList<Node>();
            if (!nodes.isEmpty() && nodes.size() >= 0) {
                for (PermissionNode permissionNode : nodes) {
                    Node node = nodeService.selectByPrimaryKey(permissionNode.getNodeId());
                    nodess.add(node);
                    if (node != null) {
                        if (node.getParentId().equals("0")) {
                            nodeparent.add(node);
                        } else {
                            nodeson.add(node);
                        }
                    }
                }
            }

            session.setAttribute("nodeparent", nodeparent);
            session.setAttribute("nodeson", nodeson);
            session.setAttribute("admin", admin);
            session.setAttribute("nodes", nodess);
            session.setAttribute("serverUrl", ipUrl);
            stringRedisTemplate.opsForValue().set("aId" + admin.getId(), session.getId());
            stringRedisTemplate.opsForValue().set("operName", adName);
            System.out.println("成功");
            return "homePage";
        } else {
            model.addAttribute("message", "密码错误");
            return "login";
        }


    }


    @RequestMapping("/loginmessage")
    public String showlogins(ModelMap map) {
        map.put("message", "用户已在异地登录");
        return "login";
    }


    //后台页面表格数据
    @RequestMapping("/adminList")
    @ResponseBody
    public TableResponse<List<Admin>> adminList(Integer page, Integer limit, String adName) {
        TableResponse<List<Admin>> table = new TableResponse<List<Admin>>();
        AdminExample getadmincount = new AdminExample();
        getadmincount.createCriteria().andIdNotEqualTo(1);

        if (adName != null) {
            getadmincount.createCriteria().andAdNameLike("%" + adName + "%");
        }

        List<Admin> admis = adminService.getAdminrole(page, limit, adName);


        table.setCode("0");
        table.setMsg("成功");
        table.setCount(adminService.countByExample(getadmincount) != 0 ? adminService.countByExample(getadmincount) + "" : "0");
        table.setData(admis);
        return table;
    }

    @RequestMapping("/showadmin")
    public String showadmin(ModelMap map) {
        map.addAttribute("pagelogo", "adminshowadmin");
        return "adminPage";
    }

    //后台退出
    @RequestMapping("/exit")
    public String adminexit(HttpSession session) {
        session.invalidate();
        return "redirect:" + redirectUrl + "/admin/showlogin";
    }


    //添加后台管理员
    @RequestMapping("/addAdmin")
    @ResponseBody
    public ResponseResult<Void> addAdmins(Admin adminss) {
        System.out.println(adminss);

        ResponseResult<Void> rr = new ResponseResult<Void>();
        AdminExample getadmin = new AdminExample();
        getadmin.createCriteria().andAdNameEqualTo(adminss.getAdName()).andIsDeleteEqualTo("1");
        List<Admin> admins = adminService.selectByExample(getadmin);
        if (adminss.getrId() == null) {
            rr.setState(0);
            rr.setMessage("必须选择角色");
            return rr;
        }
        if (!admins.isEmpty()) {
            rr.setState(0);
            rr.setMessage("用户名重复");
            return rr;
        }
        String Md5Pas = DigestUtils.md5Hex(adminss.getAdPassword() + "chumo028");
        adminss.setAdPassword(Md5Pas);
        adminService.insertSelective(adminss);
        rr.setState(1);
        rr.setMessage("添加成功");
        return rr;
    }


    @RequestMapping("/getRoleselect")
    @ResponseBody
    public ResponseResult<List<Role>> getRoleselect(HttpServletRequest request) {
        ResponseResult<List<Role>> rr = new ResponseResult<List<Role>>();
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        Role role = roleService.selectByPrimaryKey(admin.getrId());
        try {
            if (role.getRoUpper().equals("0")) {
                RoleExample getRole = new RoleExample();
                getRole.createCriteria().andIdNotEqualTo(1);
                List<Role> roles = roleService.selectByExample(getRole);
                rr.setState(1);
                rr.setMessage("获取成功");
                rr.setData(roles);
                return rr;
            }
            if (role.getRoUpper().equals("1")) {
                RoleExample getRole = new RoleExample();
                getRole.createCriteria().andIdNotEqualTo(1).andRoUpperNotEqualTo("0");
                List<Role> roles = roleService.selectByExample(getRole);
                rr.setState(1);
                rr.setMessage("获取成功");
                rr.setData(roles);
                return rr;
            }
        } catch (Exception e) {
            rr.setState(0);
            rr.setMessage("获取失败");
        }
        return rr;
    }

    @RequestMapping("/showuppass")
    public String showuppass(ModelMap map) {
        map.addAttribute("pagelogo", "adminshowuppass");
        return "updatepass";
    }

    //修改密码
    @RequestMapping("/modifiadmin")
    @ResponseBody
    public ResponseResult<Void> modifiadmin(String oldPassword, String newPassword, String repeatPassword, HttpSession session) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        Admin admin = (Admin) session.getAttribute("admin");
        Integer id = admin.getId();
        oldPassword = DigestUtils.md5Hex(oldPassword + "chumo028");
        newPassword = DigestUtils.md5Hex(newPassword + "chumo028");
        repeatPassword = DigestUtils.md5Hex(repeatPassword + "chumo028");
        if (!newPassword.equals(repeatPassword)) {
            rr.setState(0);
            rr.setMessage("两次密码不一样");
            return rr;
        }
        if (!(admin.getAdPassword()).equals(oldPassword)) {
            rr.setState(0);
            rr.setMessage("原密码输入不正确");
            return rr;
        }
        adminService.modifiPassword(newPassword, id);
        rr.setState(1);
        rr.setMessage("成功");
        return rr;
    }

    @RequestMapping("/delAdmin")
    @ResponseBody
    public ResponseResult<Void> delAdmin(String id) {
        ResponseResult<Void> responseResult = new ResponseResult<>();

        Admin byId = adminService.selectByPrimaryKey(Integer.parseInt(id));
        if (byId != null) {
            byId.setIsDelete("0");
            adminService.updateByPrimaryKey(byId);
        }
        responseResult.setState(1);
        responseResult.setMessage("操作成功");
        return responseResult;
    }

}
