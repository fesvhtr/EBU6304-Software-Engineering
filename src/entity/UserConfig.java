package entity;

import javax.jws.soap.SOAPBinding;

public class UserConfig {
    private int gptTokens;
    private String gptModel;
    private String gptApi;
    private boolean needPassword;

   public UserConfig(int gptTokens, String gptApi, String gptModel, boolean needPassword){
       this.gptApi = gptApi;
       this.gptTokens = gptTokens;
       this.needPassword = needPassword;
       this.gptModel = gptModel;
   }

    public int getGptTokens() {
        return gptTokens;
    }

    public String getGptApi() {
        return gptApi;
    }

    public String getGptModel() {
        return gptModel;
    }

    public boolean isNeedPassword() {
        return needPassword;
    }
}
