package com.whale.beewin.activity.invest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.activity.home.SafeActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.GoodInfo;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 其他标的详情
 * Created by Administrator on 2017/3/31 0031.
 */

public class InvestDetailActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tv_invest_date)
    TextView mTvInvestDate;
    @Bind(R.id.tv_invest_precent)
    TextView mTvInvestPrecent;
    @Bind(R.id.progressbar2)
    ProgressBar mProgressbar2;
    @Bind(R.id.tv_sold_precent)
    TextView mTvSoldPrecent;
    @Bind(R.id.tv_sold_money)
    TextView mTvSoldMoney;
    @Bind(R.id.tv_start_money)
    TextView mTvStartMoney;
    @Bind(R.id.tv_all_money)
    TextView mTvAllMoney;
    @Bind(R.id.tv_return_way)
    TextView mTvReturnWay;
    @Bind(R.id.tv_back_way)
    TextView mTvBackWay;
    @Bind(R.id.tv_see_contract)
    TextView mTvSeeContract;
    @Bind(R.id.btn_join_now)
    Button mBtnJoinNow;
    private String id;//产品id
    GoodInfo.GoodinfoBean bean;
    private boolean IsBug = true;
    private String pic = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investdetail);
        id = getIntent().getStringExtra("id");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        showLoadingDialog();
        Subscription subscription =wrapper.getDetail(id)
                .subscribe(newSubscriber(new Action1<GoodInfo>() {
                    @Override
                    public void call(GoodInfo goodInfo) {
                        bean = goodInfo.getGoodinfo();
                        mTitle.setText(bean.getName() + "第" + bean.getQishu() + "期");
                        mTvInvestDate.setText(bean.getTotaltime() + bean.getQtype());
                        mTvInvestPrecent.setText(bean.getYearper() + "%");
                        mTvSoldPrecent.setText(bean.getSaled() + "%");
                        mTvSoldMoney.setText(bean.getRemone());
                        mTvStartMoney.setText(bean.getPrice() );
                        mTvAllMoney.setText(bean.getTotalprice() + "");
                        mTvReturnWay.setText(bean.getBegaintime() + "");
                        mTvBackWay.setText(bean.getPaytype());
//                        Log.e("progress",Integer.parseInt(bean.getSaled())+"");
                        mProgressbar2.setProgress(Integer.parseInt(bean.getSaled().split("[.]")[0]));
                        pic = bean.getDetail();
                        if (bean.getSaled().equals("100.00")){
                            mBtnJoinNow.setBackgroundResource(R.drawable.rect_gray6);
                            mBtnJoinNow.setEnabled(false);
                            IsBug = false;
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
    
    @OnClick(R.id.tv_see_contract)
    void seeContract(){
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/content/hetong.htm");
        mIntent.putExtra("title", "蜂赢合同");
        startActivity(mIntent);
    }

    @OnClick(R.id.rl_have_sold)
    void havaSold(){
        Intent intent = new Intent(activity,SoldListActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @OnClick(R.id.rl_invest_detail)
    void investDetail(){
        Intent intent = new Intent(activity,ImageActivity.class);
        intent.putExtra("pic",pic);
        intent.putExtra("bug",IsBug);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @OnClick(R.id.rl_safe_ensure)
    void safeEnsure(){
        startActivity(new Intent(activity, SafeActivity.class));
    }

    @OnClick(R.id.rl_back_source)
    void backSource(){
        startActivity(new Intent(activity, SourceActivity.class));
    }

    @OnClick(R.id.btn_join_now)
    void joinNow(){
        Intent intent = new Intent(activity,InvestBugActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
