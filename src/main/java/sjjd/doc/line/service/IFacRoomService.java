package sjjd.doc.line.service;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.FacRoom;
import sjjd.doc.line.pojo.FacRoomExample;

import java.util.List;

public interface IFacRoomService {

    long countByExample(FacRoomExample example);

    int deleteByExample(FacRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FacRoom record);

    int insertSelective(FacRoom record);

    List<FacRoom> selectByExample(FacRoomExample example);

    FacRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(FacRoom record, FacRoomExample example);

    int updateByExample(@Param("record") FacRoom record, @Param("example") FacRoomExample example);

    int updateByPrimaryKeySelective(FacRoom record);

    int updateByPrimaryKey(FacRoom record);
}
