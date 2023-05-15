package entity;

import util.FileOperator;

import java.util.List;

public class AchievementManager
{
    private List<Achievement> achievements;
    private static AchievementManager singletonInstance;
    private AchievementManager()
    {
        achievements = FileOperator.loadData("Achievements.json", Skill.class);
    }

    public static AchievementManager getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new AchievementManager();
        }
        return singletonInstance;
    }

    public void addAchievement(Achievement achievement)
    {
        achievements.add(achievement);
        FileOperator.writeData(achievements, "Achievements.json");
    }

    public void delAchievement(Achievement achievement)
    {
        achievements.remove(achievement);
        FileOperator.writeData(achievements, "Achievements.json");
    }

    public List<Achievement> getAchievements()
    {
        return achievements;
    }
}
