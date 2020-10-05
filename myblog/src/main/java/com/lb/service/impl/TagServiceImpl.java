package com.lb.service.impl;

import com.lb.NotFoundException;
import com.lb.dao.TagRepository;
import com.lb.entity.Tag;
import com.lb.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    /**
     * 保存标签
     *
     * @param tag
     */
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 查询通过id标签
     *
     * @param id
     */
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    /**
     * 通过名字查询标签标签
     *
     * @param name
     */
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * 分页查询
     *
     * @param pageable
     */
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * 修改标签
     *
     * @param id
     * @param tag
     */
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag one = tagRepository.getOne(id);
        if (one==null){
            throw new NotFoundException("该标签不存在！");
        }
        BeanUtils.copyProperties(tag,one);
        return tagRepository.save(one);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {//1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }



    //定义一个字符串转化的方法
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    /**
     * 前端的标签管理
     *
     * @param size
     */
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

}
