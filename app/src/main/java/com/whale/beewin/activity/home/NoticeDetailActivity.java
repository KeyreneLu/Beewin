package com.whale.beewin.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.NoticeDetail;
import com.whale.beewin.utils.SPUtils;
import com.zzhoujay.richtext.RichText;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 公告详情界面
 * Created by Administrator on 2017/3/28 0028.
 */

public class NoticeDetailActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.tv_detail_name)
    TextView mTvDetailName;
    @Bind(R.id.tv_detail_content)
    TextView mTvDetailContent;
    @Bind(R.id.tv_detail_time)
    TextView mTvDetailTime;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;

    String login;//用户名
    String pwd;//用户密码
    String id;//公告id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        id = getIntent().getStringExtra("id");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getSysDetail(login, pwd, id)
                .subscribe(newSubscriber(new Action1<NoticeDetail>() {
                    @Override
                    public void call(NoticeDetail noticeDetail) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        mTvDetailName.setText(noticeDetail.getMsg().getInfo());
                        RichText.from(noticeDetail.getMsg().getContent()).autoFix(true).into(mTvDetailContent);
                        mTvDetailTime.setText(noticeDetail.getMsg().getAdd_time());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
