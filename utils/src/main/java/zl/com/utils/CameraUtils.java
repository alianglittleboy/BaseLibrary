package zl.com.utils;

import android.hardware.Camera;
import android.util.Log;

import java.util.List;

/**
 * @ClassName CameraUtils
 * @Description TODO
 * @Author zhangliang
 * @Date 2019/5/21 13:55
 * @Version 1.0
 */
public class CameraUtils {

    public void pringCameraPix(Camera camera){
        Camera.Parameters params = camera.getParameters();
        params.setPreviewSize(1080, 1920);
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        List<Camera.Size> pictureSizes = params.getSupportedPictureSizes();
        int length = pictureSizes.size();
        for (int m = 0; m < length; m++) {
            Log.e("SupportedPictureSizes", "SupportedPictureSizes : width=" + pictureSizes.get(m).width + ",height=" + pictureSizes.get(m).height);
        }
    }
}
