package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 22:46
 */
public class IdGenerator {

    public static synchronized String getCode() {
        SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmssSS"); // 时间字符串产生方式，精确到毫秒，防止重复
        String uid = format.format(new Date());
        return uid;
    }
}
