package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.AdminMapper;
import sjjd.doc.line.pojo.Admin;
import sjjd.doc.line.pojo.AdminExample;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdminService implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public long countByExample(AdminExample example) {
        // TODO Auto-generated method stub
        return adminMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AdminExample example) {
        // TODO Auto-generated method stub
        return adminMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admin record) {
        // TODO Auto-generated method stub
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        // TODO Auto-generated method stub
        return adminMapper.insertSelective(record);
    }

    @Override
    public List<Admin> selectByExample(AdminExample example) {
        // TODO Auto-generated method stub
        return adminMapper.selectByExample(example);
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Admin record, AdminExample example) {
        // TODO Auto-generated method stub
        return adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Admin record, AdminExample example) {
        // TODO Auto-generated method stub
        return adminMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        // TODO Auto-generated method stub
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        // TODO Auto-generated method stub
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Admin> getAdminrole(Integer page, Integer limit, String adName) {
        Integer currentpage = (page - 1) * limit;
        return adminMapper.getAdminrole(currentpage, limit, adName);
    }

    @Override
    public void modifiPassword(String password, Integer id) {

        adminMapper.updatePassword(password, id);
    }

}
