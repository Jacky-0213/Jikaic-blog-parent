package top.jiakaic.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author JK
 * @date 2021/7/29 -8:24
 * @Description
 **/
@Data
public class Permission {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String path;
    private String description;
}
