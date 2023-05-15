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
import entity.ActivityManager;
import entity.ModuleManager;
import entity.RoleManager;
import entity.UserConfigManager;
import util.GsonUtil;

import javax.swing.*;


public class GPTController {

    public static String mapPrompt(String oldPrompt, String mapItem){
        oldPrompt = oldPrompt + " ";
        String content = "content";
        String split = "";
        if (mapItem.equals("&module&")){
            split = "&module&";
            content = "modules: " + GsonUtil.toJson(ModuleManager.getInstance().getModule());
        } else if(mapItem.equals("&activity&")){
            split= "&activity&";
            content = "activities :" + GsonUtil.toJson(ActivityManager.getInstance().getActivities());
        }else if (mapItem.equals("&role&")){
            split="&role&";
            content = "roles :" + GsonUtil.toJson(RoleManager.getInstance().getRoles());
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
        return newPrompt;
    }
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
        String authorization = "Bearer "+ api;

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
            return "There is something wrong with the network or your api";
        }
    }

}


