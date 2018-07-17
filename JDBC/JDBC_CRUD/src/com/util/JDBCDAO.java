package com.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAO {

    public List<Student> viewAll() throws Exception {
        System.out.println("JDBCDAO>viewAll");
        Connection con = ConnectionManager.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        List<Student> list = new ArrayList<Student>();
        while(rs.next()){
            Student std = new Student();
            std.id = Integer.parseInt(rs.getString("id"));
            std.name = rs.getString("name");
            std.cgpa = Float.parseFloat(rs.getString("cgpa"));
            list.add(std);
        }
        con.close();
        return  list;
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
