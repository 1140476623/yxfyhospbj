package sjjd.doc.line.service;

import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.FacilityExample;

import java.util.List;

public interface IFacilityService {

    long countByExample(FacilityExample example);

    int deleteByExample(FacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Facility record);

    int insertSelective(Facility record);

    List<Facility> selectByExample(FacilityExample example);

    Facility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Facility record, FacilityExample example);

    int updateByExample(Facility record, FacilityExample example);

    int updateByPrimaryKeySelective(Facility record);

    int updateByPrimaryKey(Facility record);

    List<Facility> selectFacList(Integer page, Integer limit, String facDid,
                                 String register, String state, String type, List<String> orgCode);

    String selectFacnum(String facDid, String register, String state, String type, List<String> orgCode);

    List<Facility> selectFacpage(Integer page, Integer limit, String mac, List<String> orgCode);

    List<Facility> selectsignpage(Integer page, Integer limit, String mac);
}
