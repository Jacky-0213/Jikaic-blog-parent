package top.jiakaic.blog.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import top.jiakaic.blog.pojo.SysUser;
import top.jiakaic.blog.utils.JWTUtils;
import top.jiakaic.blog.vo.ErrorCode;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.UserVo;

import java.util.Map;

/**
 * @author JK
 * @date 2021/7/26 -10:37
 * @Description
 **/
public interface SysUserService {
    SysUser findUserNameByArticleId(long articleId);

    SysUser findUser(String account, String password);

    Result getUserinfoByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);

    UserVo findUserVoById(Long authorId);
}
