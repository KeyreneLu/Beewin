package com.whale.beewin.activity.me;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.ModifyActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.HomeRequest;
import com.whale.beewin.bean.Realm;
import com.whale.beewin.bean.Routine;
import com.whale.beewin.utils.SPUtils;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class DepositActivity extends BaseActivity {
    @Bind(R.id.tv_deposit_sum)
    TextView mTvDepositSum;
    @Bind(R.id.et_deposit_number)
    EditText mEtDepositNumber;
    @Bind(R.id.tv_deposit_charge)
    TextView mTvDepositCharge;
    @Bind(R.id.tv_deposit_phone)
    TextView mTvDepositPhone;
    @Bind(R.id.tv_deposit_name)
    TextView mTvDepositName;
    @Bind(R.id.tv_deposit_card)
    TextView mTvDepositCard;
    @Bind(R.id.tv_deposit_date)
    TextView mTvDepositDate;
    @Bind(R.id.tv_deposit_time)
    TextView mTvDepositTime;
    private String login;//认证手机号
    private String pwd;//用户密码
    private String sstel;
    private int txid;
    private float ugmoney;
    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        sstel = (String) SPUtils.get(BeewinApp.getContext(), "sstel", "");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        showLoadingDialog();
        Subscription Sub = Observable.zip(wrapper.getRoutine(login, pwd), wrapper.getRealm(login, pwd), new Func2<Routine, Realm, HomeRequest>() {
            @Override
            public HomeRequest call(Routine routine, Realm realm) {
                HomeRequest request = new HomeRequest();
                request.setRoutine(routine);
                request.setRealm(realm);
                return request;
            }
        }).subscribe(newSubscriber(new Action1<HomeRequest>() {
            @Override
            public void call(HomeRequest homeRequest) {
                Routine routine = homeRequest.getRoutine();
                if (routine.getIss() == 1) {
                    mTvDepositCharge.setText("3元");
                } else if (routine.getIss() == -1) {
                    mTvDepositCharge.setText("免手续费");
                    mTvDepositTime.setText("n次");
                } else {
                    mTvDepositCharge.setText("免手续费");
                    mTvDepositTime.setText("1次");
                }
                txid = routine.getTxid();

                Realm realm = homeRequest.getRealm();
                mTvDepositPhone.setText(sstel);
                mTvDepositName.setText(realm.getUname());
                mTvDepositCard.setText(realm.getUcardno());
            }
        }));
        mCompositeSubscription.add(Sub);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ugmoney = (float) SPUtils.get(BeewinApp.getContext(), "ugmoney", 0.0f);
        mTvDepositSum.setText(ugmoney + "");
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.btn_deposit_sure)
    void recharge() {
        if (mEtDepositNumber.getText().toString().isEmpty()) {
            showToast("提现金额不能为空!");
            return;
        }
        double mmon = Double.parseDouble(mEtDepositNumber.getText().toString().trim());
        if (mmon > ugmoney) {
            showToast("余额不足!");
            return;
        }
        showPayDialog(mmon);
    }

    private void showPayDialog(final double mmon) {
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_coin_pay, null);
        dialog.setView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_coin_pay);
        dialog.setCanceledOnTouchOutside(true);// 点击对话框外面的区域退出对话框
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER | Gravity.CENTER);// 对话框在正中间
        final EditText edittext = (EditText) window.findViewById(R.id.et_mima);
        TextView button_wjmm = (TextView) window.findViewById(R.id.button_qianbao_tixianfukuan_wjmm);
        TextView button_qx = (TextView) window.findViewById(R.id.button_tixianfukuan_qx);
        TextView button_true = (TextView) window.findViewById(R.id.button_qianbao_tixianquerenfukuan_cai);
        ImageView mIvDelete = (ImageView) window.findViewById(R.id.iv_delete);

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext.setText("");
            }
        });
        // 忘记密码
        button_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, ModifyActivity.class));
            }
        });
        // 取消
        button_qx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        button_true.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showLoadingDialog();
                Subscription sub = wrapper.userDeposit(login, pwd, mmon + "", edittext.getText().toString(), txid + "")
                        .subscribe(newSubscriber(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                SPUtils.put(BeewinApp.getContext(), "ugmoney", Float.parseFloat(df.format((ugmoney - mmon))));
                                SPUtils.put(BeewinApp.getContext(), "imoney", Float.parseFloat(df.format((ugmoney - mmon))));
                                SPUtils.put(BeewinApp.getContext(), "isChangeUser", true);
                                startActivity(new Intent(activity, DepositResultActivity.class));
                                finish();
                            }
                        }));
                mCompositeSubscription.add(sub);
            }
        });
    }
}
