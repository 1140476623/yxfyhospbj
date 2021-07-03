package sjjd.doc.line.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.NewsMapper;
import sjjd.doc.line.pojo.News;
import sjjd.doc.line.service.INewsService;

/**
 * @author by xyw
 * @date 2021/3/23.
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
}
