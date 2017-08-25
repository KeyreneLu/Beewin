package com.whale.beewin.activity.found;

import android.content.Intent;
import android.os.Bundle;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.OnClick;

/**
 * 奖励规则
 * Created by Administrator on 2017/4/5 0005.
 */

public class RewardRuleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewardrule);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.tv_invite_more)
    void inviteMore(){
        startActivity(new Intent(RewardRuleActivity.this,ShareActivity.class));
    }
}
