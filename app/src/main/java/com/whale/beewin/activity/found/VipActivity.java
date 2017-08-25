package com.whale.beewin.activity.found;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.whale.beewin.R;
import com.whale.beewin.activity.MainActivity;
import com.whale.beewin.activity.NoTitleCookieActivity;
import com.whale.beewin.activity.invest.SimulatorActivity;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * vip升级界面
 * Created by Administrator on 2017/4/4 0004.
 */

public class VipActivity extends BaseActivity {
    @Bind(R.id.vip_picture)
    ImageView mVipPicture;
    int[] mImages = new int[]{R.drawable.vip_aa, R.drawable.vip_bb, R.drawable.vip_cc, R.drawable.vip_dd, R.drawable.vip_ee, R.drawable.vip_ff};
    int vip;//vip等级
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        vip = getIntent().getIntExtra("vip", 0);
        mVipPicture.setImageResource(mImages[vip]);
    }

    @OnClick(R.id.tv_go_up)
    void goUp(){
        switch (vip){
            case 0:
                startActivity(new Intent(activity, SimulatorActivity.class));
                break;
            case 1:
                startActivity(new Intent(activity, MyFriendActivity.class));
                break;
            case 2:
                MainActivity.mViewpager.setCurrentItem(1, false);
                MainActivity.mRadioInvest.setChecked(true);
                finish();
                break;
            case 3:
                startActivity(new Intent(activity, MyFriendActivity.class));
                break;
            case 4:
                MainActivity.mViewpager.setCurrentItem(1, false);
                MainActivity.mRadioInvest.setChecked(true);
                finish();
                break;
            case 5:
                startActivity(new Intent(activity, MyFriendActivity.class));
                break;
        }
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.tv_all_droit)
    void droit(){
        Intent intent = new Intent(activity, NoTitleCookieActivity.class);
        intent.putExtra("url", "http://m1.judayouyuan.com/content/vip.htm");
        intent.putExtra("title", "会员福利");
        startActivity(intent);
    }
}
