package entity;

import constant.Constants;
import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager for modules
 */
public class ModuleManager extends Manager implements Constants {
    private static ModuleManager singletonInstance;

    /**
     * Constructor of ModuleManager class.
     */
    private ModuleManager() {
        super(MODULE_FILE_NAME, Module.class);
    }

    /**
     * Get the singleton instance of ModuleManager class.
     *
     * @return The singleton instance of ModuleManager class.
     */
    public static ModuleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleManager();
        }
        return singletonInstance;
    }

}
