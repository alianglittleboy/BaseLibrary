package zl.com.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swd
 * @date 2019/5/5/005
 * @time 13:06
 * @description
 */

public class FileUtil {

    public static List<String> getConfig(String strFilePath) {
        List<String> ipList = new ArrayList<>();
        Log.e("TestFile", strFilePath);
        String path = strFilePath;
        ipList.clear();
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            Log.e("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        Log.e("TestFile", line);
                        ipList.add(line);
                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                Log.e("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.e("TestFile", e.getMessage());
            }
        }
        return ipList;
    }
}
