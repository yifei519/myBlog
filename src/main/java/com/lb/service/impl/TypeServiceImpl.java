package com.lb.service.impl;

import com.lb.NotFoundException;
import com.lb.dao.TypeRepository;
import com.lb.entity.Type;
import com.lb.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//type的service层
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    //保存新增的东西
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    //通过id查询
    @Transactional
    @Override
    public Type getType(Long id) {
        return  typeRepository.getOne(id);
    }

    //分页查询
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    //通过ID查询进行修改
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type type1 = typeRepository.getOne(id);
        if(type1==null){
            throw new NotFoundException("该类型不存在");
        }
        BeanUtils.copyProperties(type,type1);

        return typeRepository.save(type1);
    }

    //删除
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    /**
     * 通过名称来查询数据库是否存在该值
     *
     * @param name
     */
    @Override
    public Type getTypeBnName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    //大小由高到底排序（主页的分类）
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }
}
