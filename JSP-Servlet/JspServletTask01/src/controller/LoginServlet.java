package controller;

import dto.User;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login servlet post method..");
        String userId, password;

        userId = request.getParameter("userId");
        password = request.getParameter("password");

        System.out.println("user id = "+userId);
        System.out.println("password = "+password);

        LoginService loginService = new LoginService();
        boolean result = loginService.authenticate(userId, password);

        if(result){
            User user = loginService.getUserDetails(userId);
            //request.getSession().setAttribute("user", user);
            //response.sendRedirect("success.jsp");
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
            System.out.println("success page "+user.getUserName());
            return;
        }
        else{
            System.out.println("login page");
            response.sendRedirect("login.jsp");
            return;
        }
    }
}
