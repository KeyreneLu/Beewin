package com.whale.beewin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;

public class RingView extends View {

    private  Paint paint;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 获取控件的属性值
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RingView);
            mBorderColor = array.getColor(R.styleable.RoundImageView_ml_border_color, mBorderColor);
            array.recycle();
        }
        paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int innerCircle = dip2px(BeewinApp.getContext(), 50); //设置内圆半径
        int ringWidth = dip2px(BeewinApp.getContext(), 7); //设置圆环宽度

        //绘制内圆
        this.paint.setColor(mBorderColor);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, this.paint);
        //绘制圆环
        this.paint.setColor(mBorderColor);
        //绿色     this.paint.setARGB(255, 0 ,255,0);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);
        //绘制外圆
        this.paint.setColor(mBorderColor);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);

        super.onDraw(canvas);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}  
