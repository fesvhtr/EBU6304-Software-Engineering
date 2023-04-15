package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 22:07
 */
public class DeviceType {
    private List<Type> types;
    private static DeviceType singletonInstance;

    // 实现单例模式：只有一个DeviceType被创建
    public static DeviceType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DeviceType();
        }
        return singletonInstance;
    }
    private DeviceType() {
        types = FileOperator.loadData("DeviceTypes.json", Type.class);
    }

    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "DeviceTypes.json");
    }

    public void removeType(Type type) {
        try {
            types.remove(type);
        } catch (Exception e) {
        }
        FileOperator.writeData(types, "DeviceTypes.json");
    }

    public List<Type> getTypes() {
        return types;
    }
}