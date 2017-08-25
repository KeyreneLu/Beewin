package com.whale.beewin.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.fragment.FoundFragment;
import com.whale.beewin.fragment.HomeFragment;
import com.whale.beewin.fragment.InvestFragment;
import com.whale.beewin.fragment.LoanFragment;
import com.whale.beewin.fragment.MeFragment;
import com.whale.beewin.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面
 * Created by Administrator on 2017/3/24 0024.
 */

public class MainActivity extends BaseActivity {
    public static NoScrollViewPager mViewpager;
    RadioGroup mRadioGroup1;
    public static  RadioButton mRadioHome;
    public static RadioButton mRadioInvest;
    public static RadioButton mRadioLoan;
    public static RadioButton mRadioFound;
    public static RadioButton mRadioMe;
    List<BaseFragment> mFragments = new ArrayList<>();//fragment集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initView();
    }

    private void initView() {
        mViewpager  = (NoScrollViewPager) findViewById(R.id.viewpager);
        mRadioHome = (RadioButton) findViewById(R.id.radio_home);
        mRadioInvest = (RadioButton) findViewById(R.id.radio_invest);
        mRadioLoan = (RadioButton) findViewById(R.id.radio_loan);
        mRadioFound = (RadioButton) findViewById(R.id.radio_found);
        mRadioMe = (RadioButton) findViewById(R.id.radio_me);
        mRadioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);

        HomeFragment homeFragment = new HomeFragment();
        mFragments.add(homeFragment);

        InvestFragment investFragment = new InvestFragment();
        mFragments.add(investFragment);

        LoanFragment loanFragment = new LoanFragment();
        mFragments.add(loanFragment);

        FoundFragment foundFragment = new FoundFragment();
        mFragments.add(foundFragment);

        MeFragment meFragment = new MeFragment();
        mFragments.add(meFragment);

        TabAdapter mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(0);
//        设置ViewPager不能左右滑动
        mViewpager.setNoScroll(true);

        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_home:
                        mViewpager.setCurrentItem(0, false);
                        break;
                    case R.id.radio_invest:
                        mViewpager.setCurrentItem(1, false);
                        break;
                    case R.id.radio_loan:
                        mViewpager.setCurrentItem(2, false);
                        break;
                    case R.id.radio_found:
                        mViewpager.setCurrentItem(3, false);
                        break;
                    case R.id.radio_me:
                        mViewpager.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    /**
     * 关闭程序提醒
     */
    private long keyBackClickCount;

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                ) {
            switch ((int) keyBackClickCount++) {
                case 0:
                    showToast( "再按一次退出");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    exitApp();
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
