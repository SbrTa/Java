/*
package com.roy.spring.service;

import com.test.dao.Notice;
import com.test.dao.NoticesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("noticesService")
public class NoticesService {

    private NoticesDAO noticesDAO = new NoticesDAO();

    @Autowired
    public void setNoticesDAO(NoticesDAO noticesDAO){
        this.noticesDAO=noticesDAO;
    }

    public List<Notice> getCurrent(){
        return noticesDAO.getNotices();
    }


    public void create(Notice notice) {
        noticesDAO.create(notice);
    }
}
*/
