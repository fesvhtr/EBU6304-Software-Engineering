package entity;

import constant.FileConstants;

/**
 * Manager for roles
 */
public class RoleManager extends Manager implements FileConstants {
    private static RoleManager singletonInstance;

    /**
     * Constructor of RoleManager class.
     */
    private RoleManager() {
        super(ROLE_FILE_NAME, Role.class);
    }

    /**
     * Get the singleton instance of RoleManager class.
     *
     * @return The singleton instance of RoleManager class.
     */
    public static RoleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RoleManager();
        }
        return singletonInstance;
    }
}
