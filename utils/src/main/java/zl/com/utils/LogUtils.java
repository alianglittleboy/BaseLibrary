
package zl.com.utils;
import android.util.Log;

/**
 * @ClassName test
 * @Description Log日志打印操作
 * @Author zhangliang
 * @Date 2019/5/14 15:53
 * @Version 1.0
 */

public class LogUtils {
    private static final String TAG="LogUtils";
    private static  boolean DEBUG = true;

    /**
     * debug日志开关，ture 允许打印日志 false 禁止打印日志
     * @param isOpen
     */
    public static void openDebug(boolean isOpen){
        DEBUG=isOpen;
    }


    public static void w(String logString) {
        if (DEBUG) {
            Log.w(TAG, logString);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * error log
     *
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }
    /**
     * error log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String tag, String logString) {
        if (DEBUG) {
            Log.i(tag, logString);
        }
    }



    public static void w(String tag, String logString) {
        if (DEBUG) {
            Log.w(tag, logString);
        }
    }

}
