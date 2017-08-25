package com.whale.beewin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.User;
import com.whale.beewin.utils.HeaderObject;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 用户登录界面
 * Created by Administrator on 2017/3/24 0024.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.et_user_name)
    EditText mEtUserName;
    @Bind(R.id.et_user_password)
    EditText mEtUserPassword;
    @Bind(R.id.iv_saw)
    ImageView mIvSaw;

    private boolean IsShow = false;//密码是否可见
    private String gesture; //手势密码
    private String login;//用户账号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gesture = (String) SPUtils.get(BeewinApp.getContext(),"isShowSetGest", "");
    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_login)
    void Login() {
        showLoadingDialog();
        Subscription subscription = wrapper.userLogin(mEtUserName.getText().toString().trim(), mEtUserPassword.getText().toString().trim())
                .subscribe(newSubscriber(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        HeaderObject.setUser(user);
                        SPUtils.put(BeewinApp.getContext(), "isRefresh", true);
                        if (SPUtils.contains(activity,user.getLogin())) {
                            showToast( "登陆成功");
                            finish();
                        } else {
                            if (gesture.equals(user.getLogin())) {
                                finish();
                            } else {
                                startActivity(new Intent(activity, GestureTipActivity.class));
                            }
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.tv_register)
    void Register() {
        startActivity(new Intent(activity, RegisterActivity.class));
    }

    @OnClick(R.id.tv_forget_password)
    void Change() {
        startActivity(new Intent(activity, ModifyActivity.class));
    }

    @OnClick(R.id.iv_saw)
    void showPassword() {
        if (IsShow){
            mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
            mIvSaw.setImageResource(R.mipmap.dl_yc1);
            IsShow = false;
        }else {
            mEtUserPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            mIvSaw.setImageResource(R.mipmap.dl_yc2);
             IsShow = true;
        }
    }

    @OnClick(R.id.iv_clear)
    void clearPassword(){
        mEtUserPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        login = (String) SPUtils.get(BeewinApp.getContext(),"login","");
        if (!login.isEmpty()){
            mEtUserName.setText(login);
        }
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

}
