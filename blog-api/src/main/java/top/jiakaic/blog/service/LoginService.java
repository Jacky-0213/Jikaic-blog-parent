package top.jiakaic.blog.service;

import top.jiakaic.blog.pojo.SysUser;
import top.jiakaic.blog.vo.LoginUserVo;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.params.LoginParams;

/**
 * @author JK
 * @date 2021/7/26 -20:45
 * @Description
 **/
public interface LoginService {
    Result login(LoginParams loginParams);

    Result logout(String token);

    Result registerWithLogin(LoginParams loginParams);

    SysUser checkToken(String token);
}
