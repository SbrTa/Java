package com.test.dao;

import com.test.dto.User;
import com.test.dto.UserDetails;
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

    public boolean saveUserDetails(UserDetails userDetails) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userDetails);
        return jdbc.update("update userdetails set " +
                "userName=:userName, name=:name, dob=:dob, blood=:blood, gender=:gender, city=:city, " +
                "contact=:contact, relation=:relation, bio=:bio where userName=:userName",parameterSource)==1;
    }

    public UserDetails getUserDetails(String userName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName",userName);
        return jdbc.queryForObject("select * from userdetails where userName=:userName", parameterSource, new RowMapper<UserDetails>() {
            @Override
            public UserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                UserDetails userDetails = new UserDetails(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("name"),
                        resultSet.getString("dob"),
                        resultSet.getString("blood"),
                        resultSet.getString("gender"),
                        resultSet.getString("city"),
                        resultSet.getString("contact"),
                        resultSet.getString("relation"),
                        resultSet.getString("bio"));
                return userDetails;
            }
        });
    }

    public boolean createUserDetails(UserDetails userDetails) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userDetails);
        return jdbc.update("insert into userdetails values (null, :userName, :name, :dob, :blood, :gender, :city, :contact, :relation, :bio)",parameterSource)==1;
    }
}
