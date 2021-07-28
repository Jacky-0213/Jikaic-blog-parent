package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiakaic.blog.service.LoginService;
import top.jiakaic.blog.vo.LoginUserVo;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.LoginParams;

/**
 * @author JK
 * @date 2021/7/26 -22:27
 * @Description
 **/
@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParams loginParams){
        return loginService.registerWithLogin(loginParams);
    }
}
