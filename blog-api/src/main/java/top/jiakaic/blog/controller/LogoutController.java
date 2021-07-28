package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiakaic.blog.service.LoginService;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/26 -22:23
 * @Description
 **/
@RestController
@RequestMapping("logout")
public class LogoutController {
    @Autowired
    LoginService loginService;
    @GetMapping
    public Result logout(@RequestHeader("Authorization")String token){
        return loginService.logout(token);
    }
}
