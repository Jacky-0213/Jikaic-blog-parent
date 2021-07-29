package top.jiakaic.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.admin.mapper.AdminMapper;
import top.jiakaic.blog.admin.mapper.PermissionMapper;
import top.jiakaic.blog.admin.pojo.Admin;
import top.jiakaic.blog.admin.pojo.Permission;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/29 -10:18
 * @Description
 **/
@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public Admin findAdminByUsername(String username){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        queryWrapper.last("limit 1");
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }


    public List<Permission> findPermissionsByAdminId(Long adminId){
        //SELECT * FROM `ms_permission` where id in (select permission_id from ms_admin_permission where admin_id=1)
        return adminMapper.findPermissionByAdminId(adminId);
    }
}
