package com.roy.spring.dao;


import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserPost;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

@Component("userPostDao")
public class UserPostDAO {
    @Autowired
    private SessionFactory sessionFactory;


    // PENDING POST
    public void createPending(Pending pending) {
        sessionFactory.getCurrentSession().saveOrUpdate(pending);
    }

    public List<Pending> getPending() {
        return sessionFactory.getCurrentSession().createQuery("from Pending").list();
    }

    public Pending getPending(int id) {
        return (Pending) sessionFactory.getCurrentSession().get(Pending.class,id);
    }

    public void deletePending(int id) {
        Pending pending = (Pending) sessionFactory.getCurrentSession().get(Pending.class,id);
        sessionFactory.getCurrentSession().delete(pending);
    }

    //FINAL POST
    public void saveOrUpdateFinal(UserPost post) {
        sessionFactory.getCurrentSession().saveOrUpdate(post);
    }

    public UserPost getFinal(String time) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserPost where time=:time");
        query.setParameter("time",time);
        List<UserPost> userPosts = query.list();
        return userPosts.get(0);
    }

    public List<UserPost> getFinal() {
        return sessionFactory.getCurrentSession().createQuery("from UserPost").list();
    }

    public UserPost getFinal(int postid) {
        return (UserPost) sessionFactory.getCurrentSession().get(UserPost.class,postid);
    }

    public void deleteFinal(int postid) {
        UserPost userPost = (UserPost) sessionFactory.getCurrentSession().get(UserPost.class,postid);
        sessionFactory.getCurrentSession().delete(userPost);
    }
}
