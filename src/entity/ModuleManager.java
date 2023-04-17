package entity;

import util.FileOperator;

import java.util.ArrayList;
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

    public ArrayList<Object> getEva(){
        ArrayList<Object> eva = new ArrayList<>();
        double average = 0;
        double GPA = 0;
        String level;
        double all_mark = 0;
        double all_credit = 0;
        for(Module module : modules)
        {
            all_mark += module.getMark();
            all_credit += 1;
        }

        average = all_mark / all_credit;
        GPA = average / 100 * 4.0;
        if(average >= 70.0)
            level = "First class";
        else if (average >= 60.0)
            level = "Upper second class";
        else if (average >= 50.0)
            level = "Lower second class";
        else if (average >= 40.0)
            level = "Third  class";
        else
            level = "Pass";

        eva.add(average);
        eva.add(GPA);
        eva.add(level);

        return eva;
    }
}
