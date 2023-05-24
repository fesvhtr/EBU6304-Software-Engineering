package entity;

import util.FileOperator;
import java.util.List;

/**
 * Manager for roles
 */
public class RoleManager {
    private List<Role> roles;
    private static RoleManager singletonInstance;

    /**
     * Constructor of RoleManager class.
     */
    private RoleManager() {
        roles = FileOperator.loadData("Roles.json", Role.class);
    }

    /**
     * Get the singleton instance of RoleManager class.
     * @return The singleton instance of RoleManager class.
     */
    public static RoleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RoleManager();
        }
        return singletonInstance;
    }

    /**
     * Add a role to the list.
     * @param role The role to be added.
     */
    public void addRole(Role role) {
        roles.add(role);
        FileOperator.writeData(role, "Roles.json");
    }

    /**
     * Delete a role from the list.
     * @param role The role to be deleted.
     */
    public void delRole(Role role) {
        roles.remove(role);
        FileOperator.writeData(roles, "Roles.json");
    }

    /**
     * Get the list of roles.
     * @return The list of roles.
     */
    public List<Role> getRoles() {
        return roles;
    }
}
