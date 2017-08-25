package com.whale.beewin.activity.invest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目详情
 * Created by Administrator on 2017/4/4 0004.
 */

public class ImageActivity extends BaseActivity {
    @Bind(R.id.iv_detail)
    ImageView mIvDetail;
    @Bind(R.id.btn_join_now)
    Button mBtnJoinNow;
    private String pic;//图片地址
    private boolean IsBug;//是否可买
    private String id;//产品id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        pic = getIntent().getStringExtra("pic");
        IsBug = getIntent().getBooleanExtra("bug", true);
        id = getIntent().getStringExtra("id");
        initView();
    }

    private void initView() {
        if (!IsBug) {
            mBtnJoinNow.setEnabled(false);
            mBtnJoinNow.setBackgroundResource(R.drawable.rect_gray6);
        }
        Glide.with(activity)
                .load(pic)
                .into(mIvDetail);
    }

    @OnClick(R.id.btn_join_now)
    void joinNow(){
        Intent intent = new Intent(activity,InvestBugActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
