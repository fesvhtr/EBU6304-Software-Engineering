package entity;

import util.FileOperator;

import java.util.List;


public class Retailer extends AbstractUser {
    public Retailer(String account, String password, String contact, String name) {
        super(account, password, contact, name, "经销商");
    }

}
