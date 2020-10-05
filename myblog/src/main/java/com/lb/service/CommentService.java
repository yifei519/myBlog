package com.lb.service;

import com.lb.entity.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 获取评论ID
     * */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * */
    Comment saveComment(Comment comment);
}
