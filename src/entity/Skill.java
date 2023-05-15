package entity;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Skill
{
    private String type;
    private String source;
    private String description;

    public Skill(String type, String source, String description)
    {
        this.type = type;
        this.source = source;
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
