package entity;

import javax.jws.soap.SOAPBinding;

/**
 * Class for user config
 */
public class UserConfig {
    private int gptTokens;
    private String gptModel;
    private String gptApi;
    private boolean needPassword;

    private String password;

    /**
     * Constructor for user config
     * @param gptTokens max number of tokens in GPT
     * @param gptApi api of GPT
     * @param gptModel model of GPT
     * @param needPassword is need password
     */
   public UserConfig(int gptTokens, String gptApi, String gptModel, boolean needPassword){
       this.gptApi = gptApi;
       this.gptTokens = gptTokens;
       this.needPassword = needPassword;
       this.gptModel = gptModel;
       this.password = "";
   }

    /**
     * Constructor for user config
     * @param gptTokens max number of tokens in GPT
     * @param gptApi api of GPT
     * @param gptModel model of GPT
     * @param needPassword is need password
     * @param password password
     */
    public UserConfig(int gptTokens, String gptApi, String gptModel, boolean needPassword, String password){
        this.gptApi = gptApi;
        this.gptTokens = gptTokens;
        this.needPassword = needPassword;
        this.gptModel = gptModel;
        this.password = password;
    }

    /**
     * Get number of max tokens in GPT
     * @return number of max tokens in GPT
     */
    public int getGptTokens() {
        return gptTokens;
    }

    /**
     * Get api of GPT
     * @return api of GPT
     */
    public String getGptApi() {
        return gptApi;
    }

    /**
     * Get model of GPT
     * @return model of GPT
     */
    public String getGptModel() {
        return gptModel;
    }

    /**
     * Get is need password
     * @return is need password
     */
    public boolean isNeedPassword() {
        return needPassword;
    }

    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
