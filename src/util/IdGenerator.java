package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Utility class for generating id
 */
public class IdGenerator {
    /**
     * Generate id
     * @return id
     */
    public static synchronized String getCode() {
        SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmssSS");
        String uid = format.format(new Date());
        return uid;
    }
}
