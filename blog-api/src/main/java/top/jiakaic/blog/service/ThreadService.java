package top.jiakaic.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.jiakaic.blog.mapper.ArticleMapper;
import top.jiakaic.blog.pojo.Article;

import java.util.concurrent.TimeUnit;

/**
 * @author JK
 * @date 2021/7/27 -11:58
 * @Description
 **/
@Component
public class ThreadService {
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(article.getViewCounts()+1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        updateWrapper.eq(Article::getViewCounts,article.getViewCounts());
        articleMapper.update(articleUpdate,updateWrapper);
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
    }
//    @Async("taskExecutor")
//    public void updateViewCount(ArticleMapper articleMapper,Article article){
//
//        Article articleUpdate = new Article();
//        articleUpdate.setViewCounts(article.getViewCounts() + 1);
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Article::getId,article.getId());
//        queryWrapper.eq(Article::getViewCounts,article.getViewCounts());
//        articleMapper.update(articleUpdate,queryWrapper);
//        try {
//            //睡眠5秒 证明不会影响主线程的使用
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
