package entity;

import util.IdGenerator;

/**
 * Class for role
 */
public class Role {
    private String title;
    private String startDate;
    private String endDate;

    private String description;

    /**
     * Constructor for role
     * @param title title of role
     * @param description description of role
     * @param startDate start date of role
     * @param endDate end date of role
     */
    public Role(String title,String description, String startDate, String endDate){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Get start date of role
     * @return start date of role
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get title of role
     * @return title of role
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get end date of role
     * @return end date of role
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Get description of role
     * @return description of role
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set title of role
     * @param title title of role
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set start date of role
     * @param startDate start date of role
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Set end date of role
     * @param endDate end date of role
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Set description of role
     * @param description description of role
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
