package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager for modules
 */
public class ModuleManager {
    private  List<Module> modules;
    private static ModuleManager singletonInstance;

    /**
     * Constructor of ModuleManager class.
     */
    private ModuleManager() {
        modules = FileOperator.loadData("Modules.json", Module.class);
    }

    /**
     * Get the singleton instance of ModuleManager class.
     * @return The singleton instance of ModuleManager class.
     */
    public static ModuleManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleManager();
        }
        return singletonInstance;
    }

    /**
     * Add a module to the list.
     * @param module The module to be added.
     */
    public void addModule(Module module) {
        modules.add(module);
        FileOperator.writeData(module, "Modules.json");
    }

    /**
     * Delete a module from the list.
     * @param module The module to be deleted.
     */
    public void delModule(Module module) {
        modules.remove(module);
        FileOperator.writeData(modules, "Modules.json");
    }

    /**
     * Get the list of modules.
     * @return The list of modules.
     */
    public List<Module> getModule() {
        return modules;
    }

    /**
     * Get the evaluation of the modules.
     * @return The evaluation of the modules.
     */
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
