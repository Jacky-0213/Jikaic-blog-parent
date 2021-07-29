package top.jiakaic.blog.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.admin.mapper.PermissionMapper;
import top.jiakaic.blog.admin.pojo.Permission;
import top.jiakaic.blog.admin.service.PermissionService;
import top.jiakaic.blog.admin.vo.PageResult;
import top.jiakaic.blog.admin.vo.Result;
import top.jiakaic.blog.admin.vo.param.PageParam;

/**
 * @author JK
 * @date 2021/7/29 -8:17
 * @Description
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Result listPermission(PageParam pageParam) {
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isBlank(pageParam.getQueryString())){
            queryWrapper.eq(Permission::getName,pageParam.getQueryString());
        }
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result addPermission(Permission permission) {
        permissionMapper.insert(permission);
        return Result.success(null);
    }

    @Override
    public Result update(Permission permission) {
        permissionMapper.updateById(permission);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        permissionMapper.deleteById(id);
        return Result.success(null);
    }
}
