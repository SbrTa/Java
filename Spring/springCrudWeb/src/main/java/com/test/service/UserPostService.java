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
        userPostDAO.createPending(userPost);
    }

    public List<UserPost> getPending(){
        return userPostDAO.getPending();
    }
    public UserPost getPending(int id){
        return userPostDAO.getPending(id);
    }

    public void deletePending(int id) {
        userPostDAO.deletePending(id);
    }

    public void createFinal(UserPost post) {
        userPostDAO.createFinal(post);
    }

    public UserPost getFinal(String time) {
        return userPostDAO.getFinal(time);
    }
    public List<UserPost> getFinal() {
        return userPostDAO.getFinal();
    }
}
