package dto;

public class UserInfo {
    public String userId;
    public String name;
    public String age;
    public String nationality;


    public UserInfo() {
    }

    public UserInfo(String name, String age, String nationality, String userId) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
