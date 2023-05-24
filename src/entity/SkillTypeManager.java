package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for skill types
 */
public class SkillTypeManager
{
    private List<Type> types;
    private List<String> sources;
    private static SkillTypeManager singletonInstance;

    /**
     * Get the singleton instance of SkillType class.
     * @return The singleton instance of SkillType class.
     */
    public static SkillTypeManager getInstance()
    {
        if (singletonInstance == null)
        {
            singletonInstance = new SkillTypeManager();
        }
        return singletonInstance;
    }

    /**
     * Constructor of SkillType class.
     */
    private SkillTypeManager()
    {
        types = FileOperator.loadData("SkillTypes.json", Type.class);
    }

    /**
     * Add a skill type to the list.
     * @param type The skill type to be added.
     */
    public void addType(String type)
    {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "SkillTypes.json");
    }

    /**
     * Delete a skill type from the list.
     * @param type The skill type to be deleted.
     */
    public void removeType(Type type)
    {
        try
        {
            for (Type t : types)
            {
                if (t.toString().equals(type.toString()))
                {
                    types.remove(t);
                }
            }
        }
        catch (Exception e)
        {
        }
        FileOperator.writeData(types, "SkillTypes.json");
    }

    /**
     * Get the list of skill types.
     * @return The list of skill types.
     */
    public List<Type> getTypes()
    {
        return types;
    }

    /**
     * Get the list of sources.
     * @return The list of sources.
     */
    public List<String> getSources()
    {
        return sources;
    }

    /**
     * Set the list of sources.
     * @param sources The list of sources.
     */
    public void setSources(List<String> sources)
    {
        this.sources = sources;
    }
}