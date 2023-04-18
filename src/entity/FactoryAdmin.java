package entity;

import util.FileOperator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FactoryAdmin extends AbstractUser {

    private String factoryName;
    private String factoryDescription;
    private List<Device> devices;
    private String factoryStatus;

    public FactoryAdmin(String account, String password, String contact, String name, String factoryName, String factoryDescription) {
        super(account, password, contact, name, "云工厂管理员");
        this.factoryName = factoryName;
        this.factoryDescription = factoryDescription;
        factoryStatus = "正常";
    }

    public String getFactoryName() {
        return factoryName;
    }

    public String getFactoryDescription() {
        return factoryDescription;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public String getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public void setFactoryDescription(String factoryDescription) {
        this.factoryDescription = factoryDescription;
    }

    public void setFactoryStatus(String factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }


    public void bootDevice(Device device) {
        device.setStatus("空闲");
    }

    public void arrangeProduction(Device device, Date startDate, Date dueDate) {

    }



}
