package top.jiakaic.blog.pojo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/26 -8:51
 * @Description tips:MybatisPlus在这里如果有基本类型会有默认值，会在更新字段时更新进数据库
 **/
@Data
public class Article {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private Long id;//

    private String title;//

    private String summary;//

    private Integer commentCounts;

    private Integer viewCounts;

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private Integer weight;


    /**
     * 创建时间
     */
    private Long createDate;//
}
