package top.jiakaic.blog.pojo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/27 -21:20
 * @Description
 **/
@Data
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;
}
