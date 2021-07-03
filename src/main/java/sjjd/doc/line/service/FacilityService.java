package sjjd.doc.line.service;

import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.FacilityMapper;
import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.FacilityExample;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FacilityService implements IFacilityService {

    @Resource
    private FacilityMapper facilityMapper;

    @Override
    public long countByExample(FacilityExample example) {
        return facilityMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(FacilityExample example) {
        return facilityMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return facilityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Facility record) {
        return facilityMapper.insert(record);
    }

    @Override
    public int insertSelective(Facility record) {
        return facilityMapper.insertSelective(record);
    }

    @Override
    public List<Facility> selectByExample(FacilityExample example) {
        return facilityMapper.selectByExample(example);
    }

    @Override
    public Facility selectByPrimaryKey(Integer id) {
        return facilityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Facility record, FacilityExample example) {
        return facilityMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Facility record, FacilityExample example) {
        return facilityMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Facility record) {
        return facilityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Facility record) {
        return facilityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Facility> selectFacList(Integer page, Integer limit, String facDid, String register, String state, String type, List<String> orgCode) {
        Integer currentpage = (page - 1) * limit;
        return facilityMapper.selectFacList(currentpage, limit, facDid, register, state, type, orgCode);
    }

    @Override
    public String selectFacnum(String facDid, String register, String state, String type, List<String> orgCode) {
        return facilityMapper.selectFacnum(facDid, register, state, type, orgCode);
    }

    @Override
    public List<Facility> selectFacpage(Integer page, Integer limit, String mac, List<String> orgCode) {
        Integer currentpage = (page - 1) * limit;
        return facilityMapper.selectFacpage(currentpage, limit, mac, orgCode);
    }

    @Override
    public List<Facility> selectsignpage(Integer page, Integer limit, String mac) {
        Integer currentpage = (page - 1) * limit;
        return facilityMapper.selectsignpage(currentpage, limit, mac);
    }


}
