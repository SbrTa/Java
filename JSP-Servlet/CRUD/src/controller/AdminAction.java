package controller;

import service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Admin action servlet post method..");
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        System.out.println("Action : "+action);

        StoryService storyService = new StoryService();
        if(action.equals("ignore")){
            try {
                storyService.removeStoryTemp(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("story removed......");
        }
        if(action.equals("accept")){
            try {
                storyService.saveStory(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("story accepted......");
        }




        response.sendRedirect("/admin");
        return;
    }

}
