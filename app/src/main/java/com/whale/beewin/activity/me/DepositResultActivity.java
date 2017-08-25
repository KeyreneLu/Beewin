package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 提现金额
 * Created by Administrator on 2017/4/11 0011.
 */

public class DepositResultActivity extends BaseActivity {
    @Bind(R.id.tx_time)
    TextView mTxTime;
    @Bind(R.id.tx_time2)
    TextView mTxTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositresult);
        initView();
    }

    private void initView() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
        mTxTime.setText(sdf.format(new Date()));
        if (DateUtils.getChinaDayOfWeek(sdf3.format(new Date())).equals("星期五")) {
            if (Integer.valueOf(sdf2.format(new Date())) > 15) {
                mTxTime2.setText(sdf.format(DateUtils.getDateAdd(new Date(), 3)));
            } else {
                mTxTime2.setText(sdf.format(DateUtils.getDateAdd(new Date(), 1)));
            }
        } else if (DateUtils.getChinaDayOfWeek(sdf3.format(new Date())).equals("星期六")) {
            mTxTime2.setText(sdf.format(DateUtils.getDateAdd(new Date(), 2)));
        } else if (DateUtils.getChinaDayOfWeek(sdf3.format(new Date())).equals("星期日")) {
            mTxTime2.setText(sdf.format(DateUtils.getDateAdd(new Date(), 1)));
        } else {
            mTxTime2.setText(sdf.format(DateUtils.getDateAdd(new Date(), 1)));
        }
    }

    @OnClick(R.id.iv_home_right)
    void Finish(){
        finish();
    }
}
