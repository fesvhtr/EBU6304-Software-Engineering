package entity;

import constant.FileConstants;

/**
 * Manager for skill types
 */
public class SkillTypeManager extends Manager implements FileConstants {
    private static SkillTypeManager singletonInstance;

    /**
     * Get the singleton instance of SkillType class.
     *
     * @return The singleton instance of SkillType class.
     */
    public static SkillTypeManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SkillTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of SkillType class.
     */
    private SkillTypeManager() {
        super(SKILL_TYPE_FILE_NAME, Type.class);
    }
}