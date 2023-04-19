package entity;

import util.FileOperator;
import java.util.List;


public class RoleManager {
    private List<Role> roles;
    private static RoleManager singletonInstance;

    private RoleManager() {
        roles = FileOperator.loadData("Devices.json", Role.class);
    }

    //实现单例模式
    public static RoleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RoleManager();
        }
        return singletonInstance;
    }

    public void addDevice(Role role) {
        roles.add(role);
        FileOperator.writeData(role, "Devices.json");
    }

    public void delDevice(Role role) {
        roles.remove(role);
        FileOperator.writeData(roles, "Devices.json");
    }

    public List<Role> getDevices() {
        return roles;
    }
}
