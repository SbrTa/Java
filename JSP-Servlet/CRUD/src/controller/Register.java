package controller;

import dto.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in register servlet.......");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println("user name : "+userName);
        System.out.println("password : "+password);

        User user = new User(userName,password,"user");
        UserService userService = new UserService();
        User user2 = null;
        try {
            user2 = userService.findUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(user2.getUserName().equals("no")){
            response.sendRedirect("register.jsp");
            return;
        }

        System.out.println("user 2 :: "+user2.toString());



        try {
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("userSaved.jsp");
        return;
    }

}
