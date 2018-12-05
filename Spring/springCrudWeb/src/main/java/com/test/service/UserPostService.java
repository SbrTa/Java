package com.test.service;


import com.test.dao.UserPostDAO;
import com.test.dto.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPostService")
public class UserPostService {
    private UserPostDAO userPostDAO = new UserPostDAO();

    @Autowired
    public void setUserPostDAO(UserPostDAO userPostDAO){
        this.userPostDAO = userPostDAO;
    }

    public void createPending(UserPost userPost){
        userPostDAO.createPaending(userPost);
    }

    public List<UserPost> getPending(){
        return userPostDAO.getPending();
    }
}
