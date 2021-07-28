package top.jiakaic.blog.service;


import org.springframework.transaction.annotation.Transactional;
import top.jiakaic.blog.dos.Archives;
import top.jiakaic.blog.pojo.Article;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.ArticleParams;
import top.jiakaic.blog.vo.params.PageParams;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/26 -9:03
 * @Description
 **/
@Transactional
public interface ArticleService {
    Result listArticles(PageParams pageParams);

    List<Article> hotArtilces(int limit);

    List<Article> newArtilces(int limit);

    List<Archives> listArchives();

    Result viewById(Long id);

    Result publish(ArticleParams articleParams);
}
