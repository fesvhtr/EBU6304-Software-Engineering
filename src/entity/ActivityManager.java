package entity;

import util.FileOperator;

import java.util.List;

public class ActivityManager {
    private List<Activity> activities;
    private static ActivityManager singletonInstance;

    private ActivityManager(){ activities = FileOperator.loadData("Activities.json", Activity.class); }

    public static ActivityManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new ActivityManager();
        }
        return singletonInstance;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
        FileOperator.writeData(activities, "Activities.json");
    }

    public void delActivity(Activity activity){
        activities.remove(activity);
        FileOperator.writeData(activities, "Activities.json");
    }

    public List<Activity> getActivities() {return activities;}

}
