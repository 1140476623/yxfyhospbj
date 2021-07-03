package sjjd.doc.line.service;


import sjjd.doc.line.pojo.Node;
import sjjd.doc.line.pojo.NodeExample;

import java.util.List;


public interface INodeService {
    long countByExample(NodeExample example);

    int deleteByExample(NodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    List<Node> selectByExample(NodeExample example);

    Node selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Node record, NodeExample example);

    int updateByExample(Node record, NodeExample example);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<Integer> getNodeId(Integer rid);
}
