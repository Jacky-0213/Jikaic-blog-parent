package top.jiakaic.blog.admin.vo.param;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/29 -8:13
 * @Description
 *  * currentPage: 1
 *  * pageSize: 2
 *  * queryString: null
 *  * total: 0
 **/
@Data
public class PageParam {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
}
