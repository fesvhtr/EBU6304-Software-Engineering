package entity;

import constant.Constants;
import util.FileOperator;

import java.util.List;

/**
 * Manager for skills
 */
public class SkillManager extends Manager implements Constants {
    private static SkillManager singletonInstance;

    /**
     * Constructor of SkillManager class.
     */
    private SkillManager() {
        super(SKILL_FILE_NAME, Skill.class);
    }

    /**
     * Get the singleton instance of SkillManager class.
     *
     * @return The singleton instance of SkillManager class.
     */
    public static SkillManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SkillManager();
        }
        return singletonInstance;
    }

}
