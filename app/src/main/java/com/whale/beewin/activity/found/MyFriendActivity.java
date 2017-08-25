package com.whale.beewin.activity.found;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.fragment.ContactFragment;
import com.whale.beewin.fragment.InviteFragment;
import com.whale.beewin.fragment.RewardFragment;
import com.whale.beewin.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的人脉
 * Created by Administrator on 2017/4/5 0005.
 */

public class MyFriendActivity extends BaseActivity{
    @Bind(R.id.tv_contacts_statistics)
    TextView mTvContactsStatistics;
    @Bind(R.id.tv_network_reward)
    TextView mTvNetworkReward;
    @Bind(R.id.tv_invite_friends)
    TextView mTvInviteFriends;
    @Bind(R.id.viewpager)
    NoScrollViewPager mViewpager;

    private ArrayList<BaseFragment> listdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriend);
        initView();
    }

    private void initView() {
        listdata = new ArrayList<>();
        listdata.add(new ContactFragment());
        listdata.add(new RewardFragment());
        listdata.add(new InviteFragment());
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(tabAdapter);
        mViewpager.setCurrentItem(0);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.tv_contacts_statistics)
    void contact(){
        mTvContactsStatistics.setBackgroundResource(R.color.white);
        mTvContactsStatistics.setTextColor(getResources().getColor(R.color.two_blue_color));
        mTvNetworkReward.setBackgroundResource(R.color.bg);
        mTvNetworkReward.setTextColor(getResources().getColor(R.color.six_color));
        mTvInviteFriends.setBackgroundResource(R.color.bg);
        mTvInviteFriends.setTextColor(getResources().getColor(R.color.six_color));
        mViewpager.setCurrentItem(0,false);
    }

    @OnClick(R.id.tv_network_reward)
    void reward(){
        mTvContactsStatistics.setBackgroundResource(R.color.bg);
        mTvContactsStatistics.setTextColor(getResources().getColor(R.color.six_color));
        mTvNetworkReward.setBackgroundResource(R.color.white);
        mTvNetworkReward.setTextColor(getResources().getColor(R.color.two_blue_color));
        mTvInviteFriends.setBackgroundResource(R.color.bg);
        mTvInviteFriends.setTextColor(getResources().getColor(R.color.six_color));
        mViewpager.setCurrentItem(1,false);
    }

    @OnClick(R.id.tv_invite_friends)
    void invite(){
        mTvContactsStatistics.setBackgroundResource(R.color.bg);
        mTvContactsStatistics.setTextColor(getResources().getColor(R.color.six_color));
        mTvNetworkReward.setBackgroundResource(R.color.bg);
        mTvNetworkReward.setTextColor(getResources().getColor(R.color.six_color));
        mTvInviteFriends.setBackgroundResource(R.color.white);
        mTvInviteFriends.setTextColor(getResources().getColor(R.color.two_blue_color));
        mViewpager.setCurrentItem(2,false);
    }


    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listdata.get(position);
        }

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
}
