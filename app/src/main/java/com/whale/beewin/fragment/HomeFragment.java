package com.whale.beewin.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.jude.rollviewpager.hintview.IconHintView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.activity.WithCookieActivity;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.activity.found.ShareActivity;
import com.whale.beewin.activity.home.NoticeActivity;
import com.whale.beewin.activity.home.SafeActivity;
import com.whale.beewin.activity.invest.ScatterRealActivity;
import com.whale.beewin.activity.invest.SimulatorActivity;
import com.whale.beewin.adapter.BannerAdapter;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.Banner;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.T;
import com.whale.beewin.utils.UIHelper;
import com.whale.beewin.utils.Utils;
import com.whale.beewin.view.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * 隐藏头部带cookie的webview
 * Created by Administrator on 2017/3/24 0024.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ad_view)
    RollPagerView mAdView;
    @Bind(R.id.iv_error)
    ImageView mIvError;
    @Bind(R.id.iv_notice_red)
    ImageView mIvNoticeRed;
    @Bind(R.id.rl_main_invest)
    RelativeLayout mRlMainInvest;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    @Bind(R.id.lamp)
    ViewFlipper mLamp;

    final ApiWrapper wrapper = new ApiWrapper();//网络请求类

    private int mIslogin;//是否已登录
    Subscription subscription = null;
    private List<Banner.AlistBean> mList;
    private boolean isUpdate = true;//用户是否更新应用
    List<String> dataList = new ArrayList<>();// 滚动文字集合
    private boolean isFirst = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        refreshUi();
        mAdView.setPlayDelay(2000);
//        mAdView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                if (mIslogin == 1) {
//                    if (mList.get(position).getType().equals("1")) {
//                        Intent intent = new Intent();
//                        intent.setClass(getActivity(), WithCookieActivity.class);
//                        intent.putExtra("url", mList.get(position).getAurl());
//                        intent.putExtra("title", mList.get(position).getTitle());
//                        getActivity().startActivity(intent);
//                    } else {
//                        getActivity().startActivity(new Intent(getActivity(), ScatterWealActivity.class));
//                    }
//                } else {
//                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
//                }
//            }
//        });
    }

    private void refreshUi() {
        //设置公告栏
        dataList.add("认证支付部分银行维护通知");
        dataList.add("日常活动奖励结算通知");
        dataList.add("VIP系统更新公告");
        dataList.add("认证支付部分银行临时维护通知");
        dataList.add("认证支付部分银行恢复通知");
        dataList.add("“在投金额”显示调整说明");
        addDataToView();
        mLamp.setFlipInterval(5000);
        mLamp.setInAnimation(getActivity(), R.anim.slide_top_in);
        mLamp.setOutAnimation(getActivity(), R.anim.slide_bottom_out);
//        for (int i = 0; i < 6; i++) {
//            dataNull.add("暂无系统消息");
//        }
//        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
//        if (mIslogin == 1) {
//            //设置公告栏
//            mLamp.startWithList(dataList);
//            mIvNoticeRed.setVisibility(View.VISIBLE);
//        } else {
//            mLamp.startWithList(dataNull);
//            mIvNoticeRed.setVisibility(View.GONE);
//        }
    }

    private void addDataToView() {
        for (String text : dataList) {
            TextView tv = new TextView(getActivity());
            tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            tv.setTextSize(14);
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setText(text);
            mLamp.addView(tv);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        subscription = wrapper.getHomeBanner()
                .subscribe(newSubscriber(new Action1<Banner>() {
                    @Override
                    public void call(final Banner banner) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        mList = new ArrayList<>();
                        // banner数据
                        mList = banner.getAlist();
                        if (mList.size() == 0 || mList == null) {
                            mIvError.setVisibility(View.VISIBLE);
                            mAdView.setVisibility(View.GONE);
                        } else {
                            mIvError.setVisibility(View.GONE);
                            mAdView.setVisibility(View.VISIBLE);
                            if (isFirst) {
                                mAdView.setHintView(new IconHintView(getContext(), R.drawable.blue, R.drawable.dot_normal, Utils.dip2px(getContext(), 20)));
                                mAdView.setHintPadding(0, 0, 0, Utils.dip2px(getContext(), 8));
                                mAdView.setAdapter(new BannerAdapter(getContext(), banner.getAlist()));
                                isFirst = false;
                            }
                        }
                        //应用更新
                        if (Integer.parseInt(banner.getVersion()) > Utils.getVersionCode(getActivity())) {
                            if (isUpdate) {
                                Dialog mDialog = new AlertDialog.Builder(getActivity())
                                        .setIcon(R.mipmap.dl_logo)
                                        .setTitle("应用更新")
                                        .setMessage(banner.getUcontent())
                                        .setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                isUpdate = false;
                                            }
                                        })
                                        .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                new RxPermissions(getActivity()).requestEach(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                                                .subscribe(new Action1<Permission>() {
                                                    @Override
                                                    public void call(Permission permission) {
                                                        if (permission.granted) {
                                                            UIHelper.openDownLoadService(getActivity(), banner.getDownurl(), "蜂赢金服");
                                                            isUpdate = false;
                                                        } else {
                                                            Toast.makeText(getActivity(), "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                                                            isUpdate = true;
                                                        }
                                                    }
                                                });
                                            }
                                        }).create();
                                mDialog.show();
                            }
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        mLamp.startFlipping();
        if (mIslogin == 1) {
            mIvNoticeRed.setVisibility(View.VISIBLE);
        } else {
            mIvNoticeRed.setVisibility(View.GONE);
        }
    }


    void showNotice() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), NoticeActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void seeData() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getTitle().contains("运营报告")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WithCookieActivity.class);
                intent.putExtra("url", mList.get(i).getAurl());
                intent.putExtra("title", mList.get(i).getTitle());
                getActivity().startActivity(intent);
            }
        }
    }


    void safeProtocol() {
        startActivity(new Intent(getActivity(), SafeActivity.class));
    }


    void newHand() {
        if (mIslogin == 1) {
            T.showShort(getActivity(), "您已注册，请前往“发现”中赢取好礼！");
        } else {
            Intent mIntent = new Intent(getActivity(), WithNoCookieActivity.class);
            mIntent.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Member&a=register");
            mIntent.putExtra("title", "新人好礼");
            startActivity(mIntent);
        }
    }


    void newGift() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), ShareActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void toScatter() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), SimulatorActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void scatterNow() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), ScatterRealActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mLamp.stopFlipping();
    }
}
