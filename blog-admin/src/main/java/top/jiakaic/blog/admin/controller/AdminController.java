package top.jiakaic.blog.admin.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jiakaic.blog.admin.pojo.Permission;
import top.jiakaic.blog.admin.service.PermissionService;
import top.jiakaic.blog.admin.vo.Result;
import top.jiakaic.blog.admin.vo.param.PageParam;


/**
 * @author JK
 * @date 2021/7/29 -8:11
 * @Description
 **/
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    PermissionService permissionService;

    @PostMapping("/permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }
    @PostMapping("/permission/add")
    public Result permissionAdd(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }
    @PostMapping("/permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("/permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }
}
