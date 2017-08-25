package com.whale.beewin.activity.invest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.ModifyActivity;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.GoodInfo;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.http.RestClient;
import com.whale.beewin.utils.Constant;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class InvestBugActivity extends BaseActivity {
    @Bind(R.id.tv_invest_name)
    TextView mTvInvestName;
    @Bind(R.id.tv_invest_precent)
    TextView mTvInvestPrecent;
    @Bind(R.id.tv_invest_date)
    TextView mTvInvestDate;
    @Bind(R.id.tv_rest_money)
    TextView mTvRestMoney;
    @Bind(R.id.tv_invest_tip)
    TextView mTvInvestTip;
    @Bind(R.id.tv_invest_money)
    TextView mTvInvestMoney;
    @Bind(R.id.iv_wallet_pay)
    ImageView mIvWalletPay;
    @Bind(R.id.tv_wallet_money)
    TextView mTvWalletMoney;
    @Bind(R.id.iv_card_pay)
    ImageView mIvCardPay;
    @Bind(R.id.tv_protocol)
    ImageButton mTvProtocol;
    @Bind(R.id.tv_loan_protocol)
    TextView mTvLoanProtocol;
    @Bind(R.id.tv_scatter_protocol)
    TextView mTvScatterProtocol;
    @Bind(R.id.btn_join_now)
    Button mBtnJoinNow;
    private int payType = 1;//支付方式
    private boolean isRead = true;//协议是否同意
    private boolean isShowTip = false;//判断dialog是否改变
    private String id;
    private float balance;//钱包余额
    private double sum;//购买金额
    private double minSum;//最低购买金额
    private double maxSum;//可购买的最大金额
    View mRootView;
    private LayoutInflater mInflater;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget, mTvNeedPay, mTv, mTv2, mTvTip;
    private ImageView mIvDelete;
    private Button mBtnCancel, mBtnOk;
    DecimalFormat decimalFormat = new DecimalFormat(".00");
    String login;//用户名
    String pwd;//用户密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investbug);
        id = getIntent().getStringExtra("id");
        balance = (float) SPUtils.get(BeewinApp.getContext(), "ugmoney", 0.0f);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        mTvWalletMoney.setText(balance+"");
        showLoadingDialog();
        Subscription subscription = wrapper.getDetail(id)
                .subscribe(newSubscriber(new Action1<GoodInfo>() {
                    @Override
                    public void call(GoodInfo goodInfo) {
                    GoodInfo.GoodinfoBean bean = goodInfo.getGoodinfo();
                        mTvInvestName.setText(bean.getName() + "第" + bean.getQishu() + "期");
                        mTvInvestPrecent.setText(bean.getYearper() + "%");
                        mTvInvestDate.setText(bean.getTotaltime() + bean.getQtype());
                        mTvRestMoney.setText(bean.getRemone());
                        mTvInvestTip.setText(bean.getPrice()+"元起,100的倍数递增");
                        mTvInvestMoney.setText(bean.getPrice());
                        maxSum = Double.parseDouble(bean.getRemone());
                        minSum = Double.parseDouble(bean.getPrice());
                        sum = minSum;
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.tv_loan_protocol)
    void loanProtocol() {
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m.judayouyuan.com/content/jiedaixieyi.htm");
        mIntent.putExtra("title", "借贷服务协议");
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_scatter_protocol)
    void scatterProtocol() {
        Intent mIntent2 = new Intent(activity, WithNoCookieActivity.class);
        mIntent2.putExtra("url", "http://m.judayouyuan.com/content/santoufuwuxieyi.htm");
        mIntent2.putExtra("title", "散投服务协议");
        startActivity(mIntent2);
    }

    @OnClick(R.id.iv_wallet_pay)
    void walletPay() {
        payType = 1;
        mIvCardPay.setImageResource(R.drawable.agree_hui);
        mIvWalletPay.setImageResource(R.drawable.agree_cai);
    }

    @OnClick(R.id.iv_card_pay)
    void cardPay() {
        payType = 2;
        isShowTip = true;
        mIvCardPay.setImageResource(R.drawable.agree_cai);
        mIvWalletPay.setImageResource(R.drawable.agree_hui);
    }

    @OnClick(R.id.tv_protocol)
    void readProtocol() {
        if (isRead) {
            mTvProtocol.setImageResource(R.drawable.agree_cai);
            isRead = false;
        } else {
            mTvProtocol.setImageResource(R.drawable.agree_hui);
            isRead = true;
        }
    }

    @OnClick(R.id.btn_invest_add)
    void sumAdd(){
        if (sum>(maxSum-100)){
            sum = maxSum;
            return;
        }
        sum+=100;
        mTvInvestMoney.setText(sum+"");
    }

    @OnClick(R.id.btn_invest_reduce)
    void sumReduce(){
        if (sum < (minSum+100)){
            showToast("购买金额最低"+minSum+"元");
            return;
        }
        sum-=100;
        mTvInvestMoney.setText(sum+"");
    }

    @OnClick(R.id.btn_join_now)
    void bugNow(){
        if (sum<balance){
            isShowTip = true;
        }
        showDialog();
    }

    private void showDialog() {
        mInflater = LayoutInflater.from(activity);
        mRootView = mInflater.inflate(R.layout.cz_layout_dialog, null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(activity, R.style.myDialog);
        mDialog.getWindow().setContentView(mRootView);
        mEtMima = (EditText) mRootView.findViewById(R.id.et_mima);
        mIvDelete = (ImageView) mRootView.findViewById(R.id.iv_delete);
        mTvForget = (TextView) mRootView.findViewById(R.id.tv_forget);
        mBtnCancel = (Button) mRootView.findViewById(R.id.btn_cancel);
        mBtnOk = (Button) mRootView.findViewById(R.id.btn_ok);
        mTvNeedPay = (TextView) mRootView.findViewById(R.id.tv_need_pay);
        mTv = (TextView) mRootView.findViewById(R.id.tv);
        mTv2 = (TextView) mRootView.findViewById(R.id.tv2);
        mTvTip = (TextView) mRootView.findViewById(R.id.tv_tip);
        if (isShowTip){
            mTv.setVisibility(View.GONE);
            mTv2.setVisibility(View.GONE);
            mTvTip.setVisibility(View.VISIBLE);
            mTvNeedPay.setVisibility(View.GONE);
        }else {
            mTvNeedPay.setText(decimalFormat.format((sum-balance)));
        }
        /** 自动弹出软键盘 **/
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEtMima, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        mDialog.show();
        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtMima.setText("");
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, ModifyActivity.class));
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtMima.getText().toString())) {
                    showToast("支付密码不能为空");
                } else {
                    mDialog.dismiss();
                    showLoadingDialog();
                    RestClient mRestClient = new RestClient();
                    Call<ResponseBody> userCall = mRestClient.getRectService().investPay(login,pwd,sum+"",id,payType+"",mEtMima.getText().toString().trim());
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
                                    startActivityForResult(intent2, Constant.USE_MONEY);
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode){
                case Constant.USE_MONEY:
                    httpUser();
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void httpUser() {
        showLoadingDialog();
        Subscription subscription = wrapper.getInfo(login,pwd)
                .subscribe(newSubscriber(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        mTvWalletMoney.setText(userInfo.getUgmoney());
                        SPUtils.put(BeewinApp.getContext(),"ugmoney",(float) Double.parseDouble(userInfo.getUgmoney()));
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
