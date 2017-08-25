package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class BulkRuleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulkrule);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
