package com.shallowan.dao.impl;

import com.shallowan.dao.UserDao;
import com.shallowan.vo.UserVO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserVO getUserByUserName(String userName) {
        String sql = "select username,password from users where username = ?";
        List<UserVO> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<UserVO>() {

            public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserVO user = new UserVO();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    public List<String> queryRolesByUserName(String userName) {
        String sql = "select role_name from user_roles where username = ?";
        return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {

            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });

    }

    public List<String> queryPermissionsByUserName(String userName) {
        return null;
    }
}
