package top.jiakaic.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author JK
 * @date 2021/7/29 -10:19
 * @Description
 **/
@Data
public class Admin {
    //后台管理不用分布式ID
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
}
