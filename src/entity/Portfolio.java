package entity;

/**
 * Class for portfolio
 */
public class Portfolio {
    private Type type;
    private String title;
    private String uploadDate;
    private String size;
    private String storeFilePath;

    /**
     * Constructor for portfolio
     * @param type type of portfolio
     * @param title title of portfolio
     * @param uploadDate upload date of portfolio
     * @param size size of portfolio
     * @param storeFilePath store file path of portfolio
     */
    public Portfolio(Type type, String title, String uploadDate, String size, String storeFilePath){
        this.type = type;
        this.title = title;
        this.uploadDate = uploadDate;
        this.size = size;
        this.storeFilePath = storeFilePath;
    }

    /**
     * Get type of portfolio
     * @return type of portfolio
     */
    public Type getType() {
        return type;
    }

    /**
     * Set type of portfolio
     * @param type type of portfolio
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Get title of portfolio
     * @return title of portfolio
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get size of portfolio
     * @return size of portfolio
     */
    public String getSize() {
        return size;
    }

    /**
     * Get store file path of portfolio
     * @return store file path of portfolio
     */
    public String getStoreFilePath() {
        return storeFilePath;
    }

    /**
     * Get upload date of portfolio
     * @return upload date of portfolio
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * Set title of portfolio
     * @param title title of portfolio
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set size of portfolio
     * @param size size of portfolio
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Set store file path of portfolio
     * @param storeFilePath store file path of portfolio
     */
    public void setStoreFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath;
    }

    /**
     * Set upload date of portfolio
     * @param uploadDate upload date of portfolio
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}

