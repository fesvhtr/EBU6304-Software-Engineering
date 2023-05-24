package entity;

import constant.FileConstants;

/**
 * Manager for achievements
 */
public class AchievementManager extends Manager implements FileConstants {
    private static AchievementManager singletonInstance;

    /**
     * Constructor of AchievementManager class.
     */
    private AchievementManager() {
        super(ACHIEVEMENT_FILE_NAME, Achievement.class);
    }

    /**
     * Get the singleton instance of AchievementManager class.
     *
     * @return The singleton instance of AchievementManager class.
     */
    public static AchievementManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AchievementManager();
        }
        return singletonInstance;
    }
}
