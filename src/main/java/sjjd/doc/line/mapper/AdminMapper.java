package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.Admin;
import sjjd.doc.line.pojo.AdminExample;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAdminrole(@Param("currentpage") Integer currentpage, @Param("perpage") Integer perpage, @Param("adName") String adName);

    //修改密码
    void updatePassword(@Param("password") String password, @Param("id") Integer id);
}