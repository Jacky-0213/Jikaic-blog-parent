package top.jiakaic.blog.pojo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/27 -10:40
 * @Description
 **/
@Data
public class Category {
    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
