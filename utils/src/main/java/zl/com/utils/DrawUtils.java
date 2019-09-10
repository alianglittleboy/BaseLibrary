package zl.com.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class DrawUtils {

    public static RectF adjustRect(int[] features, float scaleX, float scaleY) {
        if (features == null && features.length > 0) {
            return null;
        }
        RectF rect = new RectF();
        rect.left = 800-features[0] * scaleX;
        rect.top = features[1] * scaleY;
        rect.right = 800-features[2] * scaleX;
        rect.bottom = features[3] * scaleY;
        return rect;
    }

    public static void drawFaceRect(Canvas canvas, RectF rect, int color, int faceRectThickness) {
        if (canvas == null || rect == null) {
            return;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(faceRectThickness);
        paint.setColor(color);
        Path mPath = new Path();
        mPath.moveTo(rect.left, rect.top + rect.height() / 4);
        mPath.lineTo(rect.left, rect.top);
        mPath.lineTo(rect.left + rect.width() / 4, rect.top);
        mPath.moveTo(rect.right - rect.width() / 4, rect.top);
        mPath.lineTo(rect.right, rect.top);
        mPath.lineTo(rect.right, rect.top + rect.height() / 4);
        mPath.moveTo(rect.right, rect.bottom - rect.height() / 4);
        mPath.lineTo(rect.right, rect.bottom);
        mPath.lineTo(rect.right - rect.width() / 4, rect.bottom);
        mPath.moveTo(rect.left + rect.width() / 4, rect.bottom);
        mPath.lineTo(rect.left, rect.bottom);
        mPath.lineTo(rect.left, rect.bottom - rect.height() / 4);
        canvas.drawPath(mPath, paint);
    }
}
