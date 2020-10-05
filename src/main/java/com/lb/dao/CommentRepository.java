package com.lb.dao;

import com.lb.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment ,Long> {



    List<Comment> findByBlogIdAndParentCommentNull(Long id, Sort sort);
}
