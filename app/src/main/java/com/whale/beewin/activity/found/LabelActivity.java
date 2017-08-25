package com.whale.beewin.activity.found;

import android.os.Bundle;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * 标签说明
 * Created by Administrator on 2017/4/4 0004.
 */

public class LabelActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
