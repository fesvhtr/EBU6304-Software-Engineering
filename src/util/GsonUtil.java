package util;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 0:00
 */
public class GsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
    public static Object toObj(String json, Class<?> c) {
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        Gson gson = new Gson();
        Object obj = null;
        for(JsonElement item : jsonArray) {
            obj = gson.fromJson(item, c);
        }
        return obj;
    }
    public static List<Object> toObjList(String js, Class<?> c) {
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(js).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<Object> objList = new ArrayList<Object>();
        for(JsonElement item : jsonArray) {
            Object obj = gson.fromJson(item, c);
            objList.add(obj);
        }

        return objList;
    }

}