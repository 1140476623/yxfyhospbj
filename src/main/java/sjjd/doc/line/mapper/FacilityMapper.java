package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.FacilityExample;

import java.util.List;

public interface FacilityMapper {
    long countByExample(FacilityExample example);

    int deleteByExample(FacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Facility record);

    int insertSelective(Facility record);

    List<Facility> selectByExampleWithBLOBs(FacilityExample example);

    List<Facility> selectByExample(FacilityExample example);

    Facility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByExampleWithBLOBs(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByExample(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByPrimaryKeySelective(Facility record);

    int updateByPrimaryKeyWithBLOBs(Facility record);

    int updateByPrimaryKey(Facility record);

    List<Facility> selectFacList(@Param("currentpage") Integer currentpage, @Param("perpage") Integer perpagem, @Param("facDid") String facDid,
                                 @Param("register") String register, @Param("state") String state, @Param("type") String type, @Param("ids") List<String> orgCode);

    String selectFacnum(@Param("facDid") String facDid, @Param("register") String register, @Param("state") String state, @Param("type") String type,
                        @Param("ids") List<String> orgCode);

    List<Facility> selectFacpage(@Param("currentpage") Integer currentpage, @Param("perpage") Integer perpagem, @Param("mac") String mac, @Param("ids") List<String> orgCode);

    List<Facility> selectsignpage(@Param("currentpage") Integer currentpage, @Param("perpage") Integer perpagem, @Param("mac") String mac);

}