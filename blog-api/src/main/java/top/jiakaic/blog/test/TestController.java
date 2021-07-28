package top.jiakaic.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/27 -9:16
 * @Description
 **/
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public Result test1(){
        return Result.success(null);
    }
}
