package top.jiakaic.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.jiakaic.blog.admin.pojo.Admin;
import top.jiakaic.blog.admin.pojo.Permission;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/29 -10:18
 * @Description
 **/
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * FROM ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long adminId);
}
