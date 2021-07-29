package top.jiakaic.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiakaic.blog.dos.Archives;
import top.jiakaic.blog.mapper.ArticleBodyMapper;
import top.jiakaic.blog.mapper.ArticleMapper;
import top.jiakaic.blog.mapper.ArticleTagMapper;
import top.jiakaic.blog.pojo.*;
import top.jiakaic.blog.service.*;
import top.jiakaic.blog.utils.UserThreadLocal;
import top.jiakaic.blog.vo.*;
import top.jiakaic.blog.vo.params.ArticleBodyParams;
import top.jiakaic.blog.vo.params.ArticleParams;
import top.jiakaic.blog.vo.params.PageParams;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JK
 * @date 2021/7/26 -9:12
 * @Description
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleBodyMapper articleBodyMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagService tagService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ThreadService threadService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public Result listArticles(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());

        IPage<Article> articleIPage = articleMapper.listArticles(page,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth());
        return Result.success(copyList(articleIPage.getRecords(),true,true));
    }
//    @Override
//    public Result listArticles(PageParams pageParams) {
//        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        //
//        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
//        Long categoryId = pageParams.getCategoryId();
//        //若categoryId不为空，则加上
//        if(categoryId !=null){
//            queryWrapper.eq(Article::getCategoryId,categoryId);
//        }
//        //若tags 不为空
//        Long tagId = pageParams.getTagId();
//        List<Long> articleIds = new ArrayList<>();
//        if(tagId!=null){
//            LambdaQueryWrapper<ArticleTag> queryWrapper1 = new LambdaQueryWrapper<>();
//            queryWrapper1.eq(ArticleTag::getTagId,tagId);
//            List<ArticleTag> articleTags = articleTagMapper.selectList(queryWrapper1);
//            for (ArticleTag articleTag : articleTags) {
//                articleIds.add(articleTag.getArticleId());
//            }
//            if(articleIds.size()>0){
//                queryWrapper.in(Article::getId,articleIds);
//            }
//        }
//        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
//        List<Article> articles = articlePage.getRecords();
//        //调用转换方法
//        List<ArticleVo> articleVoList = copyList(articles,true,true);
//        return Result.success(articleVoList);
//    }


    /**
     * 最热文章
     *
     * @param limit 表示显示的热门文章数
     * @return
     */
    @Override
    public List<Article> hotArtilces(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getTitle);
        //在sql语句最后加上limit
        queryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return articles;
    }

    /**
     * 最新文章
     *
     * @param limit 显示最新文章数
     * @return
     */
    @Override
    public List<Article> newArtilces(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Article::getCreateDate);
        queryWrapper.select(Article::getTitle);
        //在sql语句最后加上limit
        queryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return articles;
    }

    @Override
    public List<Archives> listArchives() {
        return articleMapper.listArchives();
    }

    /**
     * 根据文章id找到文章的详情
     *
     * @param id
     * @return
     */
    @Override
    public Result viewById(Long id) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, id);
        queryWrapper.last("limit 1");
        Article article = articleMapper.selectOne(queryWrapper);
        ArticleVo articleVo = copy(article, true, true, true, true);
        //.查看文章后要更新阅读量
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }

    @Override
    public Result publish(ArticleParams articleParams) {
        /**
         * 1.发布文章 目的构建Article对象
         * 2.ArticleTag
         * 4.category
         * 3.body
         *
         */
        //1.article
        SysUser sysUser = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(Long.getLong(articleParams.getCategory().getId()));
        article.setCreateDate(System.currentTimeMillis());
        article.setCommentCounts(0);
        article.setSummary(articleParams.getSummary());
        article.setTitle(articleParams.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.Article_Common);
        article.setBodyId(-1L);
        this.articleMapper.insert(article);

        //2.Tag
        List<TagVo> tagVos = articleParams.getTags();
        if (tagVos != null) {
            for (TagVo tagVo : tagVos) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(article.getId());
                this.articleTagMapper.insert(articleTag);
            }
        }

        //3.Body
        ArticleBodyParams bodyParams = articleParams.getBody();
        ArticleBody articleBody = new ArticleBody();
        BeanUtils.copyProperties(bodyParams, articleBody);
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        //
        articleMapper.updateById(article);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        return Result.success(articleVo);
    }

    /**
     * 将articles转为articleVos的方法
     *
     * @param articles
     * @return
     */
    public List<ArticleVo> copyList(List<Article> articles, boolean hasTag, boolean hasUser, boolean hasBody, boolean hasCategory) {
        List<ArticleVo> articleVoList = articles.stream().map(e ->
                copy(e, hasTag, hasUser, hasBody, hasCategory)
        ).collect(Collectors.toList());
        return articleVoList;
    }

    public List<ArticleVo> copyList(List<Article> articles, boolean hasTag, boolean hasUser) {
        return copyList(articles, hasTag, hasUser, false, false);
    }

    public ArticleVo copy(Article article, boolean hasTag, boolean hasUser, boolean hasBody, boolean hasCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setId(String.valueOf(article.getId()));
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (hasTag) {
            articleVo.setTags(tagService.findTagsByArticleId(article.getId()));
        }
        if (hasUser) {
            articleVo.setAuthor(sysUserService.findUserNameByArticleId(article.getId()).getNickname());
        }
        if (hasBody) {
            articleVo.setBody(findArticleBodyById(article.getBodyId()));
        }
        if (hasCategory) {
            articleVo.setCategory(categoryService.findCategoryByArticleId(article.getId()));
        }
        return articleVo;
    }

    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
