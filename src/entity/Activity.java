package entity;

/**
 * This class is used to store the information of an activity.
 */
public class Activity {
    private String title;
    private String role;
    private String startDate;
    private String endDate;
    private String type;

    /**
     * Constructor of Activity class.
     * @param title Title of the activity.
     * @param role Role of the activity.
     * @param startDate Start date of the activity.
     * @param endDate End date of the activity.
     * @param type Type of the activity.
     */
    public Activity(String title, String role, String startDate, String endDate, String type){
        this.title = title;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    /**
     * Get the end date of the activity.
     * @return End date of the activity.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Get the role of the activity.
     * @return Role of the activity.
     */
    public String getRole() {
        return role;
    }

    /**
     * Get the title of the activity.
     * @return Title of the activity.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the start date of the activity.
     * @return Start date of the activity.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get the type of the activity.
     * @return Type of the activity.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the end date of the activity.
     * @param endDate End date of the activity.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Set the role of the activity.
     * @param role Role of the activity.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Set the start date of the activity.
     * @param startDate Start date of the activity.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Set the title of the activity.
     * @param title Title of the activity.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the type of the activity.
     * @param type Type of the activity.
     */
    public void setType(String type) {
        this.type = type;
    }
}
