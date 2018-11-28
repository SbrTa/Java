package controller;

import dto.Story;
import service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("User servlet Get method...");

        StoryService storyService = new StoryService();
        List<Story> book = null;
        try {
            book = storyService.getAllStory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("book : ");
        for(Story story : book){
            System.out.println(story.toString());
        }

        HttpSession session = request.getSession();
        session.setAttribute("userBook",book);

        response.sendRedirect("userLoggedIn.jsp");
        return;
    }
}
