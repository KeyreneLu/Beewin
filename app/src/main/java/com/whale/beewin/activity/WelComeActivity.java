package com.whale.beewin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 引导界面
 * Created by Administrator on 2017/3/23 0023.
 */
public class WelComeActivity extends BaseActivity {

    private String login;//是否登录过
    private boolean IsShow;//手势密码
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        IsShow = (boolean) SPUtils.get(BeewinApp.getContext(), "isShowJiuGongGe", false);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (preferences.getBoolean("firststart", true)) {
                            editor = preferences.edit();
                            //将登录标志位设置为false,下次登录时不在显示首次登录界面
                            editor.putBoolean("firststart", false);
                            editor.commit();
                            startActivity(new Intent(activity, GuideActivity.class));
                            finish();
                        } else {
                            if (SPUtils.contains(activity,login) && !IsShow) {
                                Intent mainIntent = new Intent(activity, GestureActivity.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Intent intent = new Intent(activity, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
    }
}
