package top.jiakaic.blog.service;

import top.jiakaic.blog.vo.CategoryVo;
import top.jiakaic.blog.vo.Result;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/27 -11:07
 * @Description
 **/
public interface CategoryService {
    List<CategoryVo> findCategoryByArticleId(Long id);

    Result categorys();

    void save(CategoryVo categoryVo);

    Result categorysDetail();


    Result categorysDetailById(Long id);
}
