package entity;

import constant.Constants;
import util.FileOperator;

import java.util.List;

/**
 * Manager for activity types
 */
public class ActivityTypeManager extends Manager implements Constants {
    private static ActivityTypeManager singletonInstance;

    /**
     * Get the singleton instance of ActivityType class.
     *
     * @return The singleton instance of ActivityType class.
     */
    public static ActivityTypeManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ActivityTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of ActivityType class.
     */
    private ActivityTypeManager() {
        super(ACTIVITY_TYPE_FILE_NAME, Type.class);
    }
}