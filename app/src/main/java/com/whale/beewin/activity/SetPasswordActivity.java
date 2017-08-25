package com.whale.beewin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.gesture.LocusPassWordView;
import com.whale.beewin.utils.StringUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 手势密码引导界面
 * Created by Administrator on 2017/3/24 0024.
 */

public class SetPasswordActivity extends BaseActivity {
    @Bind(R.id.LocusPassWordView)
    LocusPassWordView mLocusPassWordView;

    private boolean needverify = true;//手势密码验证
    private String mPassword;//手势密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
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
        mLocusPassWordView.setOnCompleteListener(new LocusPassWordView.OnCompleteListener() {
            @Override
            public void onComplete(String password) {
                mPassword = password;
                if (needverify) {
                    if (mLocusPassWordView.verifyPassword(password)) {
                        showToast("密码输入正确,请输入新密码!");
                        mLocusPassWordView.clearPassword();
                        needverify = false;
                    }
                }
            }
        });

        // 如果密码为空,直接输入密码
        if (mLocusPassWordView.isPasswordEmpty()) {
            needverify = false;
            showToast("请输入密码");
        }
    }

    @OnClick(R.id.tvReset)
    void resetPassword() {
        mLocusPassWordView.clearPassword();
    }

    @OnClick(R.id.tvSave)
    void savePassword() {
        if (StringUtil.isNotEmpty(mPassword)) {
            mLocusPassWordView.resetPassWord(mPassword);
            mLocusPassWordView.clearPassword();
            showToast("创建成功,请记住密码.");
            startActivity(new Intent(SetPasswordActivity.this, GestureActivity.class));
            finish();
        } else {
            mLocusPassWordView.clearPassword();
            showToast("密码不能为空,请输入密码.");
        }
    }
}
