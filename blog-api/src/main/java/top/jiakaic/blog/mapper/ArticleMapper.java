package top.jiakaic.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.jiakaic.blog.dos.Archives;
import top.jiakaic.blog.pojo.Article;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/26 -8:50
 * @Description
 **/
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();

    IPage<Article> listArticles(Page<Article> page, Long categoryId, Long tagId, String year, String month);
}
