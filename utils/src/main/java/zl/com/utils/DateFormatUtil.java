package zl.com.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by M_genius on 2018/7/13.\
 * 描述:
 * 时间格式化工具类
 */

public class DateFormatUtil {
    public static String format(long timestamp, DateFormatEnum df) {
        Timestamp ts = new Timestamp(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat(df.getFormat(), Locale.CHINA);
        try {
            return dateFormat.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatUnixTime(long unixTime, DateFormatEnum df) {
        long timestamp = unixTime * 1000;
        return format(timestamp, df);
    }

    public enum DateFormatEnum {

        ymdhmss("yyyy-MM-dd HH:mm:ss:SSS"),
        ymdhms("yyyy-MM-dd HH:mm:ss"),
        ymdhm("yyyy-MM-dd HH:mm"),
        ymd("yyyy-MM-dd"),
        ymdWeek("yyyy-MM-dd E"),
        hms("HH:mm:ss");

        private String format;

        DateFormatEnum(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
