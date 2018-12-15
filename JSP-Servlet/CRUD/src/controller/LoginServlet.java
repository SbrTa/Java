package controller;

import dto.User;
import service.LoginService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        System.out.println("login servlet GET method..");
        session.removeAttribute("user");

        String userName, password;
        userName = user.getUserName();
        password = user.getPassword();

        LoginService loginService = new LoginService();
        user = loginService.authenticate(userName, password);

        if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
            System.out.println("user name & password matched.. login success..");
            session.setAttribute("user",user);

            if(user.getRole().equals("admin")){
                response.sendRedirect("/admin");
                System.out.println("success page ADMIN "+user.getUserName());
                return;
            }

            /*RequestDispatcher dispatcher = request.getRequestDispatcher("userLoggedIn.jsp");
            dispatcher.forward(request, response);*/
            response.sendRedirect("/userServlet");
            System.out.println("success page "+user.getUserName());
            return;
        }
        else{
            System.out.println("User name or password miss matched...");
            response.sendRedirect("login.jsp");
            return;
        }
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login servlet post method..");
        String userName, password;

        userName = request.getParameter("userName");
        password = request.getParameter("password");

        LoginService loginService = new LoginService();
        User user = loginService.authenticate(userName, password);

        if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
            System.out.println("user name & password matched.. login success..");
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            if(user.getRole().equals("admin")){
                response.sendRedirect("/admin");
                System.out.println("success page ADMIN "+user.getUserName());
                return;
            }

            /*RequestDispatcher dispatcher = request.getRequestDispatcher("userLoggedIn.jsp");
            dispatcher.forward(request, response);*/
            response.sendRedirect("/userServlet");
            System.out.println("success page "+user.getUserName());
            return;
        }
        else{
            System.out.println("User name or password miss matched...");
            response.sendRedirect("login.jsp");
            return;
        }
    }
}
