package com.future.association.community.utils;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class JSONUtils {

    private static Gson gson;

    private JSONUtils() {
    }

    private static JSONUtils instance;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * json格式的字符串转JSONObject
     *
     * @auther Leo
     * created at 2016/8/2 0002 13:14
     */
    public static JSONObject strToJsonObject(String jsonStr) {
        JSONObject jsonObject = null;
        if (TextUtil.isEmpty(jsonStr)) {
            throw new RuntimeException("the param is empty");
        } else {
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 根据key获取值
     *
     * @auther Leo
     * created at 2016/8/2 0002 13:21
     */
    public static String getValueForKey(String jsonString, String key) {
        JSONObject jsonObj = strToJsonObject(jsonString);
        try {
            return jsonObj.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }

    /**
     * 根据key 获取值
     *
     * @param object
     * @param key
     * @return
     */
    public static String getValueForKey(JSONObject object, String key) {
        try {
            return object.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }

    /**
     * json转对象
     *
     * @auther Leo
     * created at 2016/8/2 0002 13:49
     */
    public static Object jsonToObject(String jsonStr, Class clazz) {
        if (!TextUtil.isEmpty(jsonStr) && clazz != null) {
            return gson.fromJson(jsonStr, clazz);
        } else {
            return null;
        }
    }

    /**
     * 将对象转化Json对象
     *
     * @auther Leo
     * created at 2016/8/2 0002 14:31
     */
    public static JSONObject objToJson(Object obj) {

        return strToJsonObject(gson.toJson(obj));
    }

    public static JSONArray stringToArray(String json) {
        if (!TextUtil.isEmpty(json)) {
            try {
                return new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return null ;
            }
        }else{
            return null ;
        }
    }

    public static ArrayList jsonToArrayObj(String str, Class clazz) {
        if (!TextUtil.isEmpty(str)) {
            JSONArray jsonArray;
            ArrayList list = new ArrayList();
            try {
                jsonArray = new JSONArray(str);
                JSONObject jsonObject;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    list.add(gson.fromJson(jsonObject.toString(), clazz));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
        return null;
    }
    public static byte[] readParse(String urlPath) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] data = new byte[1024];

        int len = 0;

        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(30000);
        conn.setConnectTimeout(30000);
        InputStream inStream;
        if (conn.getResponseCode() == 200) {
            inStream = conn.getInputStream();
            while ((len = inStream.read(data)) != -1) {

                outStream.write(data, 0, len);

            }

            inStream.close();

        } else {
        }


        return outStream.toByteArray();

    }
    public static String getStringFromInputStream(InputStream inputStream) {
        String resultData = null;      //需要返回的结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }
}
