package controller;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import entity.UserConfigManager;


public class GPTController {
    public static String generateText(String prompt) {

        if (prompt.equals("")){
            return "Input is null";
        }

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
        postData.put("messages", new JSONObject[]{new JSONObject().put("role", "user").put("content", prompt)});
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


