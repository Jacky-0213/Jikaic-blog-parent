package top.jiakaic.blog.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.jiakaic.blog.pojo.Tag;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.TagVo;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/26 -10:13
 * @Description
 **/
public interface TagService {
    List<TagVo> findTagsByArticleId(long articleId);

    Result hotTags(int limit);

    Result tags();

    Result tagsDetail();

    Result tagsDetailById(Long id);
}
