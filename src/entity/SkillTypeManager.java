package entity;

import constant.Constants;
import org.checkerframework.checker.signedness.qual.Constant;
import util.FileOperator;

import java.util.List;

/**
 * Manager for skill types
 */
public class SkillTypeManager extends Manager implements Constants {
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