package entity;


public class Student extends AbstractUser {
    public Student(String account, String password, String contact, String name) {
        super(account, password, contact, name, "student");
    }

}
