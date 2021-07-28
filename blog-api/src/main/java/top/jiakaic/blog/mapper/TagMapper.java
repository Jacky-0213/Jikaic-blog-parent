package top.jiakaic.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.jiakaic.blog.pojo.Tag;

import java.util.List;

/**
 * @author JK
 * @date 2021/7/26 -8:51
 * @Description
 **/
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> findTagsByArticleId(@Param("articleId") long articleId);

    List<Long> hotTags(int limit);
}
