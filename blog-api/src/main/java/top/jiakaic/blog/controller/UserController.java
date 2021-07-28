package top.jiakaic.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiakaic.blog.service.SysUserService;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/26 -21:35
 * @Description
 **/
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    SysUserService sysUserService;

    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization")String token){
        return sysUserService.getUserinfoByToken(token);
    }
}
