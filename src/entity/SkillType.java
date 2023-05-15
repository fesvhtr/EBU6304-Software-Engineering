package entity;

import util.FileOperator;

import java.util.List;


public class SkillType
{
    private List<Type> types;
    private List<String> sources;
    private static SkillType singletonInstance;

    // 实现单例模式：只有一个DeviceType被创建
    public static SkillType getInstance()
    {
        if (singletonInstance == null)
        {
            singletonInstance = new SkillType();
        }
        return singletonInstance;
    }

    private SkillType()
    {
        types = FileOperator.loadData("SkillTypes.json", Type.class);
    }

    public void addType(String type)
    {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "SkillTypes.json");
    }

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

    public List<Type> getTypes()
    {
        return types;
    }

    public List<String> getSources()
    {
        return sources;
    }

    public void setSources(List<String> sources)
    {
        this.sources = sources;
    }
}