package entity;

public class Skill
{
    private String type;
    private String sourceType;
    private String source;
    private String description;

    public Skill(String type, String sourceType, String source, String description)
    {
        this.type = type;
        this.sourceType = sourceType;
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

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
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
