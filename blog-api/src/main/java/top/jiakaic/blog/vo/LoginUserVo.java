package top.jiakaic.blog.vo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/26 -21:47
 * @Description
 * SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname
 **/
@Data
public class LoginUserVo {

    private Long id;

    private String account;


    private String nickname;

    private String avatar;
}
