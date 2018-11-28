package controller;

import dto.Story;
import dto.User;
import service.StoryService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostStory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post storyu servlet post method...");

        String text = request.getParameter("story");
        //System.out.println(text);

        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        System.out.println("raw date : "+date.toString());

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //System.out.println("User name : "+user.getUserName());

        Story story = new Story(user.getUserName(),text,date.toString());
        System.out.println("Story obj : "+story.toString());

        StoryService storyService = new StoryService();
        try {
            storyService.saveStoryTemp(story);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("userLoggedIn.jsp");
        return;
    }

}
