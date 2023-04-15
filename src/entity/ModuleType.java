package entity;

import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 22:03
 */
public class ModuleType {
    private List<Type> types;
    private static ModuleType singletonInstance;

    // 实现单例模式：只有一个ProductType被创建
    public static ModuleType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleType();
        }
        return singletonInstance;
    }
    private ModuleType() {
        types = FileOperator.loadData("ModuleTypes.json", Type.class);
    }

    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "ModuleTypes.json");
    }

    public void removeType(Type type) {
        try {
            types.remove(type);
        } catch (Exception e) {
        }
        FileOperator.writeData(types, "ModuleTypes.json");
    }

    public List<Type> getTypes() {
        return types;
    }
}

