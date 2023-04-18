package entity;

import util.FileOperator;

import java.util.List;


public class UserManager {
    private  List<Retailer> retailers;
    private  List<FactoryAdmin> factoryAdmins;
    private  List<SuperAdmin> superAdmins;
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
        retailers =  FileOperator.loadData("Retailers.json", Retailer.class);
        factoryAdmins = FileOperator.loadData("FactoryAdmins.json", FactoryAdmin.class);
        superAdmins = FileOperator.loadData("SuperAdmins.json", SuperAdmin.class);
    }

    /**
     * 用于验证密码输入的重要方法，并将所对应的身份信息写入本地currentUser变量，等待下一个界面读取
     * @param account 输入的账户
     * @param password 输入的密码
     * @param role  角色 1=零售商，2=工厂主，3=超级管理员
     * @return  boolean 表示密码验证成功与否
     */
    public boolean CheckLogin(String account, String password, int role){
        if (role == 1) {    // Retailer
            for (Retailer localRetailer : retailers) {
                if(localRetailer.getAccount().equals(account) && localRetailer.getPassword().equals(password)) {
                    currentUser = localRetailer;
                    return true;
                }
            }
        }
        if (role == 2) {    // FactoryAdmin
            for (FactoryAdmin localFactoryAdmin : factoryAdmins) {
                if (localFactoryAdmin.getAccount().equals(account) && localFactoryAdmin.getPassword().equals(password)) {
                    currentUser = localFactoryAdmin;
                    return true;
                }
            }
        }
        if (role == 3) {    // SuperAdmin
            for (SuperAdmin localSuperAdmin : superAdmins) {
                if (localSuperAdmin.getAccount().equals(account) && localSuperAdmin.getPassword().equals(password)) {
                    currentUser = localSuperAdmin;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addRetailer(Retailer retailer) {
        for (Retailer localRetailer : retailers) {
            if (retailer.getAccount().equals(localRetailer.getAccount()))
                return false;
        }
        retailers.add(retailer);
        FileOperator.writeData(retailer, "Retailers.json");
        return true;
    }

    public boolean addFactoryAdmin(FactoryAdmin factoryAdmin) {
        for (FactoryAdmin localFactoryAdmin : factoryAdmins) {
            if (factoryAdmin.getAccount().equals(localFactoryAdmin.getAccount()))
                return false;
        }
        factoryAdmins.add(factoryAdmin);
        FileOperator.writeData(factoryAdmin, "FactoryAdmins.json");
        return true;
    }

    public boolean addSuperAdmin(SuperAdmin superAdmin) {
        for (SuperAdmin localSuperAdmin : superAdmins) {
            if (superAdmin.getAccount().equals(localSuperAdmin.getAccount()))
                return false;
        }
        superAdmins.add(superAdmin);
        FileOperator.writeData(superAdmin, "SuperAdmin.json");
        return true;
    }

    public boolean remove(AbstractUser user) {
        if (user.getClass() == FactoryAdmin.class) {
            factoryAdmins.remove(user);
            FileOperator.writeData(factoryAdmins, "FactoryAdmins.json");
            return true;
        } else if (user.getClass() == Retailer.class) {
            retailers.remove(user);
            FileOperator.writeData(retailers, "Retailers.json");
            return true;
        }
        return false;
    }

    public List<Retailer> getRetailers() {
        return retailers;
    }

    public AbstractUser getCurrentUser() {
        return currentUser;
    }

    public List<FactoryAdmin> getFactoryAdmins() {
        return factoryAdmins;
    }

    public List<SuperAdmin> getSuperAdmins() {
        return superAdmins;
    }
}
