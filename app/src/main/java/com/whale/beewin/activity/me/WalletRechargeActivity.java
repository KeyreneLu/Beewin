package com.whale.beewin.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.invest.WebPayActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Realm;
import com.whale.beewin.http.RestClient;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class WalletRechargeActivity extends BaseActivity {
    @Bind(R.id.et_recharge_money)
    EditText mEtRechargeMoney;
    @Bind(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_card_code)
    TextView mTvCardCode;
    private String phone;//认证手机号
    private String pwd;//用户密码
    private String sstel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walletrecharge);
        phone = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        sstel = (String) SPUtils.get(BeewinApp.getContext(),"sstel","");
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
                        mTvCardCode.setText(realm.getUcardno());
                        SPUtils.put(BeewinApp.getContext(), "uname", realm.getUname());
                        SPUtils.put(BeewinApp.getContext(), "ucardno", realm.getUcardno());
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    private void initView() {
        mTvUserPhone.setText(sstel);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.btn_sure_recharge)
    void recharge(){
        String sum = mEtRechargeMoney.getText().toString();
        if (sum.equals("")) {
            showToast("请输入充值金额");
            return;
        }
        if (Integer.parseInt(sum) < 100) {
            showToast( "充值金额不能少于100");
            return;
        }
        showLoadingDialog();
        RestClient mRestClient = new RestClient();
        Call<ResponseBody> userCall = mRestClient.getRectService().userRecharge(phone,pwd,sum);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                hideLoadingDialog();
                try {
                    JSONObject result = new JSONObject(Utils.JSONTokener(Utils.convertStreamToString(response.body().byteStream())));
                    String status = result.getString("status");
                    String message = result.getString("message");
                    if (status.equals("2022")) {
                        JSONObject resObj = result.getJSONObject("result");
                        String content = resObj.getString("html_text");
                        Intent intent2 = new Intent(activity,WebPayActivity.class);
                        intent2.putExtra("texthtml", content);
                        intent2.putExtra("title", "认证支付");
                        startActivity(intent2);
                        finish();
                    } else if (status.equals("yes")){
                        showToast(message);
                        finish();
                    }else {
                        showToast(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hideLoadingDialog();
                showToast(t.getMessage());
            }
        });
    }
}
