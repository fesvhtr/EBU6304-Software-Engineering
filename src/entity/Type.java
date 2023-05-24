package entity;

/**
 * Class for type
 */
public class Type {
    String type;

    /**
     * Constructor for type
     * @param type
     */
    public Type(String type) {
        this.type = type;
    }

    /**
     * Get type
     * @return type
     */
    @Override
    public String toString() {
        return type;
    }
}
