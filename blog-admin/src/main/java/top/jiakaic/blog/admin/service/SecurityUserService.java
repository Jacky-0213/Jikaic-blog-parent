package top.jiakaic.blog.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.jiakaic.blog.admin.pojo.Admin;

import java.util.ArrayList;

/**
 * @author JK
 * @date 2021/7/29 -10:16
 * @Description
 **/
@Component
public class SecurityUserService implements UserDetailsService {
    @Autowired
    AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录的时候，会把userName传递到此处，
            //我们要通过userName查询用户表查询密码，存在返回密码，不存在返回null
        Admin admin = adminService.findAdminByUsername(username);
        if(admin==null){
            return null;
        }else{
            User user = new User(username, admin.getPassword(), new ArrayList<>());
            return user;
        }
    }
}
