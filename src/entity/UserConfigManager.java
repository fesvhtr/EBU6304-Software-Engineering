package entity;

import util.FileOperator;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager for user config
 */
public class UserConfigManager {
    private UserConfig userConfig;
    private static UserConfigManager singletonInstance;

    /**
     * Constructor for user config
     */
    private UserConfigManager() {
        List list = FileOperator.loadData("UserConfig.json", UserConfig.class);
        if (list.isEmpty()) {
            this.userConfig = new UserConfig(50, "", "gpt-3.5-turbo", false);
        } else {
            this.userConfig = (UserConfig) list.get(0);
        }
    }

    /**
     * Get the singleton instance of UserConfigManager class.
     * @return The singleton instance of UserConfigManager class.
     */
    public static UserConfigManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new UserConfigManager();
        }
        return singletonInstance;
    }

    /**
     * Change the user config
     * @param userConfig The user config to be changed
     */
    public void changeUserConfig(UserConfig userConfig){
        this.userConfig = userConfig;
        List<UserConfig> list = new ArrayList();
        list.add(this.userConfig);
        FileOperator.writeData(list, "UserConfig.json");
    }

    /**
     * Get the user config
     * @return The user config
     */
    public UserConfig getUserConfig(){
        return this.userConfig;
    }
}
