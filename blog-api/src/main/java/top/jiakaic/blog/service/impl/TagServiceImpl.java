package top.jiakaic.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.jiakaic.blog.mapper.TagMapper;
import top.jiakaic.blog.pojo.Tag;
import top.jiakaic.blog.service.TagService;
import top.jiakaic.blog.vo.Result;
import top.jiakaic.blog.vo.TagVo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JK
 * @date 2021/7/26 -10:13
 * @Description
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public List<TagVo> findTagsByArticleId(long articleId) {
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hotTags(int limit) {
        //查询得到hot tagIds
        List<Long> tagIds = tagMapper.hotTags(limit);
        if(CollectionUtils.isEmpty(tagIds)){//如果为空直接返回空集合
            return Result.success(Collections.emptyList());
        }
        //通过tagIds找到id和tagName
        List<Tag> tags = tagMapper.selectBatchIds(tagIds);
        return Result.success(tags);
    }

    @Override
    public Result tags() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        List<TagVo> tagVos = copyList(tags);
        return Result.success(tagVos);
    }

    @Override
    public Result tagsDetail() {
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<>());
        List<TagVo> tagVos = copyList(tags);
        return Result.success(tagVos);
    }

    @Override
    public Result tagsDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }

    public List<TagVo> copyList(List<Tag> tags){
        List<TagVo> tagVos = tags.stream().map(this::copy
        ).collect(Collectors.toList());
        return tagVos;
    }
    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
}
