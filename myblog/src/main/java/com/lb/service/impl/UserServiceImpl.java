package com.lb.service.impl;

import com.lb.dao.UserRepository;
import com.lb.entity.User;
import com.lb.service.UserService;
import com.lb.util.MDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MDUtils.code(password));
        return user;
    }
}
