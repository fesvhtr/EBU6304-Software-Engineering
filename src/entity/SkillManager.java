package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager for skills
 */
public class SkillManager
{
    private List<Skill> skills;
    private static SkillManager singletonInstance;

    /**
     * Constructor of SkillManager class.
     */
    private SkillManager()
    {
        skills = FileOperator.loadData("Skills.json", Skill.class);
    }

    /**
     * Get the singleton instance of SkillManager class.
     * @return The singleton instance of SkillManager class.
     */
    public static SkillManager getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SkillManager();
        }
        return singletonInstance;
    }

    /**
     * Add a skill to the list.
     * @param skill The skill to be added.
     */
    public void addSkill(Skill skill)
    {
        skills.add(skill);
        FileOperator.writeData(skills, "Skills.json");
    }

    /**
     * Delete a skill from the list.
     * @param skill The skill to be deleted.
     */
    public void delSkill(Skill skill)
    {
        skills.remove(skill);
        FileOperator.writeData(skills, "Skills.json");
    }

    /**
     * Get the list of skills.
     * @return The list of skills.
     */
    public List<Skill> getSkills()
    {
        return skills;
    }
}
