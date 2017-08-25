package com.whale.beewin.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.whale.beewin.R;
import com.whale.beewin.activity.invest.ScatterRealActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.Utils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 散投福利界面
 * Created by Administrator on 2017/3/28 0028.
 */

public class ScatterWealActivity extends BaseActivity {
    @Bind(R.id.iv1)
    ImageView mIv1;
    @Bind(R.id.iv2)
    ImageView mIv2;
    @Bind(R.id.iv3)
    ImageView mIv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatterweal);
        initView();
    }

    private void initView() {
            mIv1.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjla));
            mIv2.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjlb));
            mIv3.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjlc));
//        Glide.with(this).load(R.drawable.stjla).asBitmap().dontAnimate().into(mIv1);
//        Glide.with(this).load(R.drawable.stjlb).asBitmap().dontAnimate().into(mIv2);
//        Glide.with(this).load(R.drawable.stjlc).asBitmap().dontAnimate().into(mIv3);

//        mIv1.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjla,100,100));
//        mIv2.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjlb,100,100));
//        mIv3.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjlc,100,100));
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.tv_scatter_now)
    void scatterNow() {
        startActivity(new Intent(activity, ScatterRealActivity.class));
    }

//    @Override
//    protected void onDestroy() {
//        System.gc();
//        super.onDestroy();
//    }
}