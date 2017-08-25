package com.whale.beewin.activity.invest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.activity.found.WebActivity;
import com.whale.beewin.activity.home.ScatterWealActivity;
import com.whale.beewin.adapter.InformAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Game;
import com.whale.beewin.bean.Inform;
import com.whale.beewin.view.ListViewForScrollView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 散投模拟器
 * Created by Administrator on 2017/3/29 0029.
 */

public class SimulatorActivity extends BaseActivity {
    @Bind(R.id.tv_scatter_five)
    TextView mTvScatterFive;
    @Bind(R.id.tv_scatter_ten)
    TextView mTvScatterTen;
    @Bind(R.id.tv_scatter_tewnty)
    TextView mTvScatterTewnty;
    @Bind(R.id.tv_three_month)
    TextView mTvThreeMonth;
    @Bind(R.id.tv_six_month)
    TextView mTvSixMonth;
    @Bind(R.id.tv_twelve_month)
    TextView mTvTwelveMonth;
    @Bind(R.id.tv_invest_sum)
    TextView mTvInvestSum;
    @Bind(R.id.tv_person_sum)
    TextView mTvPersonSum;
    @Bind(R.id.iv_person_sum)
    ImageView mIvPersonSum;
    @Bind(R.id.tv_person_money)
    TextView mTvPersonMoney;
    @Bind(R.id.list)
    ListViewForScrollView mList;
    @Bind(R.id.tv_person_sum1)
    TextView mTvPersonSum1;
    @Bind(R.id.tv_invest_corpus)
    TextView mTvInvestCorpus;
    @Bind(R.id.tv_invest_interest)
    TextView mTvInvestInterest;
    @Bind(R.id.tv_person_sum2)
    TextView mTvPersonSum2;
    @Bind(R.id.tv_all_money)
    TextView mTvAllMoney;
    @Bind(R.id.tv_year_precent)
    TextView mTvYearPrecent;
    @Bind(R.id.tv_corpus_all)
    TextView mTvCorpusAll;
    @Bind(R.id.tv_interest_all)
    TextView mTvInterestAll;
    @Bind(R.id.tv_extra_all)
    TextView mTvExtraAll;
    private int dateSum = 3;//分数
    private int moneySum = 10000;//总金额
    private int fenCount = 5;// 份数
    private float date = 3.0f;// 周期
    private float benjinxiaoji;//本金小计
    private float lixixiaoji;//联系小计
    List<Inform> list = new ArrayList<>();//收益列表数据集合
    DecimalFormat df = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
    InformAdapter mAdapter;//收益列表适配器
    private double precent = 0.07;//收益百分比

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);
        getInformList();
    }

    private void getInformList() {
        list.clear();
        for (int i = 1; i <= dateSum; i++) {
            Inform inform = new Inform();
            inform.setMonth("第" + i + "个月");
            inform.setCorpus(df.format(moneySum / fenCount / date));
            inform.setInterest(df.format(moneySum * precent / 12 / fenCount));
            benjinxiaoji = moneySum / date;
            inform.setCorpusSum(df.format(benjinxiaoji));
            lixixiaoji = (float) (moneySum * precent / 12);
            inform.setInterestSum(df.format(lixixiaoji));
            inform.setExtra(df.format((benjinxiaoji + lixixiaoji) * 0.1 / 12 * (date - i)));
            list.add(inform);
        }
        mAdapter = new InformAdapter(activity, list);
        mList.setAdapter(mAdapter);
    }

    private void setPerson() {
        mTvPersonSum.setText("X" + fenCount);
        mTvPersonSum1.setText("X" + fenCount);
        mTvPersonSum2.setText("X" + fenCount);
    }

    private void setTotal() {
        double extraAll = 0;
        mTvPersonMoney.setText((moneySum / fenCount) + "元");
        mTvInvestCorpus.setText((moneySum / fenCount) + "元");
        mTvInvestSum.setText(moneySum + "");
        mTvInvestInterest.setText(df.format(moneySum * precent / 12 / fenCount * dateSum) + "元");
        mTvCorpusAll.setText(moneySum + "元");
        mTvInterestAll.setText(df.format(moneySum * precent / 12 * dateSum) + "元");
        for (int i = 1; i <= dateSum; i++) {
            extraAll += (moneySum / date + moneySum * precent / 12) * 0.1 / 12 * (date - i);
        }
        mTvExtraAll.setText(df.format(extraAll) + "元");
        mTvAllMoney.setText(df.format((moneySum + moneySum * precent / 12 * dateSum + extraAll)) + "元");
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.tv_scatter_five)
    void fiveScatter() {
        mTvScatterFive.setBackgroundResource(R.drawable.rect_blue_border);
        mTvScatterFive.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mTvScatterTen.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterTen.setTextColor(getResources().getColor(R.color.b_one));
        mTvScatterTewnty.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterTewnty.setTextColor(getResources().getColor(R.color.b_one));
        mIvPersonSum.setBackgroundResource(R.drawable.wu);
        fenCount = 5;
        setPerson();
        getInformList();
        setTotal();
    }

    @OnClick(R.id.tv_scatter_ten)
    void tenScatter() {
        mTvScatterFive.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterFive.setTextColor(getResources().getColor(R.color.b_one));
        mTvScatterTen.setBackgroundResource(R.drawable.rect_blue_border);
        mTvScatterTen.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mTvScatterTewnty.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterTewnty.setTextColor(getResources().getColor(R.color.b_one));
        mIvPersonSum.setBackgroundResource(R.drawable.shi);
        fenCount = 10;
        setPerson();
        getInformList();
        setTotal();
    }

    @OnClick(R.id.tv_scatter_tewnty)
    void tewntyScatter() {
        mTvScatterFive.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterFive.setTextColor(getResources().getColor(R.color.b_one));
        mTvScatterTen.setBackgroundResource(R.drawable.rect_gray_border);
        mTvScatterTen.setTextColor(getResources().getColor(R.color.b_one));
        mTvScatterTewnty.setBackgroundResource(R.drawable.rect_blue_border);
        mTvScatterTewnty.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mIvPersonSum.setBackgroundResource(R.drawable.ershi);
        fenCount = 20;
        setPerson();
        getInformList();
        setTotal();
    }

    @OnClick(R.id.tv_three_month)
    void threeMonth() {
        mTvThreeMonth.setBackgroundResource(R.drawable.rect_green_border);
        mTvThreeMonth.setTextColor(getResources().getColor(R.color.three_zero));
        mTvSixMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvSixMonth.setTextColor(getResources().getColor(R.color.b_one));
        mTvTwelveMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvTwelveMonth.setTextColor(getResources().getColor(R.color.b_one));
        dateSum = 3;
        date = 3.0f;
        precent = 0.07;
        getInformList();
        mTvYearPrecent.setText("10.39%");
        setTotal();
    }

    @OnClick(R.id.tv_six_month)
    void sixMonth() {
        mTvThreeMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvThreeMonth.setTextColor(getResources().getColor(R.color.b_one));
        mTvSixMonth.setBackgroundResource(R.drawable.rect_green_border);
        mTvSixMonth.setTextColor(getResources().getColor(R.color.three_zero));
        mTvTwelveMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvTwelveMonth.setTextColor(getResources().getColor(R.color.b_one));
        dateSum = 6;
        date = 6.0f;
        precent = 0.08;
        getInformList();
        mTvYearPrecent.setText("12.33%");
        setTotal();
    }

    @OnClick(R.id.tv_twelve_month)
    void twelveMonth() {
        mTvThreeMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvThreeMonth.setTextColor(getResources().getColor(R.color.b_one));
        mTvSixMonth.setBackgroundResource(R.drawable.rect_gray_border);
        mTvSixMonth.setTextColor(getResources().getColor(R.color.b_one));
        mTvTwelveMonth.setBackgroundResource(R.drawable.rect_green_border);
        mTvTwelveMonth.setTextColor(getResources().getColor(R.color.three_zero));
        dateSum = 12;
        date = 12.0f;
        precent = 0.10;
        getInformList();
        mTvYearPrecent.setText("15.04%");
        setTotal();
    }

    @OnClick(R.id.btn_sum_add)
    void add() {
        moneySum += 1000;
        getInformList();
        setTotal();
    }

    @OnClick(R.id.btn_sum_reduce)
    void reduce() {
        if (moneySum >= 3000) {
            moneySum -= 1000;
        } else {
            return;
        }
        getInformList();
        setTotal();
    }

    @OnClick(R.id.btn_scatter_introduce)
    void scatterIntroduce() {
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/content/aboutdeal.htm");
        mIntent.putExtra("title", "散投介绍");
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_first_scatter)
    void firstScatter() {
        startActivity(new Intent(activity, ScatterWealActivity.class));
    }

    @OnClick(R.id.btn_scatter_safe)
    void scatterSafe() {
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/content/safedeal.htm");
        mIntent.putExtra("title", "安全保障");
        startActivity(mIntent);
//        startActivity(new Intent(activity, EnsureActivity.class));
    }

    @OnClick(R.id.btn_red_packet)
    void redPocket() {
        Game.HlistBean game = new Game.HlistBean();
        game.setHimg("http://m1.judayouyuan.com/static/img/201604/h5coin/hdhbxxl.png");
        game.setHurl("http://m1.judayouyuan.com/game/shouhb/index.htm?mid=0");
        game.setHtitle("红包削削乐");
        Intent mIntent = new Intent(activity, WebActivity.class);
        mIntent.putExtra("game", game);
        mIntent.putExtra("flag", 0);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_scatter_now)
    void scatterNow() {
        startActivity(new Intent(activity, ScatterRealActivity.class));
    }

}
