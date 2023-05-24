package entity;

import constant.FileConstants;

/**
 * Manager for activities
 */
public class ActivityManager extends Manager implements FileConstants
{
    private static ActivityManager singletonInstance;

    /**
     * Constructor of ActivityManager class.
     */
    private ActivityManager() {
        super(ACTIVITY_FILE_NAME, Activity.class);
    }

    /**
     * Get the singleton instance of ActivityManager class.
     *
     * @return The singleton instance of ActivityManager class.
     */
    public static ActivityManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ActivityManager();
        }
        return singletonInstance;
    }

}
