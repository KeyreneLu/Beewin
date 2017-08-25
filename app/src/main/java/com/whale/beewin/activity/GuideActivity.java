package com.whale.beewin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.GuideAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 引导界面
 * Created by Administrator on 2017/3/23 0023.
 */

public class GuideActivity extends BaseActivity {
    @Bind(R.id.vp_guide)
    ViewPager mVpGuide;

    //图片资源
    private int[] resIds = {R.drawable.yindaoone, R.drawable.yindaotwo, R.drawable.yindaothree, R.drawable.yindaofour};

    private List<ImageView> list;//给viewpager添加的ImageView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window =this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            /**
             * 动态添加引导图片
             */
            ImageView img = new ImageView(this);
            img.setImageResource(resIds[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams lpImg = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(lpImg);
            list.add(img);
        }

        list.get(resIds.length - 1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(BeewinApp.getContext(),"IsFirst",false);
                startActivity(new Intent(activity, MainActivity.class));
                finish();
            }
        });

        GuideAdapter adapter = new GuideAdapter(list);
        mVpGuide.setAdapter(adapter);
        mVpGuide.setPageTransformer(false, new ZoomOutPageTransformer());
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(0);
                }

            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setScaleX(scaleFactor);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setScaleY(scaleFactor);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                }

            } else { // (1,+Infinity]
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(0);
                }
            }
        }
    }
}
