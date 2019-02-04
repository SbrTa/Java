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

    public User findByUserName(String userName){
        return dao.findByUserName(userName);
    }

    public User findByEmail(String email){
        return dao.findByEmail(email);
    }

    public boolean checkUserNameExists(String userName){
        if (findByUserName(userName)==null){
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

    public boolean checkUserExists(String userName, String email){
        return (checkUserNameExists(userName) || checkEmailExists(email));
    }


}
