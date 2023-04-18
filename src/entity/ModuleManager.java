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
        String level;
        float weightedMarks = 0;
        float totalCredits = 0;
        float weightedGradePoints = 0;
        //TODO more kinds of gpa cal algorithm, and may need consider module type
        //TODO is rank need?
        for(Module module : modules)
        {
            float mark = module.getMark();
            float credit = module.getCredit();
            weightedMarks += mark*credit;
            totalCredits += credit;
            if(mark >= 60 && mark <= 100)
            {
                float gradePoints = (float) (4.0 - 3.0*(100 - mark)*(100 - mark)/1600);
                weightedGradePoints += gradePoints * credit;
            }
        }

        float average = weightedMarks / totalCredits;
        float GPA = weightedGradePoints / totalCredits;
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
