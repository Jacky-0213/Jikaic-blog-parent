package top.jiakaic.blog.vo.params;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/26 -20:44
 * @Description
 **/
@Data
public class LoginParams {
    private String nickname;
    private String account;
    private String password;
}
