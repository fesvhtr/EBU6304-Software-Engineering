package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

public class SkillManager
{
    private List<Skill> skills;
    private static SkillManager singletonInstance;
    private SkillManager()
    {
        skills = FileOperator.loadData("Skills.json", Skill.class);
    }

    public static SkillManager getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SkillManager();
        }
        return singletonInstance;
    }

    public void addSkill(Skill skill)
    {
        skills.add(skill);
        FileOperator.writeData(skills, "Skills.json");
    }

    public void delSkill(Skill skill)
    {
        skills.remove(skill);
        FileOperator.writeData(skills, "Skills.json");
    }

    public List<Skill> getSkills()
    {
        return skills;
    }
}
