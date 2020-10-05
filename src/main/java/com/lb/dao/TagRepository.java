package com.lb.dao;

import com.lb.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    //前端标签
    @Query("select t from Tag t")
    List<Tag>  findTop(Pageable pageable);
}
