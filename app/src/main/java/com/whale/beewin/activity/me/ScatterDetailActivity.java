package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.BulkAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Bulk;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class ScatterDetailActivity extends BaseActivity {

    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    private String id;
    private String pwd;//用户密码
    private String login;//用户名
    private ArrayList<Bulk.MlistBean> datas = new ArrayList<>();
    private BulkAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatterdetail);
        id = getIntent().getStringExtra("id");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getScatterDetail(login,pwd,id)
                .subscribe(newSubscriber(new Action1<Bulk>() {
                    @Override
                    public void call(Bulk bulk) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        datas.addAll(bulk.getMlist());
                        mAdapter = new BulkAdapter(activity,datas);
                        mList.setAdapter(mAdapter);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
