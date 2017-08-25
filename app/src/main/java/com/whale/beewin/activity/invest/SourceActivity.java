package com.whale.beewin.activity.invest;

import android.os.Bundle;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * 还款来源
 * Created by Administrator on 2017/4/4 0004.
 */

public class SourceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
