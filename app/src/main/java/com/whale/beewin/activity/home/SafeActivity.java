package com.whale.beewin.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.activity.invest.SimulatorActivity;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class SafeActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.tv3)
    TextView mTv3;
    @Bind(R.id.tv4)
    TextView mTv4;
    @Bind(R.id.tv5)
    TextView mTv5;
    @Bind(R.id.tv6)
    TextView mTv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        initView();
    }

    private void initView() {
        SpannableString spanText = new SpannableString(mTv2.getText().toString());

        spanText.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
            @Override
            public void onClick(View widget) {
//                http://www.zyxthb.com/index.php
                Intent mIntent_shnagcheng = new Intent(SafeActivity.this, WithNoCookieActivity.class);
                mIntent_shnagcheng.putExtra("url", "http://www.tensynad.com/index.html");
                mIntent_shnagcheng.putExtra("title", "腾信股份");
                startActivity(mIntent_shnagcheng);

            }
        },spanText.length() - 4, spanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        mTv2.setText(spanText);
        mTv2.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件


        SpannableString spanText2 = new SpannableString(mTv3.getText().toString());
        spanText2.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(activity, SimulatorActivity.class));

            }
        },spanText2.length() - 4, spanText2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        mTv3.setText(spanText2);
        mTv3.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件

        SpannableString spanText3 = new SpannableString(mTv4.getText().toString());
        spanText3.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(activity,PictureActivity.class));

            }
        },spanText3.length() - 7, spanText3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        mTv4.setText(spanText3);
        mTv4.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件


        SpannableString spanText4 = new SpannableString(mTv5.getText().toString());
        spanText4.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
            @Override
            public void onClick(View widget) {
                Intent mIntent_shnagcheng = new Intent(SafeActivity.this, WithNoCookieActivity.class);
                mIntent_shnagcheng.putExtra("url", "http://www.hkbchina.com/portal/zh_CN/home/index.html");
                mIntent_shnagcheng.putExtra("title", "汉口银行");
                startActivity(mIntent_shnagcheng);

            }
        },spanText4.length() - 4, spanText4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        spanText4.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
            @Override
            public void onClick(View widget) {
                Intent mIntent_shnagcheng = new Intent(SafeActivity.this, WithNoCookieActivity.class);
                mIntent_shnagcheng.putExtra("url", "http://www.lianlianpay.com");
                mIntent_shnagcheng.putExtra("title", "连联支付");
                startActivity(mIntent_shnagcheng);

            }
        },spanText4.length() - 9, spanText4.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        mTv5.setText(spanText4);
        mTv5.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
