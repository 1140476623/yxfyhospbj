package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.RoleMapper;
import sjjd.doc.line.pojo.Role;
import sjjd.doc.line.pojo.RoleExample;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleService implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public long countByExample(RoleExample example) {

        return roleMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RoleExample example) {

        return roleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {

        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {

        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {

        return roleMapper.insertSelective(record);
    }

    @Override
    public List<Role> selectByExample(RoleExample example) {

        return roleMapper.selectByExample(example);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {

        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Role record, RoleExample example) {

        return roleMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Role record, RoleExample example) {

        return roleMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {

        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {

        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> getRoleAll(Integer page, Integer limit) {
        if (page == null && limit == null) {
            return roleMapper.selectRoleAll(null, null);
        }
        Integer currentpage = (page - 1) * limit;
        return roleMapper.selectRoleAll(currentpage, limit);
    }

}
