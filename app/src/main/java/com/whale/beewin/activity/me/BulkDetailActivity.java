package com.whale.beewin.activity.me;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.adapter.ReturnAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Return;
import com.whale.beewin.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class BulkDetailActivity extends BaseActivity  {

    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tv_scatter_name)
    TextView mTvScatterName;
    @Bind(R.id.iv_scatter_status)
    ImageView mIvScatterStatus;
    @Bind(R.id.tv_bulk_precent)
    TextView mTvBulkPrecent;
    @Bind(R.id.tv_bulk_time)
    TextView mTvBulkTime;
    @Bind(R.id.tv_bulk_way)
    TextView mTvBulkWay;
    @Bind(R.id.tv_bulk_start)
    TextView mTvBulkStart;
    @Bind(R.id.tv_bulk_end)
    TextView mTvBulkEnd;
    @Bind(R.id.tv_bulk_sum)
    TextView mTvBulkSum;
    @Bind(R.id.tv_bulk_lucre)
    TextView mTvBulkLucre;
    @Bind(R.id.tv_bulk_person)
    TextView mTvBulkPerson;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Bind(R.id.list)
    ListView mList;
    private String id;
    private String pwd;//用户密码
    private String login;//用户名
    ArrayList<Return.MlistBean> datas = new ArrayList<>();
    ReturnAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulkdetail);
        id = getIntent().getStringExtra("id");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    private void initView() {
        mTvBulkPerson.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
      mTvBulkPerson.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent mIntent = new Intent(BulkDetailActivity.this, WithNoCookieActivity.class);
              mIntent.putExtra("url", "http://m1.judayouyuan.com/content/userneedmoney.htm");
              mIntent.putExtra("title", "借款人详情");
              startActivity(mIntent);
          }
      });
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getScatterItems(login, pwd, id)
                .subscribe(newSubscriber(new Action1<Return>() {
                    @Override
                    public void call(Return aReturn) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        mTitle.setText("散投" + aReturn.getDetail().getDeal_sn());
                        mTvScatterName.setText(aReturn.getDetail().getDeal_sn());
                        mTvBulkPrecent.setText(aReturn.getDetail().getYearper()+"");
                        mTvBulkTime.setText(aReturn.getDetail().getShichang() + "个月");
                        mTvBulkStart.setText(aReturn.getDetail().getAdd_time().substring(0, 10));
                        mTvBulkStart.setText(aReturn.getDetail().getEnd_time().substring(0, 10));
                        mTvBulkSum.setText("￥" + aReturn.getDetail().getTamount());
                        mTvBulkLucre.setText("￥" + aReturn.getDetail().getGamount());
                        datas.addAll(aReturn.getMlist());
                        mAdapter = new ReturnAdapter(activity, datas);
                        mList.setAdapter(mAdapter);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.iv_bulk_type)
    void bulkType(){
        Intent intent = new Intent(BulkDetailActivity.this, BulkRuleActivity.class);
        startActivity(intent);
    }

}
