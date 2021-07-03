package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.PermissionNode;
import sjjd.doc.line.pojo.PermissionNodeExample;

import java.util.List;

public interface PermissionNodeMapper {
    long countByExample(PermissionNodeExample example);

    int deleteByExample(PermissionNodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionNode record);

    int insertSelective(PermissionNode record);

    List<PermissionNode> selectByExample(PermissionNodeExample example);

    PermissionNode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionNode record, @Param("example") PermissionNodeExample example);

    int updateByExample(@Param("record") PermissionNode record, @Param("example") PermissionNodeExample example);

    int updateByPrimaryKeySelective(PermissionNode record);

    int updateByPrimaryKey(PermissionNode record);

}