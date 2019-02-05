package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.UserDao;
import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public void save(User user){
        dao.save(user);
    }

    public User findByUsername(String username){
        return dao.findByUsername(username);
    }

    public User findByEmail(String email){
        return dao.findByEmail(email);
    }

    public boolean checkUsernameExists(String username){
        if (findByUsername(username)==null){
            return false;
        }
        return true;
    }

    public boolean checkEmailExists(String email){
        if(findByEmail(email)==null){
            return false;
        }
        return true;
    }

    public boolean checkUserExists(String username, String email){
        return (checkUsernameExists(username) || checkEmailExists(email));
    }


}
