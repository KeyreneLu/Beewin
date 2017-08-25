package com.whale.beewin.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.http.RestClient;
import com.whale.beewin.utils.Constant;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.T;
import com.whale.beewin.utils.TimeCountUtils;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 修改密码界面
 * Created by Administrator on 2017/3/24 0024.
 */

public class ModifyActivity extends BaseActivity {
    @Bind(R.id.et_user_name)
    EditText mEtUserName;
    @Bind(R.id.et_pic_code)
    EditText mEtPicCode;
    @Bind(R.id.iv_modify_identifying)
    ImageView mIvModifyIdentifying;
    @Bind(R.id.et_phone_code)
    EditText mEtPhoneCode;
    @Bind(R.id.btn_ask_code)
    Button mBtnAskCode;
    @Bind(R.id.et_user_password)
    EditText mEtUserPassword;
    @Bind(R.id.iv_saw)
    ImageView mIvSaw;
    @Bind(R.id.iv_protocol_state)
    ImageView mIvProtocolState;

    private RestClient mRestClient;//retrofit类
    private boolean IsShow =false;//密码是否可见
    private boolean isRead = true;//协议是否同意

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        httpPic();
    }

    private void httpPic() {
        mRestClient = new RestClient();
        long timecurrentTimeMillis = System.currentTimeMillis() / 1000;// 获取时间戳
        String URL = Constant.API_HOST + "/index.php?g=Api&m=Client&a=verifycode&t=" + timecurrentTimeMillis;
        Call<ResponseBody> userCall = mRestClient.getRectService().downloadPicFromNet(URL);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mIvModifyIdentifying.setImageBitmap(BitmapFactory.decodeStream(response.body().byteStream()));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @OnClick(R.id.iv_modify_identifying)
    void changePic(){
        httpPic();
    }

    @OnClick(R.id.iv_clear)
    void clearPassword(){
        mEtUserPassword.setText("");
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

    @OnClick(R.id.iv_protocol_state)
    void readProtocol(){
        if (isRead){
            mIvProtocolState.setImageResource(R.drawable.agree_hui);
            isRead = false;
        }else {
            mIvProtocolState.setImageResource(R.drawable.agree_cai);
            isRead = true;
        }
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.tv_protocol)
    void protocol(){
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Index&a=detail&id=1");
        mIntent.putExtra("title","蜂赢协议");
        startActivity(mIntent);
    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_ask_code)
    void askCode(){
        String phone = mEtUserName.getText().toString().trim();
        String code = mEtPicCode.getText().toString().trim();
        if (phone.isEmpty()){
            showToast("手机号不能为空");
            return;
        }
        if (code.isEmpty()){
            showToast("图形验证码不能为空");
            return;
        }
        //然后在需要用这个的方法里new一个对象，然后调用start();方法就可以啦
        TimeCountUtils timeCountUtils = new TimeCountUtils(ModifyActivity.this, 60000, 1000, mBtnAskCode);
        timeCountUtils.start();
        Subscription subscription = wrapper.askModifyCode(phone,code)
                .subscribe(newSubscriber(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        T.showShort(BeewinApp.getContext(),"短信发送成功!");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_change_password)
    void userChangePassword(){
        final String phone = mEtUserName.getText().toString().trim();
        String newPassword = mEtUserPassword.getText().toString().trim();
        String phoneCode = mEtPhoneCode.getText().toString().trim();
        if (phone.isEmpty()){
            showToast("手机号不能为空!");
            return;
        }
        if (phoneCode.isEmpty()){
            showToast("手机验证码不能为空!");
            return;
        }
        if (newPassword.isEmpty()){
            showToast("密码不能为空!");
            return;
        }
        if (newPassword.length() < 6) {
            showToast("密码长度不能少于6位!");
            return;
        }
        if (!isRead){
            showToast("请阅读并同意协议!");
            return;
        }
        Subscription subscription = wrapper.userChangePassword(phone,newPassword,phoneCode)
                .subscribe(newSubscriber(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        SPUtils.put(BeewinApp.getContext(),"login",phone);
                        showToast("修改成功!");
                        finish();
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
