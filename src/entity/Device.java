package entity;

import util.IdGenerator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 21:49
 */
public class Device {
    private final String id;
    private String name;
    private String type;
    private String description;
    private String spec;
    private String status;
    private String user;
    private boolean isOwned;
    private boolean isRent;
    private String resource;

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    private String rentStatus;

    public Device(String name,  String description, String spec, String status, String user) {
        this.name = name;

        this.description = description;
        this.spec = spec;
        this.status = status;
        this.user = user;
        id = "DEV" + IdGenerator.getCode();

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getSpec() {
        return spec;
    }

    public String getStatus() {
        return status;
    }

    public String getResource() {
        return resource;
    }

    public String getUser() {
        return user;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRent() {
        return isRent;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRent(boolean rent) {
        isRent = rent;
        if (isOwned) {
            resource = "自有设备";
            rentStatus = "工厂设备";
        } else {
            resource = "租用设备";
        }
        if (!isOwned && isRent) {
            rentStatus = "已被租用";
        } else if (!isOwned && !isRent) {
            rentStatus = "未被租用";
        }
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
        if (isOwned) {
            resource = "自有设备";
            rentStatus = "工厂设备";
        } else {
            resource = "租用设备";
        }
        if (!isOwned && isRent) {
            rentStatus = "已被租用";
        } else if (!isOwned && !isRent) {
            rentStatus = "未被租用";
        }
    }
}
