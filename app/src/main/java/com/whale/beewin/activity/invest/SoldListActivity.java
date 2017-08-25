package com.whale.beewin.activity.invest;

import android.os.Bundle;
import android.os.Handler;

import com.whale.beewin.R;
import com.whale.beewin.adapter.SoldAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Sold;
import com.whale.beewin.view.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 购买列表
 * Created by Administrator on 2017/4/4 0004.
 */

public class SoldListActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.list)
    XListView mList;
    List<Sold.MlistBean> list = new ArrayList<>();//数据集合
    Handler mHandler;//handler
    private int xListStatus = 0;//加载状态
    SoldAdapter mAdapter;//适配器
    private int page;//页数
    private String id;//产品id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldlist);
        id = getIntent().getStringExtra("id");
        initView();
        httpData();
    }

    private void initView() {
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);
        mHandler = new Handler();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getSold(id,page+"")
                .subscribe(newSubscriber(new Action1<Sold>() {
                    @Override
                    public void call(Sold sold) {
                        if (sold.getMlist() == null){
                            if (xListStatus == 0){
                                showToast("暂无购买人员!");
                            }else {
                                showToast("没有更多数据!");
                            }
                        }else {
                            if (xListStatus == 0){
                                list.clear();
                                list.addAll(sold.getMlist());
                            }else {
                                list.addAll(sold.getMlist());
                            }
                            mAdapter = new SoldAdapter(list,activity);
                            mList.setAdapter(mAdapter);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }


    @Override
    public void onRefresh() {
        xListStatus = 0;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               httpData();
                onLoad();
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        xListStatus = 1;
        page++;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               httpData();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }
}
