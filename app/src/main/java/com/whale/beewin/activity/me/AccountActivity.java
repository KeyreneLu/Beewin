package com.whale.beewin.activity.me;

import android.os.Bundle;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Realm;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 账户信息
 * Created by Administrator on 2017/4/7 0007.
 */

public class AccountActivity extends BaseActivity {
    @Bind(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_bank_card)
    TextView mTvBankCard;
    private String phone;//认证手机号
    private String pwd;//用户密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        phone = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        initView();
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        showLoadingDialog();
        Subscription sub = wrapper.getRealm(phone, pwd)
                .subscribe(newSubscriber(new Action1<Realm>() {
                    @Override
                    public void call(Realm realm) {
                        mTvUserName.setText(realm.getUname());
                        mTvBankCard.setText(realm.getUcardno());
                        SPUtils.put(BeewinApp.getContext(), "uname", realm.getUname());
                        SPUtils.put(BeewinApp.getContext(), "ucardno", realm.getUcardno());
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    private void initView() {
        mTvUserPhone.setText(phone);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }
}
