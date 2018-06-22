package com.suichen.service.impl;

import com.suichen.dao.UserModelMapper;
import com.suichen.model.UserModel;
import com.suichen.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserModel> selectUsersByName(String name) {
        return userModelMapper.selectUsersByName(name);
    }

    @Resource
    private UserModelMapper userModelMapper;
}
