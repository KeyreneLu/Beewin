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
import com.whale.beewin.activity.me.MyCashActivity;
import com.whale.beewin.base.BaseActivity;
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
 * 蜂赢散投
 * Created by Administrator on 2017/3/30 0030.
 */

public class ScatterRealActivity extends BaseActivity {
    @Bind(R.id.tv_scatter_money)
    TextView mTvScatterMoney;
    @Bind(R.id.iv_five_part)
    ImageView mIvFivePart;
    @Bind(R.id.iv_ten_part)
    ImageView mIvTenPart;
    @Bind(R.id.iv_fifteen_part)
    ImageView mIvFifteenPart;
    @Bind(R.id.iv_twenty_part)
    ImageView mIvTwentyPart;
    @Bind(R.id.iv_three_month)
    ImageView mIvThreeMonth;
    @Bind(R.id.iv_six_month)
    ImageView mIvSixMonth;
    @Bind(R.id.iv_twelve_month)
    ImageView mIvTwelveMonth;
    @Bind(R.id.tv_scatter_precent)
    TextView mTvScatterPrecent;
    @Bind(R.id.tv_scatter_lucre)
    TextView mTvScatterLucre;
    @Bind(R.id.tv_scatter_back)
    TextView mTvScatterBack;
    @Bind(R.id.tv_cash_money)
    TextView mTvCashMoney;
    @Bind(R.id.iv_wallet_pay)
    ImageView mIvWalletPay;
    @Bind(R.id.tv_wallet_money)
    TextView mTvWalletMoney;
    @Bind(R.id.iv_card_pay)
    ImageView mIvCardPay;
    @Bind(R.id.tv_protocol)
    ImageButton mTvProtocol;

    DecimalFormat decimalFormat = new DecimalFormat(".00");
    private int sum = 2000;//散投金额
    private int fenCount = 1;//拆分份数1---5  2---10  3---15  4----20
    private int date = 1;//散投时长1---3 2-----6 3 ----12
    private int month = 3;
    private float balance;//钱包余额
    private int cashMoney = 0;//代金券的钱数
    private int payType = 1;//支付方式
    private boolean isRead = true;//协议是否同意
    private boolean isShowTip = false;//判断dialog是否改变
    View mRootView;
    private LayoutInflater mInflater;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget, mTvNeedPay, mTv, mTv2, mTvTip;
    private ImageView mIvDelete;
    private Button mBtnCancel, mBtnOk;
    private String qid="";
    private String score;
    String login;//用户名
    String pwd;//用户密码
    private int number = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatterreal);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        balance = (float) SPUtils.get(BeewinApp.getContext(), "ugmoney",0.0f);
        mTvWalletMoney.setText(balance+"");
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @OnClick(R.id.btn_money_reduce)
    void reduceScatter() {
        if (sum < 3000) {
            showToast("最低购买金额为2000元");
            return;
        }
        sum -= 1000;
        computeData();
    }

    @OnClick(R.id.btn_money_add)
    void addScatter() {
        sum += 1000;
        computeData();
    }

    @OnClick(R.id.iv_five_part)
    void fivePart() {
        fenCount = 1;
        mIvFivePart.setImageResource(R.drawable.agree_cai);
        mIvTenPart.setImageResource(R.drawable.agree_hui);
        mIvFifteenPart.setImageResource(R.drawable.agree_hui);
        mIvTwentyPart.setImageResource(R.drawable.agree_hui);
    }

    @OnClick(R.id.iv_ten_part)
    void tenPart() {
        fenCount = 2;
        mIvFivePart.setImageResource(R.drawable.agree_hui);
        mIvTenPart.setImageResource(R.drawable.agree_cai);
        mIvFifteenPart.setImageResource(R.drawable.agree_hui);
        mIvTwentyPart.setImageResource(R.drawable.agree_hui);
    }

    @OnClick(R.id.iv_fifteen_part)
    void fifteenPart() {
        fenCount = 3;
        mIvFivePart.setImageResource(R.drawable.agree_hui);
        mIvTenPart.setImageResource(R.drawable.agree_hui);
        mIvFifteenPart.setImageResource(R.drawable.agree_cai);
        mIvTwentyPart.setImageResource(R.drawable.agree_hui);
    }

    @OnClick(R.id.iv_twenty_part)
    void twentyPart() {
        fenCount = 4;
        mIvFivePart.setImageResource(R.drawable.agree_hui);
        mIvTenPart.setImageResource(R.drawable.agree_hui);
        mIvFifteenPart.setImageResource(R.drawable.agree_hui);
        mIvTwentyPart.setImageResource(R.drawable.agree_cai);
    }

    @OnClick(R.id.iv_three_month)
    void threeMonth() {
        mIvThreeMonth.setImageResource(R.drawable.agree_cai);
        mIvSixMonth.setImageResource(R.drawable.agree_hui);
        mIvTwelveMonth.setImageResource(R.drawable.agree_hui);
        date = 1;
        month = 3;
        number = 4;
        mTvScatterPrecent.setText("10.39%");
        computeData();
    }

    @OnClick(R.id.iv_six_month)
    void sixMonth() {
        mIvThreeMonth.setImageResource(R.drawable.agree_hui);
        mIvSixMonth.setImageResource(R.drawable.agree_cai);
        mIvTwelveMonth.setImageResource(R.drawable.agree_hui);
        date = 2;
        month = 6;
        number = 2;
        mTvScatterPrecent.setText("12.33%");
        computeData();
    }

    @OnClick(R.id.iv_twelve_month)
    void twelveMonth() {
        mIvThreeMonth.setImageResource(R.drawable.agree_hui);
        mIvSixMonth.setImageResource(R.drawable.agree_hui);
        mIvTwelveMonth.setImageResource(R.drawable.agree_cai);
        date = 3;
        month = 12;
        number = 1;
        mTvScatterPrecent.setText("15.04%");
        computeData();
    }

    @OnClick(R.id.rl_scatter_cash)
    void useCash() {
        Intent intent = new Intent(activity, MyCashActivity.class);
        intent.putExtra("flag","1");
        startActivityForResult(intent, Constant.BUG_FLAG);
    }

    @OnClick(R.id.iv_wallet_pay)
    void walletPay() {
        payType = 1;
        isShowTip = false;
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

    @OnClick(R.id.btn_join_now)
    void bugScatter() {
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
                    Call<ResponseBody> userCall = mRestClient.getRectService().userPay(login,pwd,sum+"","0",fenCount+"",date+"",qid,payType+"",mEtMima.getText().toString().trim());
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
                                    startActivityForResult(intent2,Constant.USE_MONEY);
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

    private void computeData() {
        mTvScatterMoney.setText(decimalFormat.format(sum) + "元");
        mTvScatterLucre.setText(decimalFormat.format((sum * 0.07 / number)) + "元");
        mTvScatterBack.setText(decimalFormat.format((((sum * 0.07 / number) + sum) / month)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null &&resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.BUG_FLAG:
                    qid = data.getStringExtra("qid");
                    score = data.getStringExtra("score");
                    mTvCashMoney.setText(score+"元");
                    break;
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
