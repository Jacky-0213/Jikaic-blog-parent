package top.jiakaic.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.mapper.CommentsMapper;
import top.jiakaic.blog.pojo.Comment;
import top.jiakaic.blog.pojo.SysUser;
import top.jiakaic.blog.service.CommentsService;
import top.jiakaic.blog.service.SysUserService;
import top.jiakaic.blog.utils.UserThreadLocal;
import top.jiakaic.blog.vo.CommentVo;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.UserVo;
import top.jiakaic.blog.vo.params.CommentParams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JK
 * @date 2021/7/27 -15:29
 * @Description
 **/
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    SysUserService sysUserService;

    @Override
    public Result commentsByArticleId(Long articleId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getLevel, 1);
        List<Comment> commentsLevel1 = commentsMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(commentsLevel1);
        return Result.success(commentVos);
    }

    @Override
    public Result comment(CommentParams commentParams) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParams.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParams.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParams.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParams.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentsMapper.insert(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> commentsLevel1) {
        return commentsLevel1.stream().map(this::copy
        ).collect(Collectors.toList());
    }

    /**
     * 把Comment拷贝到CommentVo
     *
     * @param comment
     * @return
     */
    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        //1.查询用户信息拷贝进去
        UserVo userVo = sysUserService.findUserVoById(comment.getAuthorId());
        commentVo.setAuthor(userVo);
        //2.级别分类，找到子评论
        List<CommentVo> commentVoList = findCommentVosByParentId(comment.getId());
        commentVo.setChildrens(commentVoList);
        if (comment.getLevel() > 1) {
            Long toUid = comment.getToUid();
            UserVo toUserVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentVosByParentId(Long parentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,parentId);
        List<Comment> comments = commentsMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(comments);
        return commentVos;
    }
}
