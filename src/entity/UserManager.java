package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;


public class UserManager {
    private  List<Student> students;
    private  AbstractUser currentUser;
    private static UserManager singletonInstance;


    public static UserManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new UserManager();
        }
        return singletonInstance;
    }

    private UserManager() {
        students = FileOperator.loadData("Student.json", Student.class);
    }

    public void changeStudentConfig(List<Student> student){
        this.students = student;
        FileOperator.writeData(student, "Student.json");
    }


    public boolean CheckLogin(String password, int role){
        if (role == 3) {
            for (Student localStudent : students) {
                if (localStudent.getPassword().equals(password)) {
                    currentUser = localStudent;
                    return true;
                }
            }
        }
        return false;
    }



    public boolean addStudent(Student student) {
        for (Student localStudent : students) {
            if (student.getAccount().equals(localStudent.getAccount()))
                return false;
        }
        students.add(student);
        FileOperator.writeData(student, "Student.json");
        return true;
    }

    public boolean remove(AbstractUser user) {
        return false;
    }



    public AbstractUser getCurrentUser() {
        return currentUser;
    }


    public List<Student> getSuperAdmins() {
        return students;
    }
}
