package com.test.service;

import com.test.dao.Notice;
import com.test.dao.NoticesDAO;
import com.test.dao.UserDAO;
import com.test.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserService {

    private UserDAO userDAO = new UserDAO();

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public void create(User user) {
        user.setRole("user");
        userDAO.create(user);
    }

    public User getUser(String userName) {
        return userDAO.getUser(userName);
    }
}
