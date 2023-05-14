package entity;

import util.FileOperator;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class UserConfigManager {
    private UserConfig userConfig;
    private static UserConfigManager singletonInstance;

    private UserConfigManager() {
        List list = FileOperator.loadData("UserConfig.json", UserConfig.class);
        if (list.isEmpty()) {
            this.userConfig = new UserConfig(50, "", "gpt-3.5-turbo", false);
        } else {
            this.userConfig = (UserConfig) list.get(0);
        }
    }
    public static UserConfigManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new UserConfigManager();
        }
        return singletonInstance;
    }
    public void changeUserConfig(UserConfig userConfig){
        this.userConfig = userConfig;
        List<UserConfig> list = new ArrayList();
        list.add(this.userConfig);
        FileOperator.writeData(list, "UserConfig.json");
    }

    public UserConfig getUserConfig(){
        return this.userConfig;
    }
}
