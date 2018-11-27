package service;

import dto.UserInfo;

import java.sql.*;

public class UserInfoService {
    public void saveObject(UserInfo userInfo) throws Exception{

        System.out.println("save db : "+userInfo.toString());
        String sql = "insert into user_info (userName,name,age,nationality) values (?,?,?,?)";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,userInfo.userName);
        st.setString(2,userInfo.name);
        st.setString(3,userInfo.age);
        st.setString(4,userInfo.nationality);
        st.executeUpdate();
        con.close();
        System.out.println("saved.......");
    }
}
