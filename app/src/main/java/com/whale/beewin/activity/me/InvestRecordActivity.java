package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.whale.beewin.R;
import com.whale.beewin.activity.MainActivity;
import com.whale.beewin.adapter.GoodsAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.InvestRecord;
import com.whale.beewin.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 投资记录
 * Created by Administrator on 2017/2/22 0022.
 */

public class InvestRecordActivity extends BaseActivity {
    @Bind(R.id.rb_take_all)
    RadioButton mRbTakeAll;
    @Bind(R.id.rb_sale_proccess)
    RadioButton mRbSaleProccess;
    @Bind(R.id.rb_been_back)
    RadioButton mRbBeenBack;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.id_tab_line)
    ImageView mIdTabLine;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_me_none)
    LinearLayout mLlMeNone;
    @Bind(R.id.list)
    ListView mList;
    private int flag = 0;
    private List<InvestRecord.GoodsBean> data_goods = new ArrayList<>();
    private GoodsAdapter goodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investrecord);
        ButterKnife.bind(this);
        initView();
        setlistener();
        httpRecord(flag);
    }

    private void initView() {
        mIdTabLine.getLayoutParams().width = Utils.getScreenWidth(InvestRecordActivity.this) / 3;
    }

    private void setlistener() {
        mRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIdTabLine.getLayoutParams();
                switch (checkedId) {
                    case R.id.rb_take_all:
                        flag = 0;
                        mList.setVisibility(View.GONE);
                        mLlMeNone.setVisibility(View.GONE);
                        mLlLoading.setVisibility(View.VISIBLE);
                        lp.leftMargin = (int) (mRgMenu.getWidth() / 3) * 0;
                        mIdTabLine.setLayoutParams(lp);
                        httpRecord(flag);
                        break;
                    case R.id.rb_sale_proccess:
                        //获取组件距离左侧组件的距离
                        flag = 1;
                        mList.setVisibility(View.GONE);
                        mLlMeNone.setVisibility(View.GONE);
                        mLlLoading.setVisibility(View.VISIBLE);
                        lp.leftMargin = (int) (mRgMenu.getWidth() / 3);
                        mIdTabLine.setLayoutParams(lp);
                        httpRecord(flag);
                        break;
                    case R.id.rb_been_back:
                        //获取组件距离左侧组件的距离
                        flag = 2;
                        mList.setVisibility(View.GONE);
                        mLlMeNone.setVisibility(View.GONE);
                        mLlLoading.setVisibility(View.VISIBLE);
                        lp.leftMargin = (int) (mRgMenu.getWidth() / 3) * 2;
                        mIdTabLine.setLayoutParams(lp);
                        httpRecord(flag);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.button_me_touzi)
    void investNow() {
        MainActivity.mViewpager.setCurrentItem(1, false);
        MainActivity.mRadioInvest.setChecked(true);
        finish();
    }

    @SuppressWarnings("unchecked")
    private void httpRecord(final int flag) {
        data_goods.clear();
        Subscription subscription = wrapper.getInvestRecord()
                .subscribe(newSubscriber(new Action1<InvestRecord>() {
                    @Override
                    public void call(InvestRecord investRecord) {
                        mList.setVisibility(View.VISIBLE);
                        mLlLoading.setVisibility(View.GONE);
                        if (flag == 0) {
                            data_goods.addAll(investRecord.getGoods());
                        } else if (flag == 1) {
                            for (int i = 0; i < investRecord.getGoods().size(); i++) {
                                if (!investRecord.getGoods().get(i).getOstatus().equals("1")) {
                                    data_goods.add(investRecord.getGoods().get(i));
                                }
                            }
                        } else if (flag == 2) {
                            for (int i = 0; i < investRecord.getGoods().size(); i++) {
                                if (investRecord.getGoods().get(i).getOstatus().equals("1")) {
                                    data_goods.add(investRecord.getGoods().get(i));
                                }
                            }
                        }
                        if (investRecord.getGoods().size() == 0||investRecord.getGoods() == null) {
                            mLlLoading.setVisibility(View.GONE);
                            mLlMeNone.setVisibility(View.VISIBLE);
                        } else {
                            goodsAdapter = new GoodsAdapter(InvestRecordActivity.this, data_goods);// 初始化适配器
                            mList.setAdapter(goodsAdapter);// 设置适配器
                            goodsAdapter.notifyDataSetChanged();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
