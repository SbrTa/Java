package main;

import java.sql.*;

public class ConnectionManager {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            System.out.println("connection ok..........");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  con;
    }
}