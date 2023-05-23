package entity;

import util.FileOperator;

import java.util.List;


public class ActivityType {
    private List<Type> types;
    private static ActivityType singletonInstance;


    public static ActivityType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ActivityType();
        }
        return singletonInstance;
    }

    private ActivityType() {
        types = FileOperator.loadData("ActivityTypes.json", Type.class);
    }

    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "ActivityTypes.json");
    }

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

    public List<Type> getTypes() {
        return types;
    }
}