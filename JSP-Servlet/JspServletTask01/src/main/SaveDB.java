package main;

import dto.UserInfo;

import java.sql.*;

public class SaveDB {
    public void saveObject(UserInfo userInfo) throws Exception{

        System.out.println("save db : "+userInfo.toString());
        String sql = "insert into user_info (name,age,nationality) values (?,?,?)";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,userInfo.name);
        st.setString(2,userInfo.age);
        st.setString(3,userInfo.nationality);
        st.executeUpdate();
        con.close();
        System.out.println("saved.......");
    }
}
