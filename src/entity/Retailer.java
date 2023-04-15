package entity;

import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 9:55
 */
public class Retailer extends AbstractUser {
    public Retailer(String account, String password, String contact, String name) {
        super(account, password, contact, name, "经销商");
    }

}
