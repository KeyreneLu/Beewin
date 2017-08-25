package com.whale.beewin.activity.found;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.CoinAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Coin;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.XListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

import static com.whale.beewin.utils.Constant.BUG_FLAG;

/**
 * 我的花币
 * Created by Administrator on 2017/4/4 0004.
 */

public class MyCoinActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.tv_coin_number)
    TextView mTvCoinNumber;
    @Bind(R.id.listview_coin)
    XListView mListviewCoin;
    @Bind(R.id.main_content)
    LinearLayout mMainContent;

    private CoinAdapter mAdapter;//花币记录适配器
    private ArrayList<Coin.SlistBean> datas = new ArrayList<>();
    private String pwd;//用户密码
    private String login;//用户名
    private int  page = 1;//页数
    Handler mHandler;
    private int xListStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoin);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    private void initView() {
        mListviewCoin.setPullLoadEnable(true);
        mListviewCoin.setXListViewListener(this);
        mHandler = new Handler();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getCoin(login,pwd,page+"")
                .subscribe(newSubscriber(new Action1<Coin>() {
                    @Override
                    public void call(Coin coin) {
                        mLlLoading.setVisibility(View.GONE);
                        mMainContent.setVisibility(View.VISIBLE);
                        if (coin.getSlist() == null){
                            if (xListStatus != 0){
                                showToast("没有更多数据!");
                            }
                        }else {
                            if (xListStatus == 0){
                                datas.clear();
                                datas.addAll(coin.getSlist());
                            }else {
                                datas.addAll(coin.getSlist());
                            }
                            mAdapter = new CoinAdapter(activity,datas);
                            mListviewCoin.setAdapter(mAdapter);
                        }
                        mTvCoinNumber.setText(coin.getJifen());
                        SPUtils.put(BeewinApp.getContext(),"jifen",Integer.parseInt(coin.getJifen()));
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onRefresh() {
        xListStatus = 0;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                httpData();
                onLoad();
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        xListStatus =2;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                httpData();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mListviewCoin.stopRefresh();
        mListviewCoin.stopLoadMore();
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.btn_coin_bug)
    void bugCoin(){
        startActivityForResult(new Intent(activity, RechargeActivity.class), BUG_FLAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode){
                case BUG_FLAG:
                    mLlLoading.setVisibility(View.VISIBLE);
                    mMainContent.setVisibility(View.GONE);
                    datas.clear();
                    xListStatus = 0;
                    httpData();
                    break;
            }
        }
    }
}
