package com.whale.beewin.activity.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Rate;
import com.whale.beewin.bean.Realm;
import com.whale.beewin.utils.DateUtils;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.MyMarkerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class WalletActivity extends BaseActivity {
    @Bind(R.id.tv_wallet_money)
    TextView mTvWalletMoney;
    @Bind(R.id.tv_wallet_percent)
    TextView mTvWalletPercent;
    @Bind(R.id.tv_real_return)
    TextView mTvRealReturn;
    @Bind(R.id.linechart)
    LineChart mLinechart;
    @Bind(R.id.tv_come_back)
    TextView mTvComeBack;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.Main_content)
    LinearLayout mMainContent;
    private float twfu;
    private float imoney;
    private int Bcard;
    private String pwd;//用户密码
    private String login;//用户名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Bcard = (int) SPUtils.get(BeewinApp.getContext(), "bcard", 0);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    private void initView() {
        mLinechart.setDescription(null);//设置图表描述信息
        mLinechart.setNoDataText("暂无数据没有数据");//没有数据时显示的文字
        mLinechart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        mLinechart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        mLinechart.setDrawBorders(true);//禁止绘制图表边框的线
        mLinechart.setBorderColor(getResources().getColor(R.color.a_five));
        mLinechart.setBorderWidth(1f);
        //获取此图表的x轴
        XAxis xAxis = mLinechart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(12f);//设置字体
        xAxis.setTextColor(getResources().getColor(R.color.a_five));//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.setAxisLineWidth(1f);
        xAxis.setGridLineWidth(1f);
        xAxis.setGridColor(getResources().getColor(R.color.a_five));
        xAxis.setAxisLineColor(getResources().getColor(R.color.a_five));
//        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
//        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        xAxis.setLabelCount(8);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                Log.e("value", value + "");
                return DateUtils.getDateInYear(value);
            }
        });
        //获取右边的轴线
        YAxis rightAxis = mLinechart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = mLinechart.getAxisLeft();
        leftAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        //设置网格线为虚线效果
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setTextSize(12f);//设置字体
        leftAxis.setGridLineWidth(1f);
        leftAxis.setGridColor(getResources().getColor(R.color.a_five));
        leftAxis.setAxisLineWidth(1f);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.a_five));
        leftAxis.setTextColor(getResources().getColor(R.color.a_five));//设置字体颜色
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(true);
        final DecimalFormat df = new DecimalFormat("#.00");
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return df.format(value / 100);
            }
        });
        Legend l = mLinechart.getLegend();//图例
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);//设置图例的位置
        l.setTextSize(14f);//设置文字大小
        l.setTextColor(getResources().getColor(R.color.lucency));
        l.setForm(Legend.LegendForm.NONE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度
        //自定义的MarkerView对象
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mLinechart);
        mLinechart.setMarker(mv);
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription subscription = wrapper.getRate()
                .subscribe(newSubscriber(new Action1<Rate>() {
                    @Override
                    public void call(Rate rate) {
                        mLlLoading.setVisibility(View.GONE);
                        mMainContent.setVisibility(View.VISIBLE);
                        mTvWalletPercent.setText(rate.getTd() + "%");
                        ArrayList<Entry> values1 = new ArrayList<>();
//                        Utils.getTime(rate.getRq1())
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq1()), Float.valueOf(rate.getSy1()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq2()), Float.valueOf(rate.getSy2()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq3()), Float.valueOf(rate.getSy3()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq4()), Float.valueOf(rate.getSy4()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq5()), Float.valueOf(rate.getSy5()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq6()), Float.valueOf(rate.getSy6()) * 100));
                        values1.add(new Entry(DateUtils.getDayInYear(rate.getRq7()), Float.valueOf(rate.getSy7()) * 100));
                        //LineDataSet每一个对象就是一条连接线
                        LineDataSet set1;
                        //判断图表中原来是否有数据
                        if (mLinechart.getData() != null && mLinechart.getData().getDataSetCount() > 0) {
                            //获取数据1
                            set1 = (LineDataSet) mLinechart.getData().getDataSetByIndex(0);
                            set1.setValues(values1);
                            //刷新数据
                            mLinechart.getData().notifyDataChanged();
                            mLinechart.notifyDataSetChanged();
                        } else {
                            //设置数据1  参数1：数据源 参数2：图例名称
                            set1 = new LineDataSet(values1, "7日年化利率(%)");
                            set1.setLabel(null);
                            set1.setColor(getResources().getColor(R.color.touzi_fenshu));
                            set1.setCircleColor(getResources().getColor(R.color.touzi_fenshu));
                            set1.setLineWidth(1f);//设置线宽
                            set1.setCircleRadius(3f);//设置焦点圆心的大小
                            set1.setHighlightEnabled(true);//是否禁用点击高亮线
                            set1.setHighLightColor(getResources().getColor(R.color.lucency));//设置点击交点后显示交高亮线的颜色
                            set1.setValueTextSize(9f);//设置显示值的文字大小
                            set1.setDrawFilled(false);//设置禁用范围背景填充
                            //格式化显示数据
                            final DecimalFormat df = new DecimalFormat("#.00");
                            set1.setValueFormatter(new IValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                                    return df.format(value / 100);
                                }
                            });
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(set1); // add the datasets
                            //创建LineData对象 属于LineChart折线图的数据集合
                            LineData data = new LineData(dataSets);
                            // 添加到图表中
                            mLinechart.setData(data);
                            //绘制图表
                            mLinechart.invalidate();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        twfu = (float) SPUtils.get(BeewinApp.getContext(), "twfu", 0.0f);
        imoney = (float) SPUtils.get(BeewinApp.getContext(), "imoney", 0.0f);
        mTvComeBack.setText(twfu + "");
        mTvWalletMoney.setText(imoney + "");
    }

    @OnClick(R.id.rl_wallet_deposit)
    void deposit() {
        httpReal(1);
    }

    @SuppressWarnings("unchecked")
    private void httpReal(final int flag) {
        Subscription sub = wrapper.getRealm(login, pwd)
                .subscribe(newSubscriber(new Action1<Realm>() {
                    @Override
                    public void call(Realm realm) {
                        if (realm.getBcard().equals("1")) {
                            if (flag == 1) {
                                startActivity(new Intent(activity, DepositActivity.class));
                            } else {
                                startActivity(new Intent(activity, WalletRechargeActivity.class));
                            }
                        } else {
                            startActivity(new Intent(activity, RealNameActivity.class));
                        }
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    void recharge() {
        httpReal(2);
    }

    @OnClick(R.id.rl_wallet_balance)
    void balance() {
        //                Intent mintent_qbye = new Intent(WalletActivity.this, Web_help.class);
//                mintent_qbye.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Member&a=smoney");
//                mintent_qbye.putExtra("title", "钱包余额");
//                startActivity(mintent_qbye);
    }

    void linpin() {
        Intent mintent = new Intent(WalletActivity.this, WithNoCookieActivity.class);
        mintent.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Index&a=gift201703");
        mintent.putExtra("title", "春季好礼");
        startActivity(mintent);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
