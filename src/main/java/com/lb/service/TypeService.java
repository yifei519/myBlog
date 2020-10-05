package com.lb.service;

import com.lb.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    //保存新增的东西
    Type saveType(Type type);
    //通过id查询
    Type getType(Long id);
    //分页查询
    Page<Type> listType(Pageable pageable);
    //通过ID查询进行修改
    Type updateType(Long id,Type type);
    //删除
    void deleteType(Long id);
/**
 * 通过名称来查询数据库是否存在该值
 * */
    Type getTypeBnName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

}
