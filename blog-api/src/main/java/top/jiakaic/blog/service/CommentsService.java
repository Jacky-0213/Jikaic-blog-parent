package top.jiakaic.blog.service;

import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.CommentParams;

/**
 * @author JK
 * @date 2021/7/27 -15:29
 * @Description
 **/
public interface CommentsService {
    Result commentsByArticleId(Long articleId);

    Result comment(CommentParams commentParams);
}
