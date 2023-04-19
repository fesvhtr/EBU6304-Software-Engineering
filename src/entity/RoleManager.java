package entity;

import util.FileOperator;
import java.util.List;


public class RoleManager {
    private List<Role> roles;
    private static RoleManager singletonInstance;

    private RoleManager() {
        roles = FileOperator.loadData("Roles.json", Role.class);
    }

    //实现单例模式
    public static RoleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RoleManager();
        }
        return singletonInstance;
    }

    public void addRole(Role role) {
        roles.add(role);
        FileOperator.writeData(role, "Roles.json");
    }

    public void delRole(Role role) {
        roles.remove(role);
        FileOperator.writeData(roles, "Roles.json");
    }

    public List<Role> getRoles() {
        return roles;
    }
}
