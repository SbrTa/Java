package service;

import dto.User;

import java.util.HashMap;

public class LoginService {

    HashMap<String,String> users = new HashMap();

    public LoginService(){
    }


    UserService userService = new UserService();

    public User authenticate(String userName, String password){
        User user = null;
        try {
            user = userService.findUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserDetails(String userId){
        User user = new User();
        user.setUserName(users.get(userId));
        return user;
    }
}
