package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for activity types
 */
public class ActivityTypeManager {
    private List<Type> types;
    private static ActivityTypeManager singletonInstance;

    /**
     * Get the singleton instance of ActivityType class.
     * @return The singleton instance of ActivityType class.
     */
    public static ActivityTypeManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ActivityTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of ActivityType class.
     */
    private ActivityTypeManager() {
        types = FileOperator.loadData("ActivityTypes.json", Type.class);
    }

    /**
     * Add a type to the list.
     * @param type The type to be added.
     */
    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "ActivityTypes.json");
    }

    /**
     * Delete a type from the list.
     * @param type The type to be deleted.
     */
    public void removeType(Type type) {
        try {
            for (Type t : types) {
                if (t.toString().equals(type.toString())) {
                    types.remove(t);
                }
            }
        } catch (Exception e) {
        }
        FileOperator.writeData(types, "ActivityTypes.json");
    }

    /**
     * Get the list of types.
     * @return The list of types.
     */
    public List<Type> getTypes() {
        return types;
    }
}