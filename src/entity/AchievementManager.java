package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for achievements
 */
public class AchievementManager
{
    private List<Achievement> achievements;
    private static AchievementManager singletonInstance;

    /**
     * Constructor of AchievementManager class.
     */
    private AchievementManager()
    {
        achievements = FileOperator.loadData("Achievements.json", Achievement.class);
    }

    /**
     * Get the singleton instance of AchievementManager class.
     * @return The singleton instance of AchievementManager class.
     */
    public static AchievementManager getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new AchievementManager();
        }
        return singletonInstance;
    }

    /**
     * Add an achievement to the list.
     * @param achievement The achievement to be added.
     */
    public void addAchievement(Achievement achievement)
    {
        achievements.add(achievement);
        FileOperator.writeData(achievements, "Achievements.json");
    }

    /**
     * Delete an achievement from the list.
     * @param achievement The achievement to be deleted.
     */
    public void delAchievement(Achievement achievement)
    {
        achievements.remove(achievement);
        FileOperator.writeData(achievements, "Achievements.json");
    }

    /**
     * Get the list of achievements.
     * @return The list of achievements.
     */
    public List<Achievement> getAchievements()
    {
        return achievements;
    }
}
