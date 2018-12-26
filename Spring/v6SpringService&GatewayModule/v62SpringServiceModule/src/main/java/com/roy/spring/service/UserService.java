package com.roy.spring.service;
import com.roy.spring.dao.UserDAO;
import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserService {

    private UserDAO userDAO = new UserDAO();

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public void create(User user) {
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userDAO.create(user);
    }

    public User getUser(String userName) {
        return userDAO.getUser(userName);
    }

    public void saveUserDetails(UserDetails userDetails) {
        UserDetails details = getUserDetails(userDetails.getUserName());

        details.setName(userDetails.getName());
        details.setDob(userDetails.getDob());
        details.setBlood(userDetails.getBlood());
        details.setGender(userDetails.getGender());
        details.setCity(userDetails.getCity());
        details.setContact(userDetails.getContact());
        details.setRelation(userDetails.getRelation());
        details.setBio(userDetails.getBio());

        userDAO.saveUserDetails(details);
    }

    public UserDetails getUserDetails(String userName) {
        return userDAO.getUserDetails(userName);
    }

    public void createUserDetails(UserDetails userDetails) {
        userDAO.saveUserDetails(userDetails);
    }
}
