package entity;

import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 22:07
 */
public class ActivityType {
    private List<Type> types;
    private static ActivityType singletonInstance;

    // 实现单例模式：只有一个DeviceType被创建
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