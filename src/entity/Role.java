package entity;

import util.IdGenerator;


public class Role {
    private String title;
    private String startDate;
    private String endDate;

    private String description;

    public Role(String title, String startDate, String endDate, String description){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
