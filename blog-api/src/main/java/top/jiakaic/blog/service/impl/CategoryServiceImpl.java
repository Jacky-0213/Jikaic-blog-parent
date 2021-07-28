package top.jiakaic.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.mapper.CategoryMapper;
import top.jiakaic.blog.pojo.Category;
import top.jiakaic.blog.service.CategoryService;
import top.jiakaic.blog.vo.CategoryVo;
import top.jiakaic.blog.vo.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JK
 * @date 2021/7/27 -11:07
 * @Description
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> findCategoryByArticleId(Long id) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId,id);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<CategoryVo> categoryVos = categories.stream().map(e -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(e, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
        return categoryVos;
    }

    @Override
    public Result categorys() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<CategoryVo> categoryVos = copyList(categories);
        return Result.success(categories);
    }

    @Override
    public void save(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVo,category);
        categoryMapper.insert(category);
    }

    @Override
    public Result categorysDetail() {
        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<Category>());
        List<CategoryVo> categoryVos = copyList(categories);
        return Result.success(categories);
    }

    @Override
    public Result categorysDetailById(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = copy(category);
        return Result.success(categoryVo);
    }

    private List<CategoryVo> copyList(List<Category> categories) {
        return categories.stream().map(this::copy).collect(Collectors.toList());
    }
    private CategoryVo copy(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
