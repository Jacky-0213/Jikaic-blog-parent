package top.jiakaic.blog.utils;


import top.jiakaic.blog.pojo.SysUser;

/**
 * @author JK
 * @date 2021/7/27 -16:27
 * @Description
 **/
public class UserThreadLocal {
    private UserThreadLocal(){};
    private static final ThreadLocal<SysUser> LOCAL=  new ThreadLocal<>();
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
