package com.whale.beewin.activity.loan;

import android.os.Bundle;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * 借款规则
 * Created by Administrator on 2017/4/11 0011.
 */

public class HonorTipActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honortip);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.btn_loan_now)
    void loan(){

    }
}
