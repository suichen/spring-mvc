package com.suichen.dao;

import com.suichen.model.UserModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    @Select("SELECT * FROM user_dao WHERE username = #{name}")
    List<UserModel> selectUsersByName(String name);
}