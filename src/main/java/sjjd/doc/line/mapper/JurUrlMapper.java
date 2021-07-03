package sjjd.doc.line.mapper;

import org.apache.ibatis.annotations.Param;
import sjjd.doc.line.pojo.JurUrl;
import sjjd.doc.line.pojo.JurUrlExample;

import java.util.List;

public interface JurUrlMapper {
    long countByExample(JurUrlExample example);

    int deleteByExample(JurUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JurUrl record);

    int insertSelective(JurUrl record);

    List<JurUrl> selectByExample(JurUrlExample example);

    JurUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JurUrl record, @Param("example") JurUrlExample example);

    int updateByExample(@Param("record") JurUrl record, @Param("example") JurUrlExample example);

    int updateByPrimaryKeySelective(JurUrl record);

    int updateByPrimaryKey(JurUrl record);


}