package dto;

public class Story {
    private Integer id;
    private String userName;
    private String story;
    private String date;

    public Story() {
    }

    public Story(String userName, String story, String date) {
        this.userName = userName;
        this.story = story;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", story='" + story + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
