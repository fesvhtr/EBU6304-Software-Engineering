package entity;

public class Activity {
    private String title;
    private String role;
    private String startDate;
    private String endDate;
    private String type;

    public Activity(String title, String role, String startDate, String endDate, String type){
        this.title = title;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRole() {
        return role;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getType() {
        return type;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }
}
