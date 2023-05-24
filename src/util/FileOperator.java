package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File operator
 */
public class FileOperator {

    /**
     * read data from file
     *
     * @param fileName：data file name
     * @param c: class of object
     * @return List of object
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
     * write data to file
     *
     * @param object：object to be written
     * @param filename：file name
     * @throws IOException in case of file not found
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
     * write data to file
     *
     * @param filename：file name
     * @throws IOException in case of file not found
     */
    public static void writeData(List objectList, String filename)  {
        try {
            String fileName = "data/" + filename;
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
            for (Object object : objectList) {
                out.write(GsonUtil.toJson(object));
                out.newLine();
            }
            //flush data
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

