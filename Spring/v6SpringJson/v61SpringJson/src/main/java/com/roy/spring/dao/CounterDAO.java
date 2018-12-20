package com.roy.spring.dao;


import com.roy.spring.dto.Counter;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("counterDao")
public class CounterDAO {
    @Autowired
    private SessionFactory sessionFactory;


    public void saveOrUpdateCounter(Counter counter) {
        sessionFactory.getCurrentSession().saveOrUpdate(counter);
    }

    public Counter getCounter(int postid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Counter where post=:postid");
        query.setParameter("postid",postid);
        List<Counter> counters = query.list();
        return counters.get(0);
    }

    public List<Counter> getCounterList() {
        return sessionFactory.getCurrentSession().createQuery("from Counter").list();
    }

    public void deleteCounter(int postid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Counter where post=:postid");
        query.setParameter("postid",postid);
        List<Counter> counters = query.list();
        Counter counter = counters.get(0);
        sessionFactory.getCurrentSession().delete(counter);
    }
}
