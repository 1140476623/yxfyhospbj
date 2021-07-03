package sjjd.doc.line.service;


import sjjd.doc.line.pojo.PermissionNode;
import sjjd.doc.line.pojo.PermissionNodeExample;

import java.util.List;


public interface IPermissionNodeService {
    long countByExample(PermissionNodeExample example);

    int deleteByExample(PermissionNodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionNode record);

    int insertSelective(PermissionNode record);

    List<PermissionNode> selectByExample(PermissionNodeExample example);

    PermissionNode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(PermissionNode record, PermissionNodeExample example);

    int updateByExample(PermissionNode record, PermissionNodeExample example);

    int updateByPrimaryKeySelective(PermissionNode record);

    int updateByPrimaryKey(PermissionNode record);
}
