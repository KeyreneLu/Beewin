package com.whale.beewin.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.activity.loan.view.SesameCreditPanel;
import com.whale.beewin.activity.loan.view.SesameItemModel;
import com.whale.beewin.activity.loan.view.SesameModel;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class BalanceActivity extends BaseActivity {
    @Bind(R.id.seSameCreditPanel)
    SesameCreditPanel mSeSameCreditPanel;
    @Bind(R.id.tv_lend_money)
    TextView mTvLendMoney;
    @Bind(R.id.tv_honor_now)
    TextView mTvHonorNow;
    @Bind(R.id.tv_had_money)
    TextView mTvHadMoney;
    @Bind(R.id.tv_loan_number)
    TextView mTvLoanNumber;
    private int xy;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        xy = (int) SPUtils.get(BeewinApp.getContext(), "xy", 0);
        number = (String) SPUtils.get(BeewinApp.getContext(),"number","0.00");
        initView();
    }

    private void initView() {
        mTvLendMoney.setText(xy+"");
        mTvLoanNumber.setText(number);
        mSeSameCreditPanel.setDataModel(getData());
    }

    private SesameModel getData() {
        SesameModel model = new SesameModel();
        model.setUserTotal(xy);
        model.setAssess("可借额度");
        model.setTotalMin(0);
        model.setTotalMax(180000);
        // model.setFirstText("BETA");
        // model.setTopText("因为信用 所以简单");
        // model.setFourText("评估时间:" + formater.format(new Date()));
        ArrayList<SesameItemModel> sesameItemModels = new ArrayList<SesameItemModel>();

        SesameItemModel ItemModel350 = new SesameItemModel();
        ItemModel350.setArea("较差");
        ItemModel350.setMin(350);
        ItemModel350.setMax(36000);
        sesameItemModels.add(ItemModel350);

        SesameItemModel ItemModel550 = new SesameItemModel();
        ItemModel550.setArea("中等");
        ItemModel550.setMin(36000);
        ItemModel550.setMax(72000);
        sesameItemModels.add(ItemModel550);

        SesameItemModel ItemModel600 = new SesameItemModel();
        ItemModel600.setArea("良好");
        ItemModel600.setMin(72000);
        ItemModel600.setMax(108000);
        sesameItemModels.add(ItemModel600);

        SesameItemModel ItemModel650 = new SesameItemModel();
        ItemModel650.setArea("优秀");
        ItemModel650.setMin(108000);
        ItemModel650.setMax(144000);
        sesameItemModels.add(ItemModel650);

        SesameItemModel ItemModel700 = new SesameItemModel();
        ItemModel700.setArea("较好");
        ItemModel700.setMin(144000);
        ItemModel700.setMax(180000);
        sesameItemModels.add(ItemModel700);

        model.setSesameItemModels(sesameItemModels);
        return model;
    }

    @OnClick(R.id.tv_honor_now)
    void improve(){
        startActivity(new Intent(activity,CreditActivity.class));
    }

    @OnClick(R.id.btn_loan_now)
    void loanNow(){
        showToast("暂未开放");
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.rl_loan_record)
    void record(){
        showToast("暂未开放");
    }

    @OnClick(R.id.rl_know_loan)
    void know(){
        startActivity(new Intent(activity,HonorTipActivity.class));
    }

    @OnClick(R.id.rl_loan_number)
    void loanNumber(){
        Intent intent = new Intent(activity, WithNoCookieActivity.class);
        intent.putExtra("url","http://m1.judayouyuan.com/content/touchnums.htm");
        intent.putExtra("title","了解蜂赢借款");
        startActivity(intent);
    }
}
