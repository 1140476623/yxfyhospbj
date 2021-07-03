package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.JurUrlMapper;
import sjjd.doc.line.pojo.JurUrl;
import sjjd.doc.line.pojo.JurUrlExample;

import javax.annotation.Resource;
import java.util.List;


@Service
public class JurUrlService implements IJurUrlService {

    @Resource
    private JurUrlMapper jurUrlMapper;

    @Override
    public long countByExample(JurUrlExample example) {
        // TODO Auto-generated method stub
        return jurUrlMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(JurUrlExample example) {
        // TODO Auto-generated method stub
        return jurUrlMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return jurUrlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JurUrl record) {
        // TODO Auto-generated method stub
        return jurUrlMapper.insert(record);
    }

    @Override
    public int insertSelective(JurUrl record) {
        // TODO Auto-generated method stub
        return jurUrlMapper.insertSelective(record);
    }

    @Override
    public List<JurUrl> selectByExample(JurUrlExample example) {
        // TODO Auto-generated method stub
        return jurUrlMapper.selectByExample(example);
    }

    @Override
    public JurUrl selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return jurUrlMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(JurUrl record, JurUrlExample example) {
        // TODO Auto-generated method stub
        return jurUrlMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(JurUrl record, JurUrlExample example) {
        // TODO Auto-generated method stub
        return jurUrlMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(JurUrl record) {
        // TODO Auto-generated method stub
        return jurUrlMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JurUrl record) {
        // TODO Auto-generated method stub
        return jurUrlMapper.updateByPrimaryKey(record);
    }

}
