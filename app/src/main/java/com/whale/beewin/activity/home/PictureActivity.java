package com.whale.beewin.activity.home;

import android.os.Bundle;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * 分散投资
 * Created by Administrator on 2017/3/29 0029.
 */

public class PictureActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
