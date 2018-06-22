package com.suichen.service;


import com.suichen.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> selectUsersByName(String name);
}
