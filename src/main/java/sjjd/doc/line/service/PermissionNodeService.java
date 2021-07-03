package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.PermissionNodeMapper;
import sjjd.doc.line.pojo.PermissionNode;
import sjjd.doc.line.pojo.PermissionNodeExample;

import javax.annotation.Resource;
import java.util.List;


@Service
public class PermissionNodeService implements IPermissionNodeService {

    @Resource
    private PermissionNodeMapper permissionNodeMapper;

    @Override
    public long countByExample(PermissionNodeExample example) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PermissionNodeExample example) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PermissionNode record) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.insert(record);
    }

    @Override
    public int insertSelective(PermissionNode record) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.insertSelective(record);
    }

    @Override
    public List<PermissionNode> selectByExample(PermissionNodeExample example) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.selectByExample(example);
    }

    @Override
    public PermissionNode selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(PermissionNode record, PermissionNodeExample example) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(PermissionNode record, PermissionNodeExample example) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionNode record) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PermissionNode record) {
        // TODO Auto-generated method stub
        return permissionNodeMapper.updateByPrimaryKey(record);
    }

}
