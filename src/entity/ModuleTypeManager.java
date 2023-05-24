package entity;

import constant.Constants;
import util.FileOperator;

import java.util.List;

/**
 * Manager for module types
 */
public class ModuleTypeManager extends Manager implements Constants {
    private static ModuleTypeManager singletonInstance;

    /**
     * Get the singleton instance of ModuleType class.
     * @return The singleton instance of ModuleType class.
     */
    public static ModuleTypeManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of ModuleType class.
     */
    private ModuleTypeManager() {
        super(MODULE_TYPE_FILE_NAME, Type.class);
    }
}
