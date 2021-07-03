package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.FacRoomMapper;
import sjjd.doc.line.pojo.FacRoom;
import sjjd.doc.line.pojo.FacRoomExample;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FacRoomService implements IFacRoomService {

    @Resource
    private FacRoomMapper facRoomMapper;


    @Override
    public long countByExample(FacRoomExample example) {
        return facRoomMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(FacRoomExample example) {
        return facRoomMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return facRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FacRoom record) {
        return facRoomMapper.insert(record);
    }

    @Override
    public int insertSelective(FacRoom record) {
        return facRoomMapper.insertSelective(record);
    }

    @Override
    public List<FacRoom> selectByExample(FacRoomExample example) {
        return facRoomMapper.selectByExample(example);
    }

    @Override
    public FacRoom selectByPrimaryKey(Integer id) {
        return facRoomMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(FacRoom record, FacRoomExample example) {
        return facRoomMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(FacRoom record, FacRoomExample example) {
        return facRoomMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(FacRoom record) {
        return facRoomMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FacRoom record) {
        return facRoomMapper.updateByPrimaryKey(record);
    }
}
