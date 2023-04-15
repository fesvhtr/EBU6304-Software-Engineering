package entity;

import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 17:24
 */
public class SuperAdmin extends AbstractUser {
    public SuperAdmin(String account, String password, String contact, String name) {
        super(account, password, contact, name, "超级管理员");
    }

}
