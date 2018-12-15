package service;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.Integer.parseInt;

public class UserService {

    public void saveUser(User user) throws Exception{
        String sql = "insert into user values (?,?,?,?)";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,null);
        st.setString(2,user.getUserName());
        st.setString(3,user.getPassword());
        st.setString(4,user.getRole());
        st.executeUpdate();
        con.close();
        System.out.println("User saved successfully.......");
    }

    public User findUser(String str) throws Exception{
        String sql = "select * from user where userName=?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,str);

        ResultSet rs=null;
        try{
            rs = st.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        User user = new User();
        int cnt=0;
        while (rs.next()){
            String str1 = rs.getString(1);
            user.setId(parseInt(str1));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(rs.getString(4));
            cnt++;
        }
        if(cnt==0){
            user.setId(0);
            user.setUserName("no");
            user.setPassword("no");
            user.setRole("no");
        }
        return user;
    }

}
