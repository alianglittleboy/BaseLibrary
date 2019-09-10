package zl.com.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * @author swd
 * @date 2019/5/15/015
 * @time 13:52
 * @description
 */

public class ImageUtil {
    public static Bitmap getBitmap(Bitmap receiveBitmap, int width, int height, int x, int y) {
        int offset = (int) ((width > height ? height : width) * 0.5);
        width = width + offset;
        height = height + offset;
        if (x < offset / 2) {
            x = 0;
        } else {
            x = x - offset / 2;
        }

        if (y < offset / 2) {
            y = 0;
        } else {
            y = y - offset / 2;
        }
        x = (x < 0) ? 0 : x;
        y = (y < 0) ? 0 : y;

        width = x + width > receiveBitmap.getWidth() ? receiveBitmap.getWidth() - x : width;
        height = y + height > receiveBitmap.getHeight() ? receiveBitmap.getHeight() - y : height;
        return Bitmap.createBitmap(receiveBitmap, x, y, width, height);
    }

    public static Matrix getTransformationMatrix(
            final int srcWidth,
            final int srcHeight,
            final int dstWidth,
            final int dstHeight,
            final int applyRotation,
            final boolean maintainAspectRatio) {
        final Matrix matrix = new Matrix();

        if (applyRotation != 0) {
            // Translate so center of image is at origin.
            matrix.postTranslate(-srcWidth / 2.0f, -srcHeight / 2.0f);

            // Rotate around origin.
            matrix.postRotate(applyRotation);
        }

        // Account for the already applied rotation, if any, and then determine how
        // much scaling is needed for each axis.
        final boolean transpose = (Math.abs(applyRotation) + 90) % 180 == 0;

        final int inWidth = transpose ? srcHeight : srcWidth;
        final int inHeight = transpose ? srcWidth : srcHeight;

        // Apply scaling if necessary.
        if (inWidth != dstWidth || inHeight != dstHeight) {
            final float scaleFactorX = dstWidth / (float) inWidth;
            final float scaleFactorY = dstHeight / (float) inHeight;

            if (maintainAspectRatio) {
                // Scale by minimum factor so that dst is filled completely while
                // maintaining the aspect ratio. Some image may fall off the edge.
                final float scaleFactor = Math.max(scaleFactorX, scaleFactorY);
                matrix.postScale(scaleFactor, scaleFactor);
            } else {
                // Scale exactly to fill dst from src.
                matrix.postScale(scaleFactorX, scaleFactorY);
            }
        }

        if (applyRotation != 0) {
            // Translate back from origin centered reference to destination frame.
            matrix.postTranslate(dstWidth / 2.0f, dstHeight / 2.0f);
        }

        return matrix;
    }

}
