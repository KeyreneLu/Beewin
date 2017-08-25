package com.whale.beewin.fragment;

import android.content.Intent;
import android.widget.RelativeLayout;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.activity.loan.HonorActivity;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.Honor;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class LoanFragment extends BaseFragment {
    @Bind(R.id.rl_realm_name)
    RelativeLayout mRlRealmName;
    @Bind(R.id.rl_your_honor)
    RelativeLayout mRlYourHonor;
    @Bind(R.id.rl_small_loan)
    RelativeLayout mRlSmallLoan;
    private int mIslogin;
    private int xy;
    private String login;//认证手机号
    private String pwd;//用户密码
    ApiWrapper wrapper = new ApiWrapper();
    private boolean isRefresh;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void initView() {
//        mIslogin = (int) SPUtils.get(BeewinApp.getContext(),"islogin", -1);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(),"islogin", -1);
//        isRefresh = (boolean) SPUtils.get(BeewinApp.getContext(), "isRefresh", false);
//        if (isRefresh) {
//            initView();
//            loadData();
//        }
    }

    @OnClick(R.id.rl_realm_name)
    void realmName(){
        showToast("暂未开放");
    }

    void yourHonor(){
        if (mIslogin == 1){
            startActivity(new Intent(getActivity(), HonorActivity.class));
        }else {
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @OnClick(R.id.rl_small_loan)
    void smallLoan(){
        showToast("暂未开放");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
            Subscription sub = wrapper.getHonor(login, pwd)
                    .subscribe(newSubscriber(new Action1<Honor>() {
                        @Override
                        public void call(Honor honor) {
                            if (honor.getXy().contains(".")){
                                xy = Integer.parseInt(honor.getXy().split("[.]")[0]);
                            }else {
                                xy = Integer.parseInt(honor.getXy());
                            }
                            SPUtils.put(BeewinApp.getContext(),"number",honor.getXy());
                            SPUtils.put(BeewinApp.getContext(),"xy",xy);
                            SPUtils.put(BeewinApp.getContext(),"xyd",honor.getXyd());
                            SPUtils.put(BeewinApp.getContext(),"umoney",(float)Double.parseDouble(honor.getUmoney()));
                        }
                    }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
