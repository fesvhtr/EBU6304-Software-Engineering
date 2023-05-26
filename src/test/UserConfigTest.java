package test;

import entity.UserConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for UserConfig class.
 */
public class UserConfigTest {
    /**
     * Test for getters and setters.
     */
    @Test
    public void testGettersAndSetters() {
        int gptTokens = 100;
        String gptApi = "https://api.gpt.com";
        String gptModel = "gpt-3.5";
        boolean needPassword = true;
        String password = "mypassword";

        UserConfig userConfig = new UserConfig(gptTokens, gptApi, gptModel, needPassword);
        Assert.assertEquals(gptTokens, userConfig.getGptTokens());
        Assert.assertEquals(gptApi, userConfig.getGptApi());
        Assert.assertEquals(gptModel, userConfig.getGptModel());
        Assert.assertEquals(needPassword, userConfig.isNeedPassword());
        Assert.assertEquals("", userConfig.getPassword());

        userConfig.setPassword(password);
        Assert.assertEquals(password, userConfig.getPassword());
    }
}
