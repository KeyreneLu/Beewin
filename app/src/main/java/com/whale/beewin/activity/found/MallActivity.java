package com.whale.beewin.activity.found;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.VoucherAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Cash;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 花币商城
 * Created by Administrator on 2017/4/5 0005.
 */

public class MallActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.list)
    ListView mList;

    public static final int VOUCHER_BUG = 1;//全局常量
    VoucherAdapter mAdapter;//代金券适配器
    List<Cash.MlistBean> datas = new ArrayList<>();//数据集合
    private int page = 1;//页数
    private String mLogin;
    private String mPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        mLogin = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        mPwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        initView();
        httpData();
    }

    private void initView() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity,VoucherMallActivity.class);
                intent.putExtra("voucher",datas.get(position));
                startActivityForResult(intent,VOUCHER_BUG);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription sub = wrapper.getCash(mLogin,mPwd,page+"")
                .subscribe(newSubscriber(new Action1<Cash>() {
                    @Override
                    public void call(Cash cash) {
                        mLlLoading.setVisibility(View.GONE);
                        mList.setVisibility(View.VISIBLE);
                        for (int i=0;i<cash.getMlist().size();i++){
                            if (cash.getMlist().get(i).getBscore().equals("1")){
                                datas.add(cash.getMlist().get(i));
                            }
                        }
                        for (int j = cash.getMlist().size()-1; j >=0; j--) {
                            if (!cash.getMlist().get(j).getBscore().equals("1")){
                                datas.add(cash.getMlist().get(j));
                            }
                        }
                        mAdapter = new VoucherAdapter(activity, datas);
                        mList.setAdapter(mAdapter);
                    }
                }));
        mCompositeSubscription.add(sub);
    }


    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode){
                case VOUCHER_BUG:
                    mLlLoading.setVisibility(View.VISIBLE);
                    mList.setVisibility(View.GONE);
                    datas.clear();
                    httpData();
                    break;
            }
        }
    }
}
