package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class QRCodeActivity extends BaseActivity {
    @Bind(R.id.iv_qr_code)
    ImageView mIvQrCode;
    @Bind(R.id.tv_qr_code)
    TextView mTvQrCode;
    private String ticket;
    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ticket = (String) SPUtils.get(BeewinApp.getContext(),"ticket","");
        uuid = (String) SPUtils.get(BeewinApp.getContext(),"uuid","");
        Glide.with(activity)
                .load(ticket)
                .into(mIvQrCode);
        mTvQrCode.setText(uuid);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
