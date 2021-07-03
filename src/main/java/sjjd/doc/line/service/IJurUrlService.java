package sjjd.doc.line.service;


import sjjd.doc.line.pojo.JurUrl;
import sjjd.doc.line.pojo.JurUrlExample;

import java.util.List;


public interface IJurUrlService {
    long countByExample(JurUrlExample example);

    int deleteByExample(JurUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JurUrl record);

    int insertSelective(JurUrl record);

    List<JurUrl> selectByExample(JurUrlExample example);

    JurUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(JurUrl record, JurUrlExample example);

    int updateByExample(JurUrl record, JurUrlExample example);

    int updateByPrimaryKeySelective(JurUrl record);

    int updateByPrimaryKey(JurUrl record);
}
