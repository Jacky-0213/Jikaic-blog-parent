package top.jiakaic.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/29 -8:25
 * @Description
 **/
@Data
public class PageResult<T> {
    private List<T> list;
    private Long total;
}
