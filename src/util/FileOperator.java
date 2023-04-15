package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 23:48
 */
public class FileOperator {

    /**
     * 读取文件
     *
     * @param fileName：data目录下的文件名称
     * @param c:对象类名
     * @return 对象集合
     */
    public static List loadData(String fileName, Class<?> c) {
        List ret = new ArrayList<>();
        try{
            String path = "data/" + fileName;
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = in.readLine()) != null) {
                line = "[" + line + "]";
                Object object =GsonUtil.toObj(line, c);
                ret.add(object);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 写入文件(追加模式)
     *
     * @param object：待存盘的对象
     * @param filename：文件名称
     * @return
     * @throws IOException
     */
    public static void writeData(Object object, String filename)  {
        try {
            String fileName = "data/" + filename;
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(GsonUtil.toJson(object));
            out.newLine();
            //刷新流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件(追加模式)
     *
     * @param object：待存盘的对象数组
     * @param filename：文件名称
     * @return
     * @throws IOException
     */
    public static void writeData(List objectList, String filename)  {
        try {
            String fileName = "data/" + filename;
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
            for (Object object : objectList) {
                out.write(GsonUtil.toJson(object));
                out.newLine();
            }
            //刷新流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

