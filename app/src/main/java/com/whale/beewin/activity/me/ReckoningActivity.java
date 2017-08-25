package com.whale.beewin.activity.me;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.ReckoningAdapter;
import com.whale.beewin.adapter.SpeciesAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Reckoning;
import com.whale.beewin.utils.DateUtils;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ReckoningActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.tv_reckoning_type)
    TextView mTvReckoningType;
    @Bind(R.id.iv_reckoning_type)
    ImageView mIvReckoningType;
    @Bind(R.id.tv_reckoning_time)
    TextView mTvReckoningTime;
    @Bind(R.id.iv_reckoning_time)
    ImageView mIvReckoningTime;
    @Bind(R.id.ll_me_none)
    LinearLayout mLlMeNone;
    @Bind(R.id.list)
    XListView mList;
    @Bind(R.id.xian)
    View mXian;
    List<String> monthList2;//月份集合
    List<String> SpeciesList;//类型集合
    //    获得全部种类
    String[] mSpecies;//从arrays中得到数组
    //    获得时间
    String[] mMonths;////从arrays中得到月份数组

    ListView mLvSpecies;
    View popupView;
    PopupWindow mPopupWindow;
    SpeciesAdapter mAdapter;
    SpeciesAdapter mAdapter2;
    ListView mLvYear;
    ListView mLvMonth;
    TextView mTvMonth;
    //    得到当前的年，月
    private int mCurrentYear;//当前年份
    private int mCurrentMonth;//当前月份
    boolean isFirst = true;//是否是第一次
    private int yearPos = 0;//选中的年份下标
    private int monthPos = 0;//选中的月份下标
    List<String> yearList;//年份集合
    List<String> monthList;//月份集合
    private String pwd;//用户密码
    private String login;//用户名
    List<Reckoning.SlistBean> list = new ArrayList<>();
    ReckoningAdapter mReckoningAdapter;
    private int page = 1;//页数
    Handler mHandler;
    private int xListStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reckoning);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        String t;
        String y;
        String n = "0";
        String type = mTvReckoningType.getText().toString();
        String time = mTvReckoningTime.getText().toString();
        if (type.equals("全部类型")) {
            t = "0";
        } else if (type.equals("充值提现")) {
            t = "1";
        } else if (type.equals("投资理财")) {
            t = "2";
        } else {
            t = "3";
        }

        if (time.equals("全部时间")) {
            y = "0";
            n = "0";
        } else {
            y = time.split("年")[0];
            if (time.split("年")[1].split("月")[0].equals("10") || time.split("年")[1].split("月")[0].equals("11") || time.split("年")[1].split("月")[0].equals("12")) {
            } else {
                n = "0" + time.split("年")[1].split("月")[0];
            }
        }
        if (xListStatus == 0) {
            showLoadingDialog();
        }
        Subscription sub = wrapper.getUserReckoning(login, pwd, page + "", t, y, n, "1")
                .subscribe(newSubscriber(new Action1<Reckoning>() {
                    @Override
                    public void call(Reckoning reckoning) {
                        if (reckoning.getSlist() == null || reckoning.getSlist().size() == 0) {
                            mLlMeNone.setVisibility(View.VISIBLE);
                            mList.setVisibility(View.GONE);
                        } else {
                            mLlMeNone.setVisibility(View.GONE);
                            mList.setVisibility(View.VISIBLE);
                            if (xListStatus == 0) {
                                list.clear();
                            }
                            list.addAll(reckoning.getSlist());
                            mReckoningAdapter = new ReckoningAdapter(activity, list);
                            mList.setAdapter(mReckoningAdapter);
                        }
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    private void initView() {
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);
        mHandler = new Handler();
        SpeciesList = new ArrayList<>();
        monthList2 = new ArrayList<>();
        mSpecies = getResources().getStringArray(R.array.Species);
        mMonths = getResources().getStringArray(R.array.Month);

        for (int i = 0; i < mSpecies.length; i++) {
            SpeciesList.add(mSpecies[i]);
        }
        for (int i = 0; i < mMonths.length; i++) {
            monthList2.add(mMonths[i]);
        }
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.rl_reckoning_type)
    void reckoningType() {
        mIvReckoningType.setImageResource(R.drawable.xljt_cai);
        mTvReckoningTime.setText("全部时间");
        showTypePopu();
    }


    @OnClick(R.id.rl_reckonning_time)
    void reckoningTime() {
        mIvReckoningTime.setImageResource(R.drawable.xljt_cai);
        mTvReckoningType.setText("全部类型");
        showTimePopu();
    }

    private void showTypePopu() {
        popupView = getLayoutInflater().inflate(R.layout.popu_species, null, false);
        mLvSpecies = (ListView) popupView.findViewById(R.id.lv_species);
        mAdapter = new SpeciesAdapter(activity, SpeciesList, 0);
        mLvSpecies.setAdapter(mAdapter);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.showAsDropDown(mXian);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mTvReckoningType.setTextColor(getResources().getColor(R.color.three_color));
                mIvReckoningType.setImageResource(R.drawable.xljt_hui);
                xListStatus = 0;
                httpData();
            }
        });
        mLvSpecies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTvReckoningType.setText(mSpecies[position]);
                mIvReckoningType.setImageResource(R.drawable.xljt_hui);
                mPopupWindow.dismiss();
            }
        });
    }

    private void showTimePopu() {
        popupView = getLayoutInflater().inflate(R.layout.popu_date, null, false);
        mLvYear = (ListView) popupView.findViewById(R.id.lv_year);
        mLvMonth = (ListView) popupView.findViewById(R.id.lv_month);
        mTvMonth = (TextView) popupView.findViewById(R.id.tv_month);
        mCurrentYear = Integer.parseInt(DateUtils.getCurrentYear());
        mCurrentMonth = Integer.parseInt(DateUtils.getCurrentMonth());
        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        for (int i = mCurrentYear; i >= 2014; i--) {
            yearList.add(String.valueOf(i));
        }
        for (int j = mCurrentMonth; j >= 1; j--) {
            monthList.add(String.valueOf(j));
        }
        mAdapter = new SpeciesAdapter(activity, yearList, 1);
        mLvYear.setAdapter(mAdapter);
        mAdapter2 = new SpeciesAdapter(activity, monthList, 2);
        mLvMonth.setAdapter(mAdapter2);

        if (isFirst) {
            mAdapter.changeSelected(0);
            mAdapter2.changeMonthSelected(0);
            mTvMonth.setText(mCurrentYear + "全年");
            isFirst = false;
        } else {
            mAdapter.changeSelected(yearPos);
            int year = Integer.parseInt(yearList.get(yearPos));
            if (year < mCurrentYear) {
                monthList.clear();
                monthList.addAll(monthList2);
                mAdapter2.notifyDataSetChanged();
            } else {
                monthList.clear();
                for (int j = mCurrentMonth; j >= 1; j--) {
                    monthList.add(String.valueOf(j));

                }
                mAdapter2.notifyDataSetChanged();
            }
            mAdapter2.changeMonthSelected(monthPos);
            mTvMonth.setText(yearList.get(yearPos) + "全年");
        }

        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.showAsDropDown(mXian);

        mLvYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearPos = position;
                mAdapter.changeSelected(position);
                mAdapter2.changeMonthSelected(monthPos);
                mTvMonth.setText(yearList.get(position) + "全年");
                int year = Integer.parseInt(yearList.get(position));
                if (year < mCurrentYear) {
                    monthList.clear();
                    monthList.addAll(monthList2);
                    mAdapter2.notifyDataSetChanged();
                } else {
                    monthList.clear();
                    for (int j = mCurrentMonth; j >= 1; j--) {
                        monthList.add(String.valueOf(j));
                    }
                    mAdapter2.notifyDataSetChanged();
                }
            }
        });
        mLvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthPos = position;
                mAdapter2.changeMonthSelected(position);
                mAdapter.changeSelected(yearPos);
                mTvMonth.setText(yearList.get(yearPos) + "全年");
                int year = Integer.parseInt(yearList.get(yearPos));
                int month = Integer.parseInt(monthList.get(position));
                mTvReckoningTime.setText(year + "年" + month + "月");
                mIvReckoningTime.setImageResource(R.drawable.xljt_hui);
                mPopupWindow.dismiss();
                xListStatus = 0;
                httpData();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mIvReckoningTime.setImageResource(R.drawable.xljt_hui);
            }
        });
    }

    @Override
    public void onRefresh() {
        xListStatus = 0;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                httpData();
                onLoad();
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        xListStatus = 2;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                httpData();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }

}
