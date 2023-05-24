package entity;

/**
 * This class is used to store the information of an achievement.
 */
public class Achievement
{
    private String sourceType;
    private String source;
    private String description;

    /**
     * Constructor of Achievement class.
     * @param sourceType Type of the source of the achievement.
     * @param source Source of the achievement.
     * @param description Description of the achievement.
     */
    public Achievement(String sourceType, String source, String description)
    {
        this.sourceType = sourceType;
        this.source = source;
        this.description = description;
    }

    /**
     * Get the type of the source of the achievement.
     * @return Type of the source of the achievement.
     */
    public String getSourceType()
    {
        return sourceType;
    }

    /**
     * Set the type of the source of the achievement.
     * @param sourceType Type of the source of the achievement.
     */
    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    /**
     * Get the source of the achievement.
     * @return Source of the achievement.
     */
    public String getSource()
    {
        return source;
    }

    /**
     * Set the source of the achievement.
     * @param source Source of the achievement.
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * Get the description of the achievement.
     * @return Description of the achievement.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the description of the achievement.
     * @param description Description of the achievement.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
