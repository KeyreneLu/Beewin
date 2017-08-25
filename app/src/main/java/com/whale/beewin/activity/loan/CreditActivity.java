package com.whale.beewin.activity.loan;

import android.content.Intent;
import android.os.Bundle;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.NoTitleCookieActivity;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.GradientProgressBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 信用界面
 * Created by Administrator on 2017/4/12 0012.
 */

public class CreditActivity extends BaseActivity {
    @Bind(R.id.progress)
    GradientProgressBar mProgress;
    private int xyd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        xyd = (int) SPUtils.get(BeewinApp.getContext(),"xyd",0);
        mProgress.setPercent(xyd);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.tv_know_honor)
    void knowHonor(){
        Intent intent = new Intent(activity, WithNoCookieActivity.class);
        intent.putExtra("url","http://m1.judayouyuan.com/content/aboutus.htm");
        intent.putExtra("title","了解蜂赢信用");
        startActivity(intent);
    }

    @OnClick(R.id.tv_more_honor)
    void moreHonor(){
        Intent intent = new Intent(activity, WithNoCookieActivity.class);
        intent.putExtra("url","http://m1.judayouyuan.com/content/xingyong.htm");
        intent.putExtra("title","如何提高信用");
        startActivity(intent);
    }

    @OnClick(R.id.tv_perfect_information)
    void Information(){
        Intent intent = new Intent(activity, NoTitleCookieActivity.class);
        intent.putExtra("url","http://m1.judayouyuan.com/index.php?g=App&m=Member&a=oauthverfiy");
        intent.putExtra("title","完善资料提高信用");
        startActivity(intent);
    }
}
