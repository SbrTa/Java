package com.util;

import java.sql.*;

public class ConnectionManager {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  con;
    }
}
