package sjjd.doc.line.service;


import sjjd.doc.line.pojo.Admin;
import sjjd.doc.line.pojo.AdminExample;

import java.util.List;

public interface IAdminService {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Admin record, AdminExample example);

    int updateByExample(Admin record, AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAdminrole(Integer page, Integer limit, String adName);

    void modifiPassword(String password, Integer id);
}
