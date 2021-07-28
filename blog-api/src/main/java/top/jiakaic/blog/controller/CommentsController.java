package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jiakaic.blog.service.CommentsService;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.CommentParams;

/**
 * @author JK
 * @date 2021/7/27 -15:27
 * @Description
 **/
@RestController
@RequestMapping("comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @GetMapping("/article/{id}")
    public Result commentsByArticleId(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);
    }
    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParams commentParams){
        return commentsService.comment(commentParams);
    }
}
