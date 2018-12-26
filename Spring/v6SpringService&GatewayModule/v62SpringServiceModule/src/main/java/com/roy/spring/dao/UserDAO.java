package com.roy.spring.dao;
import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("userDao")
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void create(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public User getUser(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where userName=:name");
        query.setParameter("name",userName);
        List<User> users = query.list();
        return users.get(0);
    }

    public void saveUserDetails(UserDetails userDetails) {
        sessionFactory.getCurrentSession().saveOrUpdate(userDetails);
    }
    /*public void update(UserDetails userDetails) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserDetails where userName=:name");
        query.setParameter("name",userDetails.getUserName());
        List<UserDetails> userDetailsList = query.list();
        UserDetails userDetails1 = userDetailsList.get(0);

    }*/

    public UserDetails getUserDetails(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserDetails where userName=:name");
        query.setParameter("name",userName);
        List<UserDetails> userDetails = query.list();
        return userDetails.get(0);
    }


    public int isExist(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where userName=:name");
        query.setParameter("name",userName);
        return query.list().size();
    }
}
