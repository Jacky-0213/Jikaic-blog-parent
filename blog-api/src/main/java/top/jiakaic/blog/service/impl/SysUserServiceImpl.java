package top.jiakaic.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.mapper.SysUserMapper;
import top.jiakaic.blog.pojo.SysUser;
import top.jiakaic.blog.service.SysUserService;
import top.jiakaic.blog.utils.JWTUtils;
import top.jiakaic.blog.vo.ErrorCode;
import top.jiakaic.blog.vo.LoginUserVo;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.UserVo;

import java.util.Map;

/**
 * @author JK
 * @date 2021/7/26 -10:37
 * @Description
 **/
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserNameByArticleId(long articleId) {
        SysUser sysUser = sysUserMapper.selectById(articleId);
        if(sysUser==null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setNickname("炸屎");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result getUserinfoByToken(String token) {
        //1.检查token是否合法：包括是否为空，解析是否成功，以及redis是否存在
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String userJson = (String) redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        LoginUserVo userVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        return Result.success(userVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }

    @Override
    public UserVo findUserVoById(Long authorId) {
        SysUser sysUser = sysUserMapper.selectById(authorId);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("炸屎");
        }
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(sysUser.getId());
        return userVo;
    }
}
