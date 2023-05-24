package util;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Gson
 */
public class GsonUtil {
    /**
     * Convert object to json string
     * @param object object to be converted
     * @return json string
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    /**
     * Convert json string to object
     * @param json json string
     * @param c class of object
     * @return object
     */
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

    /**
     * Convert json string to object list
     * @param js json string
     * @param c class of object
     * @return object list
     */
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