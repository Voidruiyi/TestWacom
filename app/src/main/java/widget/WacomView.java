package widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ruiyi on 2017/9/16.
 * //手绘板
 */

public class WacomView extends View {

    private Path mPath;
    private float mPreX, mPreY;
    private Paint mPaint;

    public WacomView(Context context) {
        this(context, null);
    }

    public WacomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public WacomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }


    //暴露出去的方法

    /**
     * 重绘
     */
    public void reset() {
        mPath.reset();
        postInvalidate();
    }

    /**
     * 获取图片
     */
    public Bitmap save() {
        //生成图片宽高  需要自己测量
        int w = 1080, h = 960;
        Bitmap b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawPath(mPath, mPaint);
        return b;

    }

    /**
     * 获取图片
     */
    public Bitmap save2() {
        //生成图片宽高  需要自己测量
        this.setDrawingCacheEnabled(true);
        Bitmap b = copyBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return b;

    }

    private Bitmap copyBitmap(Bitmap src) {
        Bitmap rst = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
                src.getConfig());
        Canvas canvas = new Canvas(rst);
        Paint paint = new Paint();
        canvas.drawBitmap(src, 0, 0, paint);

        return rst;
    }
}
