package top.jiakaic.blog.pojo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/27 -15:26
 * @Description
 **/
@Data
public class Comment {
    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;

}
