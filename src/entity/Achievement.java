package entity;

public class Achievement
{
    private String sourceType;
    private String source;
    private String description;

    public Achievement(String sourceType, String source, String description)
    {
        this.sourceType = sourceType;
        this.source = source;
        this.description = description;
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
