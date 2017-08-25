package com.whale.beewin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.gesture.LocusPassWordView;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 手势密码
 * Created by Administrator on 2017/3/24 0024.
 */

public class GestureActivity extends BaseActivity {
    @Bind(R.id.toast)
    TextView mToast;
    @Bind(R.id.LocusPassWordView)
    LocusPassWordView mLocusPassWordView;

    private String login;//用户手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window =this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
    }

    private void initView() {
        mLocusPassWordView.setOnCompleteListener(new LocusPassWordView.OnCompleteListener() {
            @Override
            public void onComplete(String password) {
                if (mLocusPassWordView.verifyPassword(password)) {
                    SPUtils.put(activity, "isShowSetGest", login);
                    SPUtils.put(activity, login, true);
                    showToast("解锁成功!");
                    startActivity(new Intent(activity, MainActivity.class));
                    finish();
                } else {
                    showToast("密码输入错误,请重新输入");
                    mLocusPassWordView.clearPassword();
                }
            }
        });
    }

    @OnClick(R.id.btn_gesture_forget)
    void forgetPassword() {
        if (SPUtils.contains(activity, login)) {
            SPUtils.put(BeewinApp.getContext(), "isShowSetGest", "");
            SPUtils.remove(activity, login);
        }
        startActivity(new Intent(activity, LoginActivity.class));
    }
}
