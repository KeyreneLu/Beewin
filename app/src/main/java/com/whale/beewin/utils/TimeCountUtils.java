package com.whale.beewin.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.whale.beewin.R;


/**
 * 验证码倒数计时器
 * Created by Administrator on 2016/8/16 0016.
 */
public class TimeCountUtils extends CountDownTimer {
    private Activity mActivity;
    private Button btn;//按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtils(Activity mActivity, long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn =btn;
    }


    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + "秒后可重新发送");//设置倒计时时间

        //设置按钮为灰色，这时是不能点击的
        btn.setBackground(mActivity.getResources().getDrawable(R.drawable.rect_gray4));
        SpannableStringBuilder style=new SpannableStringBuilder(btn.getText().toString());//获取按钮的文字
        style.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
        btn.setText(style);
    }

    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        btn.setText("重新获取");
        btn.setClickable(true);//重新获得点击
        btn.setBackground(mActivity.getResources().getDrawable(R.drawable.rect_blue_normal));//还原背景色
    }

}