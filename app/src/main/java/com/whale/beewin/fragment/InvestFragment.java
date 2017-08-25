package com.whale.beewin.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.adapter.InvestAdapter;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.Invest;
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
 * Created by Administrator on 2017/3/24 0024.
 */

public class InvestFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.list)
    XListView mList;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    final ApiWrapper wrapper = new ApiWrapper();//网络请求类
    List<Invest.MlistBean> list = new ArrayList<>();//数据集合
    Handler mHandler;
    private int xListStatus = 0;
    InvestAdapter mAdapter;
    private int mIslogin;
    private boolean isRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView() {
        View headerView = this.getActivity().getLayoutInflater().inflate(R.layout.listview_headview, null);
        // 添加头部view：必须放在adapter前面不然会报错
        mList.addHeaderView(headerView);
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);
        mHandler = new Handler();
        mList.setOnItemClickListener(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        Subscription subscription = wrapper.getSysInvest()
                .subscribe(newSubscriber(new Action1<Invest>() {
                    @Override
                    public void call(Invest invest) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        if (list != null) {
                            list.clear();
                        }
                        list.addAll(invest.getMlist());
                        mAdapter = new InvestAdapter(getActivity(), list);
                        mList.setAdapter(mAdapter);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
//        isRefresh = (boolean) SPUtils.get(BeewinApp.getContext(), "isRefresh", false);
//        if (isRefresh) {
//            mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
//            loadData();
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        xListStatus = 1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
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
                showToast("没有更多数据");
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        if (mIslogin == 1) {
//            if (position == 1) {
//                startActivity(new Intent(getActivity(), SimulatorActivity.class));
//            } else {
//                Intent intent = new Intent(getActivity(), InvestDetailActivity.class);
//                intent.putExtra("id", list.get(position - 2).getId());
//                startActivity(intent);
//            }
//        } else {
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//        }
    }
}
