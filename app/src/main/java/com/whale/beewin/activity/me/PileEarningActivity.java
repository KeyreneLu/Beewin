package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.EarningAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Earning;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 累计收益
 * Created by Administrator on 2017/4/10 0010.
 */

public class PileEarningActivity extends BaseActivity {
    @Bind(R.id.tv_pile_earning)
    TextView mTvPileEarning;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_me_none)
    LinearLayout mLlMeNone;
    @Bind(R.id.list)
    ListView mList;
    private String pwd;//用户密码
    private String login;//用户名
    EarningAdapter mAdapter;
    List<Earning.MlistBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pileearning);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getUserEarning(login,pwd)
                .subscribe(newSubscriber(new Action1<Earning>() {
                    @Override
                    public void call(Earning earning) {
                        mLlLoading.setVisibility(View.GONE);
                        mTvPileEarning.setText(earning.getUcmoney());
                        if (earning.getMlist() == null||earning.getMlist().size() == 0){
                            mLlMeNone.setVisibility(View.VISIBLE);
                        }else {
                            mList.setVisibility(View.VISIBLE);
                            list.addAll(earning.getMlist());
                            mAdapter = new EarningAdapter(activity,list);
                            mList.setAdapter(mAdapter);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
