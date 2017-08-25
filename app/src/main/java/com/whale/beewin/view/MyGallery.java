package com.whale.beewin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {

    public MyGallery(Context context) {
        super(context);
    }

    public MyGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /**
         * return false; 只可以滑动一张图牿
         * */
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
