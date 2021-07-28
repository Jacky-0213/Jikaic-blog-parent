package top.jiakaic.blog.controller;

import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jiakaic.blog.common.aop.LogAnnotation;
import top.jiakaic.blog.dos.Archives;
import top.jiakaic.blog.pojo.Article;
import top.jiakaic.blog.service.ArticleService;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.ArticleParams;
import top.jiakaic.blog.vo.params.PageParams;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/26 -8:54
 * @Description
 **/
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping
    @LogAnnotation(module="文章",operation="获取文章列表")
    public Result listArticles(@RequestBody PageParams pageParams){
//        int i= 10/0; //检测CommonExceptionHandler通用异常处理
        return articleService.listArticles(pageParams);
    }
    @PostMapping("/hot")
    public Result hotArticles(){
        int limit = 5;
        List<Article> articles = articleService.hotArtilces(limit);
        return Result.success(articles);
    }
    @PostMapping("/new")
    public Result newArticles(){
        int limit = 5;
        List<Article> articles = articleService.newArtilces(limit);
        return Result.success(articles);
    }
    @PostMapping("/listArchives")
    public Result listArchives(){
        List<Archives> archives = articleService.listArchives();
        return Result.success(archives);
    }
    @PostMapping("/view/{id}")
    public Result viewById(@PathVariable("id") Long id){
        return articleService.viewById(id);
    }
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParams articleParams){
        return articleService.publish(articleParams);
    }
}
