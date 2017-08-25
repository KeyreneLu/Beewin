package com.whale.beewin.activity.invest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 散投介绍
 * Created by Administrator on 2017/4/18 0018.
 */

public class IntroduceActivity extends BaseActivity {
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
    @Bind(R.id.iv8)
    ImageView mIv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
    }

    private void initView() {
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) view.getBackground();
//        view.setBackgroundResource(0);
//        bitmapDrawable.setCallback(null);
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//        if(bitmap != null && !bitmap.isRecycled()){
//            bitmap.recycle();
//            bitmap = null;
//        }
//        mIv1.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsa,100,100));
//        mIv2.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsb,100,100));
//        mIv3.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsc,100,100));
//        mIv4.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsd,100,100));
//        mIv5.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjse,100,100));
//        mIv6.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsf,100,100));
//        mIv7.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsg,100,100));
//        mIv8.setImageBitmap(Utils.decodeSampledBitmapFromResource(getResources(),R.drawable.stjsh,100,100));

//            mIv1.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsa));
//            mIv2.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsb));
//            mIv3.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsc));
//            mIv4.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsd));
//            mIv5.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjse));
//            mIv6.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsf));
//            mIv7.setImageBitmap(Utils.readBitMap(activity,R.drawable.stjsg));
//        Glide.with(this).load(R.drawable.stjsa).asBitmap().dontAnimate().into(mIv1);
//        Glide.with(this).load(R.drawable.stjsb).asBitmap().dontAnimate().into(mIv2);
//        Glide.with(this).load(R.drawable.stjsc).asBitmap().dontAnimate().into(mIv3);
//        Glide.with(this).load(R.drawable.stjsd).asBitmap().dontAnimate().into(mIv4);
//        Glide.with(this).load(R.drawable.stjse).asBitmap().dontAnimate().into(mIv5);
//        Glide.with(this).load(R.drawable.stjsf).asBitmap().dontAnimate().into(mIv6);
//        Glide.with(this).load(R.drawable.stjsg).asBitmap().dontAnimate().into(mIv7);
//        Glide.with(this).load(R.drawable.stjsh).asBitmap().dontAnimate().into(mIv8);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.tv_scatter_now)
    void scatter() {
        startActivity(new Intent(activity, ScatterRealActivity.class));
    }

//    @Override
//    protected void onDestroy() {
//        System.gc();
//        super.onDestroy();
//    }
}
