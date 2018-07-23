package com.shallowan.dao;

import com.shallowan.vo.UserVO;

import java.util.List;

public interface UserDao {
    UserVO getUserByUserName(String userName);

    List<String> queryRolesByUserName(String userName);

    List<String> queryPermissionsByUserName(String userName);
}
