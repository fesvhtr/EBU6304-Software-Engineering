package controller;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import entity.*;
import util.GsonUtil;

import javax.swing.*;

/**
 * The controller for the GPT.
 */
public class GPTController {

    /**
     * Change key word in the prompt.
     * @param oldPrompt The old prompt.
     * @param mapItem The key word.
     * @return The new prompt.
     */
    public static String mapPrompt(String oldPrompt, String mapItem){
        oldPrompt = oldPrompt + " ";
        String content = "content";
        String split = "";
        if (mapItem.equals("&module&")){
            split = "&module&";
            content = "modules: " + GsonUtil.toJson(ModuleManager.getInstance().getList());
        } else if(mapItem.equals("&activity&")){
            split= "&activity&";
            content = "activities :" + GsonUtil.toJson(ActivityManager.getInstance().getList());
        }else if (mapItem.equals("&role&")){
            split="&role&";
            content = "roles :" + GsonUtil.toJson(RoleManager.getInstance().getList());
        } else if (mapItem.equals("&skill&")) {
            split = "&skill&";
            content = "skills: " + GsonUtil.toJson(SkillManager.getInstance().getList());
        } else if (mapItem.equals("&achievement")){
            split = "&achievement&";
            content = "achievements: " + GsonUtil.toJson(AchievementManager.getInstance().getList());
        }
        String[] strs = oldPrompt.split(mapItem);
        if (strs.length == 0){
            return content;
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = result + content;
            result = result + strs[i];
        }
        return result;
    }

    /**
     * Update the prompt.
     * @param oldPrompt The old prompt.
     * @return The new prompt.
     */
    public static String refreshPrompt(String oldPrompt){
        String newPrompt = oldPrompt;
        if(oldPrompt.contains("&module&")){
            newPrompt = mapPrompt(oldPrompt, "&module&");
        }
        if (oldPrompt.contains("&activity&")) {
            newPrompt = mapPrompt(oldPrompt, "&activity&");
        }
        if (oldPrompt.contains("&role&")){
            newPrompt = mapPrompt(oldPrompt, "&role&");
        }
        if(oldPrompt.contains("&skill&")){
            newPrompt = mapPrompt(oldPrompt, "&skill&");
        }
        if (oldPrompt.contains("&achievement&")) {
            newPrompt = mapPrompt(oldPrompt, "&achievement&");
        }
        return newPrompt;
    }

    /**
     * Generate the text using GPT based on the prompt.
     * @param prompt The prompt.
     * @return The generated text.
     */
    public static String generateText(String prompt) {

        if (prompt.equals("")){
            return "Input is null";
        }
        String newPrompt = refreshPrompt(prompt);

        String api = UserConfigManager.getInstance().getUserConfig().getGptApi();
        String model = UserConfigManager.getInstance().getUserConfig().getGptModel();
        int maxToken = UserConfigManager.getInstance().getUserConfig().getGptTokens();
        String proxyHost = "localhost";
        int proxyPort = 7890;

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

        String apiUrl = "https://api.openai.com/v1/chat/completions";

        String contentType = "application/json";
        String authorization = "Bearer " + api;

        JSONObject postData = new JSONObject();
        postData.put("model", model);
        postData.put("max_tokens", maxToken);
        postData.put("messages", new JSONObject[]{new JSONObject().put("role", "user").put("content", newPrompt)});
        postData.put("temperature", 0.7);
        try {

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("Authorization", authorization);

            conn.setDoOutput(true);

            conn.getOutputStream().write(postData.toString().getBytes());

            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject result = new JSONObject(response);
            JSONArray jsonArray = result.getJSONArray("choices");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONObject messageObject = jsonObject.getJSONObject("message");

            return messageObject.getStr("content");

        } catch (IOException e) {
            System.out.println(e.toString());
            return "There is something wrong with the network or your api";
        }
    }

}


