package com.future.baselib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jniu on 2017/5/19.
 */
public class CommonUtils {
    /**
     * 获取 base字符串
     * @param imgPath
     * @return
     */
    public static String getImg(String imgPath) {
        File file = new File(imgPath);
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = bitmap2Byte(bitmap);
            JLog.e("img","图片大小"+data.length/1024);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static String getImg(Bitmap bitmap){
        byte[] data = bitmap2Byte(bitmap);
        return Base64.encode(data);
    }

    public static String getImgs(List<String> imgPaths){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < imgPaths.size(); i++) {
            stringBuilder.append(CommonUtils.getImg(imgPaths.get(i))+",");
        }
        return stringBuilder.toString();
    }

    public static byte[] bitmap2Byte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] bytes = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
