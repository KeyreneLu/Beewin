package com.whale.beewin.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.NoticeAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Notice;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 公告界面
 * Created by Administrator on 2017/3/28 0028.
 */

public class NoticeActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    String login ;//用户名
    String pwd ;//用户密码
    int page = 1;//页数
    List<Notice.SlistBean> list = new ArrayList<>();
    NoticeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        login = (String) SPUtils.get(BeewinApp.getContext(),"login","");
        pwd = (String) SPUtils.get(BeewinApp.getContext(),"pwd","");
        intiView();
        httpData();
    }

    private void intiView() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent mIntent = new Intent(activity,NoticeDetailActivity.class);
                mIntent.putExtra("id",list.get(position).getId());
                startActivity(mIntent);
            }
        });
    }


    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getSysNotice(login,pwd,page+"")
                .subscribe(newSubscriber(new Action1<Notice>() {
                    @Override
                    public void call(Notice notice) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        if (notice.getSlist() == null){
                            showToast("暂无任何公告");
                        }else {
                            list.addAll(notice.getSlist());
                            mAdapter = new NoticeAdapter(activity,list);
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
