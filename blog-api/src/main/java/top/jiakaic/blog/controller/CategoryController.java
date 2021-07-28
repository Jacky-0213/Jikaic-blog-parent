package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jiakaic.blog.service.CategoryService;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/27 -20:23
 * @Description
 **/
@RestController
@RequestMapping("categorys")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Result categorys(){
        return categoryService.categorys();
    }

    @GetMapping("/detail")
    public Result categorysDetail(){
        return categoryService.categorysDetail();
    }
    @GetMapping("/detail/{id}")
    public Result categorysDetailById(@PathVariable("id")Long id){
        return categoryService.categorysDetailById(id);
    }
}
