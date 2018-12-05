package com.test.dao;


import com.test.dto.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("userPostDao")
public class UserPostDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc){
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean createPaending(UserPost post){
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(post);
        return jdbc.update("insert into pending (time,userName,email,content) " +
                "values(:time, :userName, :email, :content)",parameterSource)==1;
    }

    public List<UserPost> getPending(){
        return jdbc.query("select * from pending", new RowMapper<UserPost>() {
            @Override
            public UserPost mapRow(ResultSet resultSet, int i) throws SQLException {
                UserPost post = new UserPost(
                        resultSet.getInt("id"),
                        resultSet.getString("time"),
                        resultSet.getString("userName"),
                        resultSet.getString("email"),
                        resultSet.getString("content"));
                return post;
            }
        });
    }




}
