package sjjd.doc.line.service;


import sjjd.doc.line.pojo.Role;
import sjjd.doc.line.pojo.RoleExample;

import java.util.List;

public interface IRoleService {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Role record, RoleExample example);

    int updateByExample(Role record, RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRoleAll(Integer page, Integer limit);
}
