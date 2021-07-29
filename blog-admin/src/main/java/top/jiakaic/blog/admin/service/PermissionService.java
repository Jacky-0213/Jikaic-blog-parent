package top.jiakaic.blog.admin.service;

import top.jiakaic.blog.admin.pojo.Permission;
import top.jiakaic.blog.admin.vo.Result;
import top.jiakaic.blog.admin.vo.param.PageParam;

/**
 * @author JK
 * @date 2021/7/29 -8:17
 * @Description
 **/
public interface PermissionService {
    Result listPermission(PageParam pageParam);

    Result addPermission(Permission permission);

    Result update(Permission permission);

    Result delete(Long id);
}
