package com.test.dao;

import com.test.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component("userDao")
public class UserDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc){
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }




    public boolean create(User user){
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        return jdbc.update("insert into user (name,email,userName,password,role) values (:name, :email, :userName, :password, :role)",param) == 1;
    }

    public User getUser(String userName){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userName",userName);
        return jdbc.queryForObject("select * from user where userName=:userName", param, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        });
    }
}
