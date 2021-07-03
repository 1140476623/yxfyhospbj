package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.Role;
import sjjd.doc.line.pojo.RoleExample;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //获取所有角色
    List<Role> selectRoleAll(@Param("currentpage") Integer currentpage, @Param("perpage") Integer perpage);
}