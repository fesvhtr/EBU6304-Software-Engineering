package entity;

import util.FileOperator;

import java.util.List;


public class SuperAdmin extends AbstractUser {
    public SuperAdmin(String account, String password, String contact, String name) {
        super(account, password, contact, name, "超级管理员");
    }

}
