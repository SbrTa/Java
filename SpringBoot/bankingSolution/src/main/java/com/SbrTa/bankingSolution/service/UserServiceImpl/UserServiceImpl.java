package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.RoleDao;
import com.SbrTa.bankingSolution.dao.UserDao;
import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.domain.security.UserRole;
import com.SbrTa.bankingSolution.service.AccountService;
import com.SbrTa.bankingSolution.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void save(User user){
        userDao.save(user);
    }

    public User createUser(User user, Set<UserRole> userRoles){
        String encryptredPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptredPassword);
        for(UserRole role : userRoles){
            roleDao.save(role.getRole());
        }
        user.setPrimaryAccount(accountService.createPrimaryAccount());
        user.setSavingsAccount(accountService.createSavingsAccount());
        return userDao.save(user);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
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
