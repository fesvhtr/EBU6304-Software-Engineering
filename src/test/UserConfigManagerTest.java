package test;

import entity.UserConfig;
import entity.UserConfigManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserConfigManagerTest {
    private UserConfigManager userConfigManager;

    @Before
    public void setUp() {
        userConfigManager = UserConfigManager.getInstance();
    }

    @Test
    public void testChangeUserConfig() {
        UserConfig initialUserConfig = userConfigManager.getUserConfig();

        int newGptTokens = 100;
        String newGptApi = "https://api.gpt.com";
        String newGptModel = "gpt-3.5-turbo";
        boolean newNeedPassword = true;
        String newPassword = "mypassword";

        UserConfig newUserConfig = new UserConfig(newGptTokens, newGptApi, newGptModel, newNeedPassword, newPassword);
        userConfigManager.changeUserConfig(newUserConfig);

        UserConfig updatedUserConfig = userConfigManager.getUserConfig();
        Assert.assertEquals(newGptTokens, updatedUserConfig.getGptTokens());
        Assert.assertEquals(newGptApi, updatedUserConfig.getGptApi());
        Assert.assertEquals(newGptModel, updatedUserConfig.getGptModel());
        Assert.assertEquals(newNeedPassword, updatedUserConfig.isNeedPassword());
        Assert.assertEquals(newPassword, updatedUserConfig.getPassword());

        // Restore initial user config
        userConfigManager.changeUserConfig(initialUserConfig);
    }

    @Test
    public void testGetUserConfig() {
        UserConfig userConfig = userConfigManager.getUserConfig();
        Assert.assertNotNull(userConfig);
    }
}
