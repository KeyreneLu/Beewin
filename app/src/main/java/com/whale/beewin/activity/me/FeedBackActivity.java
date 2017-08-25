package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.widget.EditText;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class FeedBackActivity extends BaseActivity {
    @Bind(R.id.et_feed_back)
    EditText mEtFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.btn_submit)
    void submit(){
        if (mEtFeedBack.getText().toString().trim().isEmpty()){
            showToast("反馈意见不能为空!");
            return;
        }
        mEtFeedBack.setText("");
        showToast("提交成功!");
        finish();
    }
}
