/*
package com.roy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component("noticesDao")
public class NoticesDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc){
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }
    public List<Notice> getNotices(){
        return jdbc.query("select * from notices", new RowMapper<Notice>() {
            public Notice mapRow(ResultSet rs, int i) throws SQLException {
                Notice notice = new Notice();
                notice.setId(rs.getInt("id"));
                notice.setName(rs.getString("name"));
                notice.setEmail(rs.getString("email"));
                notice.setText(rs.getString("text"));
                return notice;
            }
        });
    }

    public Notice getNotice(int id){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbc.queryForObject("select * from notices where id=:id", param, new RowMapper<Notice>() {
            public Notice mapRow(ResultSet rs, int i) throws SQLException {
                Notice notice = new Notice();
                notice.setId(rs.getInt("id"));
                notice.setName(rs.getString("name"));
                notice.setEmail(rs.getString("email"));
                notice.setText(rs.getString("text"));
                return notice;
            }
        });
    }

    public boolean delete(int id){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbc.update("delete from notices where id=:id", param) == 1;
    }

    public boolean create(Notice notice){
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(notice);
        return jdbc.update("insert into notices (name,email,text) values (:name, :email, :text)",param) == 1;
    }

    @Transactional
    public int[] create(List<Notice> notices){
        SqlParameterSource[] param = SqlParameterSourceUtils.createBatch(notices.toArray());
        return jdbc.batchUpdate("insert into notices (name,email,text) values (:name, :email, :text)",param);
    }

    public boolean update(Notice notice){
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(notice);
        return jdbc.update("update notices set name=:name, email=:email, text=:text where id=:id",param)==1;
    }
}
*/
