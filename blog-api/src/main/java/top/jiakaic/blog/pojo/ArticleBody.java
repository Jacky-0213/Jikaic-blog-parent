package top.jiakaic.blog.pojo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

/**
 * @author JK
 * @date 2021/7/27 -10:40
 * @Description
 **/
@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
