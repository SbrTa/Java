package service;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public void saveUser(User user) throws Exception{
        String sql = "insert into user (userName,password,role) values (?,?,?)";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,user.getUserName());
        st.setString(2,user.getPassword());
        st.setString(3,user.getRole());
        st.executeUpdate();
        con.close();
        System.out.println("User saved successfully.......");
    }

    public User findUser(String str) throws Exception{
        String sql = "select * from user where userName=?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,str);
        ResultSet rs = st.executeQuery();
        User user = new User();
        int cnt=0;
        while (rs.next()){
            user.setUserName(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setRole(rs.getString(3));
            cnt++;

            System.out.println("user found : "+rs.getString(1));
        }
        System.out.println("cnt = "+cnt);
        if(cnt==0){
            user.setUserName("no");
            user.setPassword("no");
            user.setRole("no");
            System.out.println("innn");
        }
        System.out.println(user.getUserName());
        return user;
    }

}
