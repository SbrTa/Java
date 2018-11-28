package service;

import dto.Story;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryService {

    public void saveStoryTemp(Story story) throws Exception{
        System.out.println("save story to temp : "+story);
        Connection con = ConnectionManager.getConnection();
        String sql = "insert into storytemp values(?,?,?,?);";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, null);
        st.setString(2, story.getUserName());
        st.setString(3, story.getStory());
        st.setString(4, story.getDate());
        st.executeUpdate();
        con.close();
        System.out.println("Story submitted to Admin...");
    }

    public List<Story> getAllStoryTemp() throws Exception{
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from storytemp";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Story> book = new ArrayList<Story>();
        while (rs.next()){
            Story story = new Story();
            story.setId(rs.getInt("id"));
            story.setUserName(rs.getString("userName"));
            story.setStory(rs.getString("story"));
            story.setDate(rs.getString("date"));
            System.out.println(story.toString());
            book.add(story);
        }
        con.close();
        System.out.println("Story added to book...");
        return book;
    }

    public Story getStoryTemp(int id) throws Exception{
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from storytemp";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        Story story = new Story();
        while (rs.next()){
            if(rs.getInt("id")==id){
                story.setId(rs.getInt("id"));
                story.setUserName(rs.getString("userName"));
                story.setStory(rs.getString("story"));
                story.setDate(rs.getString("date"));
                System.out.println(story.toString());
            }
        }
        con.close();
        System.out.println("Story added to book...");
        return story;
    }

    public void removeStoryTemp(int id) throws SQLException {
        String sql = "delete from storytemp where id=?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,id);
        st.executeUpdate();
        con.close();
        System.out.println("story removed...");
    }

    public void saveStory(int id) throws Exception{
        System.out.println("save story...");
        StoryService storyService = new StoryService();
        Story story = storyService.getStoryTemp(id);
        System.out.println(story.toString());
        storyService.removeStoryTemp(id);

        System.out.println("save story : "+story);
        Connection con = ConnectionManager.getConnection();
        String sql = "insert into story values(?,?,?,?);";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, null);
        st.setString(2, story.getUserName());
        st.setString(3, story.getStory());
        st.setString(4, story.getDate());
        st.executeUpdate();
        con.close();
        System.out.println("Story saved...");
    }

    public List<Story> getAllStory() throws Exception{
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from story order by date";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Story> book = new ArrayList<Story>();
        while (rs.next()){
            Story story = new Story();
            story.setId(rs.getInt("id"));
            story.setUserName(rs.getString("userName"));
            story.setStory(rs.getString("story"));
            story.setDate(rs.getString("date"));
            System.out.println(story.toString());
            book.add(story);
        }
        con.close();
        System.out.println("Story added to book...");
        return book;
    }

}
