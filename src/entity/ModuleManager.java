package entity;

import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 18:53
 */
public class ModuleManager {
    private  List<Module> modules;
    private static ModuleManager singletonInstance;

    private ModuleManager() {
        modules = FileOperator.loadData("Modules.json", Module.class);
    }

    // 实现单例模式
    public static ModuleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleManager();
        }
        return singletonInstance;
    }

    public void addProduct(Module module) {
        modules.add(module);
        FileOperator.writeData(module, "Modules.json");
    }

    public void delProduct(Module module) {
        modules.remove(module);
        FileOperator.writeData(modules, "Modules.json");
    }

    public List<Module> getProducts() {
        return modules;
    }
}
