package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for module types
 */
public class ModuleTypeManager {
    private List<Type> types;
    private static ModuleTypeManager singletonInstance;

    /**
     * Get the singleton instance of ModuleType class.
     * @return The singleton instance of ModuleType class.
     */
    public static ModuleTypeManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ModuleTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of ModuleType class.
     */
    private ModuleTypeManager() {
        types = FileOperator.loadData("ModuleTypes.json", Type.class);
    }

    /**
     * Add a module type to the list.
     * @param type The module type to be added.
     */
    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "ModuleTypes.json");
    }

    /**
     * Delete a module type from the list.
     * @param type The module type to be deleted.
     */
    public void removeType(Type type) {
        try {
            types.remove(type);
        } catch (Exception e) {
        }
        FileOperator.writeData(types, "ModuleTypes.json");
    }

    /**
     * Get the list of module types.
     * @return The list of module types.
     */
    public List<Type> getTypes() {
        return types;
    }
}

