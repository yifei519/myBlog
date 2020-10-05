package com.lb.service;

import com.lb.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    /**
     * 保存标签
     * */
    Tag saveTag(Tag tag);
    /**
     * 查询通过id标签
     * */
    Tag getTag(Long id);
    /**
     * 通过名字查询标签标签
     * */
    Tag getTagByName(String name);
    /**
     * 分页查询
     * */
    Page<Tag> listTag(Pageable pageable);
    /**
     * 修改标签
     * */
    Tag updateTag(Long id,Tag tag);
    /**
     * 删除标签
     * */
    void  deleteTag(Long id);

    List<Tag> listTag();
    List<Tag> listTag(String ids);
    /**
     * 前端的标签管理
     * */
    List<Tag> listTagTop(Integer size);
}
