package top.jiakaic.blog.pojo;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/26 -8:52
 * @Description
 **/
@Data
public class SysUser {
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
