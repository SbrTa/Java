package com.roy.spring.service;



import com.roy.spring.dao.UserPostDAO;
import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userPostService")
public class UserPostService {
    private UserPostDAO userPostDAO = new UserPostDAO();

    @Autowired
    public void setUserPostDAO(UserPostDAO userPostDAO){
        this.userPostDAO = userPostDAO;
    }

    public void createPending(Pending pending){
        userPostDAO.createPending(pending);
    }

    public List<Pending> getPending(){
        return userPostDAO.getPending();
    }
    public Pending getPending(int id){
        return userPostDAO.getPending(id);
    }

    public void deletePending(int id) {
        userPostDAO.deletePending(id);
    }

    public void createFinal(UserPost post) {
        userPostDAO.saveOrUpdateFinal(post);
    }
    public void updateFinal(UserPost post) {
        userPostDAO.saveOrUpdateFinal(post);
    }
    public UserPost getFinal(String time) {
        return userPostDAO.getFinal(time);
    }
    public List<UserPost> getFinal() {
        return userPostDAO.getFinal();
    }
    public UserPost getFinal(int postid) {
        return userPostDAO.getFinal(postid);
    }
    public void deleteFinal(int postid) {
        userPostDAO.deleteFinal(postid);
    }


}
