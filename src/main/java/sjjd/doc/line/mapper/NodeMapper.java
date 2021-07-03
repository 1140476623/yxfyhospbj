package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.Node;
import sjjd.doc.line.pojo.NodeExample;

import java.util.List;

public interface NodeMapper {
    long countByExample(NodeExample example);

    int deleteByExample(NodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    List<Node> selectByExample(NodeExample example);

    Node selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByExample(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    //根据roleid取出nodeId
    List<Integer> selectnodeIdbyRId(Integer rid);
}