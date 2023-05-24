package entity;

import util.IdGenerator;

/**
 * Class for module
 */
public class Module
{
    private final String id;
    private String name;
    private String type;
    private String description;
    private float mark;
    private float credit;

    /**
     * Constructor for module
     * @param name name of module
     * @param type type of module
     * @param spec specification of module
     * @param description description of module
     */
    public Module(String name, String type, String spec, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        id = "PRO" + IdGenerator.getCode();
    }

    /**
     * Constructor for module
     * @param id id of module
     * @param name name of module
     * @param type type of module
     * @param mark mark of module
     * @param credit credit of module
     * @param description description of module
     */
    public Module(String id, String name, String type, float mark, float credit, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.mark = mark;
        this.credit = credit;
        this.description = description;
    }

    /**
     * Get id of module
     * @return id of module
     */
    public String getId() {
        return id;
    }

    /**
     * Get name of module
     * @return name of module
     */
    public String getName() {
        return name;
    }

    /**
     * Get type of module
     * @return type of module
     */
    public String getType() {
        return type;
    }

    /**
     * Get description of module
     * @return description of module
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set name of module
     * @param name name of module
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set type of module
     * @param type type of module
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set description of module
     * @param description description of module
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get mark of module
     * @return mark of module
     */
    public float getMark() {
        return mark;
    }

    /**
     * Set mark of module
     * @param mark mark of module
     */
    public void setMark(float mark) {
        this.mark = mark;
    }

    /**
     * Get credit of module
     * @return credit of module
     */
    public float getCredit() {return credit;}

    /**
     * Set credit of module
     * @param credit credit of module
     */
    public void setCredit(float credit) {this.credit = credit;}

}
