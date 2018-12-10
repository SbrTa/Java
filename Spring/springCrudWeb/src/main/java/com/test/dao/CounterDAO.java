package com.test.dao;


import com.test.dto.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("counterDao")
public class CounterDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setJdbc(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean createCounter(Counter counter){
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(counter);
        return jdbc.update("insert into counter (post,liker,disliker) values (:post, :liker, :disliker)",source)==1;
    }

    public Counter getCounter(int postid) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("postid",postid);
        return jdbc.queryForObject("select * from counter where post=:postid", source, new RowMapper<Counter>() {
            @Override
            public Counter mapRow(ResultSet resultSet, int i) throws SQLException {
                Counter post = new Counter(
                        resultSet.getInt("id"),
                        resultSet.getInt("post"),
                        resultSet.getString("liker"),
                        resultSet.getString("disliker")
                );
                return post;
            }
        });
    }

    public boolean updateCounter(Counter counter) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(counter);
        return jdbc.update("update counter set " +
                "post=:post, liker=:liker, disliker=:disliker where id=:id",parameterSource)==1;
    }

    public List<Counter> getCounterList() {
        return jdbc.query("select * from counter", new RowMapper<Counter>() {
            @Override
            public Counter mapRow(ResultSet resultSet, int i) throws SQLException {
                Counter post = new Counter(
                        resultSet.getInt("id"),
                        resultSet.getInt("post"),
                        resultSet.getString("liker"),
                        resultSet.getString("disliker")
                );
                return post;
            }
        });
    }
}
