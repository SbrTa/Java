package controller;

import dto.UserInfo;
import main.SaveDB;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user info servlet post method...");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String nationality = request.getParameter("nationality");

        System.out.println("name = "+name);
        System.out.println("age = "+age);
        System.out.println("nationality = "+nationality);

        UserInfo userInfo = new  UserInfo(name,age,nationality);
        SaveDB saveDB = new SaveDB();
        try{

            saveDB.saveObject(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(userInfo.toString()+" saved.........");


        response.sendRedirect("login.jsp");
        return;

    }
}
