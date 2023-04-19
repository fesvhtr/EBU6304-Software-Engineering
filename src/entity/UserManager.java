package entity;

import util.FileOperator;

import java.util.List;


public class UserManager {
    private  List<Student> students;
    private  AbstractUser currentUser;
    private static UserManager singletonInstance;

    //实现单例模式
    public static UserManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new UserManager();
        }
        return singletonInstance;
    }

    private UserManager() {
        students = FileOperator.loadData("SuperAdmins.json", Student.class);
    }

    /**
     * 用于验证密码输入的重要方法，并将所对应的身份信息写入本地currentUser变量，等待下一个界面读取
     * @param account 输入的账户
     * @param password 输入的密码
     * @param role  角色 1=零售商，2=工厂主，3=超级管理员
     * @return  boolean 表示密码验证成功与否
     */
    public boolean CheckLogin(String account, String password, int role){
        if (role == 3) {    // SuperAdmin
            for (Student localStudent : students) {
                if (localStudent.getAccount().equals(account) && localStudent.getPassword().equals(password)) {
                    currentUser = localStudent;
                    return true;
                }
            }
        }
        return false;
    }



    public boolean addSuperAdmin(Student student) {
        for (Student localStudent : students) {
            if (student.getAccount().equals(localStudent.getAccount()))
                return false;
        }
        students.add(student);
        FileOperator.writeData(student, "SuperAdmin.json");
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
