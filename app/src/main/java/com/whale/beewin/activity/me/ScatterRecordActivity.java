package com.whale.beewin.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.invest.ScatterRealActivity;
import com.whale.beewin.adapter.ScatterAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.ScatterRecord;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class ScatterRecordActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_none)
    LinearLayout mLlNone;
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    private String pwd;//用户密码
    private String login;//用户名
    private int page = 1;//页数
    private List<ScatterRecord.MlistBean> datas = new ArrayList<>();//数据集合
    ScatterAdapter mAdapter;//适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatterrecord);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    private void initView() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScatterRecordActivity.this, ScatterDetailActivity.class);
                intent.putExtra("id", datas.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.button_me_scatter)
    void scatterNow() {
        Intent intent = new Intent(ScatterRecordActivity.this, ScatterRealActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getScatterRecord(login, pwd, page + "")
                .subscribe(newSubscriber(new Action1<ScatterRecord>() {
                    @Override
                    public void call(ScatterRecord scatterRecord) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        if (scatterRecord.getMlist() == null || scatterRecord.getMlist().size() == 0) {
                            mLlNone.setVisibility(View.VISIBLE);
                        } else {
                            mList.setVisibility(View.VISIBLE);
                            datas.addAll(scatterRecord.getMlist());
                            mAdapter = new ScatterAdapter(ScatterRecordActivity.this, datas);
                            mList.setAdapter(mAdapter);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
