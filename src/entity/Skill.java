package entity;

/**
 * Class for skill
 */
public class Skill
{
    private String type;
    private String sourceType;
    private String source;
    private String description;

    /**
     * Constructor for skill
     * @param type type of skill
     * @param sourceType source type of skill
     * @param source source of skill
     * @param description description of skill
     */
    public Skill(String type, String sourceType, String source, String description)
    {
        this.type = type;
        this.sourceType = sourceType;
        this.source = source;
        this.description = description;
    }

    /**
     * Get type of skill
     * @return type of skill
     */
    public String getType()
    {
        return type;
    }

    /**
     * Set type of skill
     * @param type type of skill
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Get source type of skill
     * @return source type of skill
     */
    public String getSourceType()
    {
        return sourceType;
    }

    /**
     * Set source type of skill
     * @param sourceType source type of skill
     */
    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    /**
     * Get source of skill
     * @return source of skill
     */
    public String getSource()
    {
        return source;
    }

    /**
     * Set source of skill
     * @param source source of skill
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * Get description of skill
     * @return description of skill
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set description of skill
     * @param description description of skill
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
