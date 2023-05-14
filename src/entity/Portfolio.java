package entity;

public class Portfolio {
    private String type;
    private String title;
    private String uploadDate;
    private String size;
    private String storeFilePath;

    public Portfolio(String type, String title, String uploadDate, String size, String storeFilePath){
        this.type = type;
        this.title = title;
        this.uploadDate = uploadDate;
        this.size = size;
        this.storeFilePath = storeFilePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public String getStoreFilePath() {
        return storeFilePath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStoreFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}

