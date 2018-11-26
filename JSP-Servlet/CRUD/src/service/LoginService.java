package service;

import dto.User;

import java.util.HashMap;

public class LoginService {

    HashMap<String,String> users = new HashMap();

    public LoginService(){
    }

    public boolean authenticate(String userId, String password){
        if((userId.equals("SbrTa") || userId.equals("test")) && password.equals("12345")){
            return true;
        }
        return false;
    }

    public User getUserDetails(String userId){
        User user = new User();
        user.setUserName(users.get(userId));
        return user;
    }
}
