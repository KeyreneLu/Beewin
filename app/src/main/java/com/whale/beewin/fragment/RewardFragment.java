package com.whale.beewin.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.RewardAdapter;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.Income;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class RewardFragment extends BaseFragment implements XListView.IXListViewListener {

    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.xlist)
    XListView mXlist;
    @Bind(R.id.ll_me_none)
    LinearLayout mLlMeNone;

    private String mLogin;//用户名
    private String password;//用户密码
    final ApiWrapper wrapper = new ApiWrapper();//网络请求类
    private int page = 1;
    private RewardAdapter mAdapter;
    Handler mHandler;
    private int xListStatus = 0;
    private List<Income.MlistBean> datas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reward;
    }

    @Override
    protected void initView() {
        mXlist.setPullLoadEnable(true);
        mXlist.setXListViewListener(this);
        mHandler = new Handler();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        mLogin = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        password = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        Subscription subscription = wrapper.getIncome(mLogin, password, page + "")
                .subscribe(newSubscriber(new Action1<Income>() {
                    @Override
                    public void call(Income income) {
                        mLlLoading.setVisibility(View.GONE);
                        if (income.getMlist() == null) {
                            if (xListStatus != 0) {
                                showToast("没有更多数据!");
                            }else {
                                mLlMeNone.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mXlist.setVisibility(View.VISIBLE);
                            if (xListStatus == 0) {
                                datas.clear();
                                datas.addAll(income.getMlist());
                            } else {
                                datas.addAll(income.getMlist());
                            }
                            mAdapter = new RewardAdapter(getActivity(), datas);
                            mXlist.setAdapter(mAdapter);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        xListStatus = 0;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                loadData();
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
                loadData();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mXlist.stopRefresh();
        mXlist.stopLoadMore();
    }
}
