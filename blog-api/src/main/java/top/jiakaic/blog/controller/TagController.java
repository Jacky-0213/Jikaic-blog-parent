package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiakaic.blog.service.TagService;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/26 -14:52
 * @Description
 **/
@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/hot")
    public Result hotTags(){
        int limit = 5;
        return tagService.hotTags(5);
    }
    @GetMapping
    public Result tags(){
        return tagService.tags();
    }
    @GetMapping("/detail")
    public Result tagsDetail(){
        return tagService.tagsDetail();
    }
    @GetMapping("/detail/{id}")
    public Result tagsDetailById(@PathVariable("id")Long id){
        return tagService.tagsDetailById(id);
    }
}
