package entity;


public abstract class AbstractUser {
    private String account;
    private String password;
    private String contact;
    private String name;
    private String role;

    public AbstractUser(String account, String password, String contact, String name, String role) {
        this.account = account;
        this.password = password;
        this.contact = contact;
        this.name = name;
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }
}
