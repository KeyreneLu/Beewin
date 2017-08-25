package com.whale.beewin.activity.invest;

import android.os.Bundle;
import android.widget.ImageView;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class EnsureActivity extends BaseActivity {
    @Bind(R.id.iv1)
    ImageView mIv1;
    @Bind(R.id.iv2)
    ImageView mIv2;
    @Bind(R.id.iv3)
    ImageView mIv3;
    @Bind(R.id.iv4)
    ImageView mIv4;
    @Bind(R.id.iv5)
    ImageView mIv5;
    @Bind(R.id.iv6)
    ImageView mIv6;
    @Bind(R.id.iv7)
    ImageView mIv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensure);
        initView();
    }

    private void initView() {
//        Glide.with(this).load(R.drawable.aqbza).asBitmap().dontAnimate().into(mIv1);
//        Glide.with(this).load(R.drawable.aqbzb).asBitmap().dontAnimate().into(mIv2);
//        Glide.with(this).load(R.drawable.aqbzc).asBitmap().dontAnimate().into(mIv3);
//        Glide.with(this).load(R.drawable.aqbzd).asBitmap().dontAnimate().into(mIv4);
//        Glide.with(this).load(R.drawable.aqbze).asBitmap().dontAnimate().into(mIv5);
//        Glide.with(this).load(R.drawable.aqbzf).asBitmap().dontAnimate().into(mIv6);
//        Glide.with(this).load(R.drawable.aqbzg).asBitmap().dontAnimate().into(mIv7);

//        mIv1.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbza,100,100));
//        mIv2.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbzb,100,100));
//        mIv3.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbzc,100,100));
//        mIv4.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbzd,100,100));
//        mIv5.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbze,100,100));
//        mIv6.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbzf,100,100));
//        mIv7.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.aqbzg,100,100));
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
