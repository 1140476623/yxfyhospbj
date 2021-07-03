package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.NodeMapper;
import sjjd.doc.line.pojo.Node;
import sjjd.doc.line.pojo.NodeExample;

import javax.annotation.Resource;
import java.util.List;


@Service
public class NodeService implements INodeService {

    @Resource
    private NodeMapper nodeMapper;

    @Override
    public long countByExample(NodeExample example) {
        // TODO Auto-generated method stub
        return nodeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(NodeExample example) {
        // TODO Auto-generated method stub
        return nodeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return nodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Node record) {
        // TODO Auto-generated method stub
        return nodeMapper.insert(record);
    }

    @Override
    public int insertSelective(Node record) {
        // TODO Auto-generated method stub
        return nodeMapper.insertSelective(record);
    }

    @Override
    public List<Node> selectByExample(NodeExample example) {
        // TODO Auto-generated method stub
        return nodeMapper.selectByExample(example);
    }

    @Override
    public Node selectByPrimaryKey(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        return node;
    }

    @Override
    public int updateByExampleSelective(Node record, NodeExample example) {
        // TODO Auto-generated method stub
        return nodeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Node record, NodeExample example) {
        // TODO Auto-generated method stub
        return nodeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Node record) {
        // TODO Auto-generated method stub
        return nodeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Node record) {
        // TODO Auto-generated method stub
        return nodeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> getNodeId(Integer rid) {
        return nodeMapper.selectnodeIdbyRId(rid);
    }

}
