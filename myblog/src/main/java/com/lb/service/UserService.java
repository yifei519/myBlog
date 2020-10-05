package com.lb.service;

import com.lb.entity.User;

public interface UserService {
    /**
     * 登陆
     * */
    User checkUser(String username, String password);
}
