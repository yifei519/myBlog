package com.lb.service;

import com.lb.entity.Blog;
import com.lb.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    /**
     * 查询一个博客
     * */
    Blog getBlog(Long id);
    /**
     * 分页查询博客
     * */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    /**
     * 添加一个博客
     * */
    Blog saveBlog(Blog blog);
    /**
     * 修改客
     * */
    Blog updateBlog(Long id,Blog blog);
    /**
     * 删除一个博客
     * */
    void delete(Long id);
    /**
     * 前端页面
     * */
    Page<Blog> listBlog(Pageable pageable);
    /**
     * 推荐博客展示
     * */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     *搜索框查询
     * */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     *获取并转换的方法用于调整页面格式
     * */
    Blog getConvert(Long id);

    /**
     *Tag分页查询的方法
     * */
    Page<Blog> listBlog(Long tagId,Pageable pageable);
    /**
     *标签页面
     * */
    Map<String,List<Blog>> archiveBlog();
    /**
     *标签页面具体有多少个标签
     * */
    Long count();
}
