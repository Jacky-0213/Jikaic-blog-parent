package top.jiakaic.blog.vo.params;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/27 -16:18
 * @Description
 **/
@Data
public class CommentParams {
    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
