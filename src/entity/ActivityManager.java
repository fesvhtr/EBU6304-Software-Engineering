package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for activities
 */
public class ActivityManager {
    private List<Activity> activities;
    private static ActivityManager singletonInstance;

    /**
     * Constructor of ActivityManager class.
     */
    private ActivityManager(){ activities = FileOperator.loadData("Activities.json", Activity.class); }

    /**
     * Get the singleton instance of ActivityManager class.
     * @return The singleton instance of ActivityManager class.
     */
    public static ActivityManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new ActivityManager();
        }
        return singletonInstance;
    }

    /**
     * Add an activity to the list.
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity){
        activities.add(activity);
        FileOperator.writeData(activities, "Activities.json");
    }

    /**
     * Delete an activity from the list.
     * @param activity The activity to be deleted.
     */
    public void delActivity(Activity activity){
        activities.remove(activity);
        FileOperator.writeData(activities, "Activities.json");
    }

    /**
     * Get the list of activities.
     * @return The list of activities.
     */
    public List<Activity> getActivities() {return activities;}

}
