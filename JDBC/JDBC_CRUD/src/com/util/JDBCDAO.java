package com.util;


import java.sql.*;
public class JDBCDAO {
    public void viewAll() throws Exception {
        System.out.println("JDBCDAO>viewAll");
        Connection con = ConnectionManager.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        while(rs.next()){
            String name = rs.getString("name");
            String id = rs.getString("id");
            System.out.println(id + " "+ name);
        }
        con.close();
    }

    public void addStudent(Student std) throws Exception {
        String sql = "insert into student (id,name,cgpa) values (?,?,?)";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,std.id);
        st.setString(2,std.name);
        st.setFloat(3,std.cgpa);
        st.executeUpdate();
        con.close();
    }

    public void deleteStudent(int id) throws Exception {
        String sql = "delete from student where id=?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,id);
        st.executeUpdate();
        con.close();
    }



}
