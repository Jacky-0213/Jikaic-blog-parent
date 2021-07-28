package top.jiakaic.blog.vo.params;

import lombok.Data;
import top.jiakaic.blog.vo.CategoryVo;
import top.jiakaic.blog.vo.TagVo;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/27 -20:34
 * @Description
 **/
@Data
public class ArticleParams {
    private Long id;

    private String title;

    private String summary;

    private ArticleBodyParams body;

    private List<TagVo> tags;

    private CategoryVo category;
}
