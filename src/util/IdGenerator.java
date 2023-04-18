package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class IdGenerator {

    public static synchronized String getCode() {
        SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmssSS"); // 时间字符串产生方式，精确到毫秒，防止重复
        String uid = format.format(new Date());
        return uid;
    }
}
