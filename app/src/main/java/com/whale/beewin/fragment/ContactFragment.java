package com.whale.beewin.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.found.RewardRuleActivity;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class ContactFragment extends BaseFragment {
    @Bind(R.id.tv_register_number)
    TextView mTvRegisterNumber;
    @Bind(R.id.tv_real_number)
    TextView mTvRealNumber;
    @Bind(R.id.tv_invested_number)
    TextView mTvInvestedNumber;
    @Bind(R.id.tv_register_number2)
    TextView mTvRegisterNumber2;
    @Bind(R.id.tv_real_number2)
    TextView mTvRealNumber2;
    @Bind(R.id.tv_invested_number2)
    TextView mTvInvestedNumber2;
    @Bind(R.id.tv_once_rebate3)
    TextView mTvOnceRebate3;
    @Bind(R.id.tv_register_number3)
    TextView mTvRegisterNumber3;
    @Bind(R.id.tv_real_number3)
    TextView mTvRealNumber3;
    @Bind(R.id.tv_invested_number3)
    TextView mTvInvestedNumber3;
    @Bind(R.id.tv_once_rebate4)
    TextView mTvOnceRebate4;
    @Bind(R.id.tv_register_number4)
    TextView mTvRegisterNumber4;
    @Bind(R.id.tv_real_number4)
    TextView mTvRealNumber4;
    @Bind(R.id.tv_invested_number4)
    TextView mTvInvestedNumber4;
    @Bind(R.id.tv_rule)
    TextView mTvRule;
    @Bind(R.id.tv_rule2)
    TextView mTvRule2;
    @Bind(R.id.iv_reward2)
    ImageView mIvReward2;
    @Bind(R.id.rl_reward2)
    RelativeLayout mRlReward2;
    @Bind(R.id.iv_reward1)
    ImageView mIvReward1;
    @Bind(R.id.rl_reward1)
    RelativeLayout mRlReward1;
    private String mLogin;//用户名
    private String password;//用户密码
    final ApiWrapper wrapper = new ApiWrapper();//网络请求类
    private LayoutInflater mInflater;
    View mRootView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView() {
        SpannableString spanText = new SpannableString(mTvRule.getText().toString());
        spanText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                getActivity().startActivity(new Intent(getActivity(), RewardRuleActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
            }
        },spanText.length()-7,spanText.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

        mTvRule.setText(spanText);
        mTvRule.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件

        SpannableString spanText2 = new SpannableString(mTvRule2.getText().toString());
        spanText2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ShowRuleDialog();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.touzi_fenshu));       //设置文件颜色
            }
        },spanText2.length()-11,spanText2.length()-7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

        mTvRule2.setText(spanText2);
        mTvRule2.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        mLogin = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        password = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
//        showLoadingDialog();
        Subscription sub = wrapper.getInfo(mLogin, password)
                .subscribe(newSubscriber(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        mTvRegisterNumber.setText(userInfo.getN1());
                        mTvRealNumber.setText(userInfo.getB1());
                        mTvInvestedNumber.setText(userInfo.getJ1());

                        mTvRegisterNumber2.setText(userInfo.getN2());
                        mTvRealNumber2.setText(userInfo.getB2());
                        mTvInvestedNumber2.setText(userInfo.getJ2());

                        if (Integer.parseInt(userInfo.getB1()) >= 10 && Integer.parseInt(userInfo.getB1()) >= 2) {
                            mRlReward1.setBackgroundResource(R.drawable.rect_green);
                            mTvOnceRebate3.setText(R.string.rmjlz);
                            mTvOnceRebate3.setTextColor(getResources().getColor(R.color.three_green));
                            mIvReward1.setVisibility(View.GONE);
                            mTvRegisterNumber3.setText(userInfo.getN3());
                            mTvRealNumber3.setText(userInfo.getB3() );
                            mTvInvestedNumber3.setText(userInfo.getJ3());
                        } else {
                            mTvRegisterNumber3.setText("?");
                            mTvRealNumber3.setText("?");
                            mTvInvestedNumber3.setText("?");
                            mRlReward1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShowPoPu(10, 2);
                                }
                            });
                        }

                        if (Integer.parseInt(userInfo.getB1()) >= 100 && Integer.parseInt(userInfo.getB1()) >= 10) {
                            mRlReward2.setBackgroundResource(R.drawable.rect_green);
                            mTvOnceRebate4.setText(R.string.rmjlz);
                            mIvReward2.setVisibility(View.GONE);
                            mTvOnceRebate4.setTextColor(getResources().getColor(R.color.three_green));
                            mTvRegisterNumber4.setText(userInfo.getN4());
                            mTvRealNumber4.setText(userInfo.getB4());
                            mTvInvestedNumber4.setText(userInfo.getJ4());
                        } else {
                            mTvRegisterNumber4.setText("?");
                            mTvRealNumber4.setText("?");
                            mTvInvestedNumber4.setText("?");
                            mRlReward2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShowPoPu(100, 10);
                                }
                            });
                        }
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    private void ShowPoPu(int real, int invest) {
        mInflater = LayoutInflater.from(getActivity());
        mRootView = mInflater.inflate(R.layout.popu_more_layout, null);
        TextView mTvReal = (TextView) mRootView.findViewById(R.id.tv_real_number);
        TextView mTvInvest = (TextView) mRootView.findViewById(R.id.tv_invest_number);
        mTvReal.setText(real+"");
        mTvInvest.setText(invest+"");
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(getActivity(), R.style.myDialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = mDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setAttributes(p);
    }

    private void ShowRuleDialog() {
        mInflater = LayoutInflater.from(getActivity());
        mRootView = mInflater.inflate(R.layout.popu_rule_layout, null);
        final Dialog mDialog = new Dialog(getActivity(), R.style.myDialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = mDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setAttributes(p);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
