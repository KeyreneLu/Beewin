package com.whale.beewin.activity.found;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.ModifyActivity;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.HeaderObject;
import com.whale.beewin.utils.SPUtils;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 花币充值
 * Created by Administrator on 2017/4/5 0005.
 */

public class RechargeActivity extends BaseActivity {

    @Bind(R.id.btn_hundred_coin)
    TextView mBtnHundredCoin;
    @Bind(R.id.btn_thousand_coin)
    TextView mBtnThousandCoin;
    @Bind(R.id.btn_wan_coin)
    TextView mBtnWanCoin;
    @Bind(R.id.et_other)
    EditText mEtOther;
    @Bind(R.id.btn_other_coin)
    RelativeLayout mBtnOtherCoin;
    @Bind(R.id.tv_user_pay)
    TextView mTvUserPay;
    @Bind(R.id.tv_wallet_money)
    TextView mTvWalletMoney;
    @Bind(R.id.iv_protocol)
    ImageButton mIvProtocol;
    @Bind(R.id.tv_coin_protocol)
    TextView mTvCoinProtocol;

    private Boolean isok = true;//协议是否阅读
    private String mLogin;//用户名
    private String password;//用户密码
    private float number = 1;//购买数量
    private float mImoney;//账户余额
    private String uuid;//用户id
    private int jifen;//花币数量
    DecimalFormat df = new DecimalFormat("#.00");//格式化数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        mLogin = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        password = (String) SPUtils.get(BeewinApp.getContext(),"pwd","");
        mImoney = (float) SPUtils.get(BeewinApp.getContext(), "imoney",0.0f);
        uuid = (String) SPUtils.get(BeewinApp.getContext(),"uuid","");
        jifen = (int) SPUtils.get(BeewinApp.getContext(),"jifen",0);
        initView();
    }

    private void initView() {
        mTvWalletMoney.setText(mImoney + "");
        mEtOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(mEtOther.getText().toString())) {
                    mTvUserPay.setText(Float.valueOf(mEtOther.getText().toString()) / 100 + "元");
                    number = Float.valueOf(mEtOther.getText().toString()) / 100;
                } else {
                    mTvUserPay.setText("0" + "元");
                    number = 0;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.iv_protocol)
    void readProtocol(){
        if (isok){
            mIvProtocol.setImageResource(R.drawable.agree_hui);
            isok =false;
        }else {
            mIvProtocol.setImageResource(R.drawable.agree_cai);
            isok =true;
        }
    }

    @OnClick(R.id.tv_coin_protocol)
    void protocol(){
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/content/xieyi_score.htm");
        mIntent.putExtra("title", "花币使用协议");
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_hundred_coin)
    void bug100(){
        mBtnHundredCoin.setBackgroundResource(R.drawable.rect_blue1);
        mBtnThousandCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnWanCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnOtherCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnHundredCoin.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mBtnThousandCoin.setTextColor(getResources().getColor(R.color.three_color));
        mBtnWanCoin.setTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setHintTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setText("");
        number =1;
        hideSoftInputView();
        mEtOther.setCursorVisible(false);
        mTvUserPay.setText("1元");
    }

    @OnClick(R.id.btn_thousand_coin)
    void bug1000(){
        mBtnThousandCoin.setBackgroundResource(R.drawable.rect_blue1);
        mBtnHundredCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnWanCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnOtherCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnThousandCoin.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mBtnHundredCoin.setTextColor(getResources().getColor(R.color.three_color));
        mBtnWanCoin.setTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setHintTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setText("");
        number = 10;
        hideSoftInputView();
        mEtOther.setCursorVisible(false);
        mTvUserPay.setText("10元");
    }

    @OnClick(R.id.btn_wan_coin)
    void bug10000(){
        mBtnWanCoin.setBackgroundResource(R.drawable.rect_blue1);
        mBtnHundredCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnThousandCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnOtherCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnWanCoin.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mBtnHundredCoin.setTextColor(getResources().getColor(R.color.three_color));
        mBtnThousandCoin.setTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setHintTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setText("");
        number = 100;
        hideSoftInputView();
        mEtOther.setCursorVisible(false);
        mTvUserPay.setText("100元");
    }

    @OnClick(R.id.et_other)
    void bugother(){
        mBtnOtherCoin.setBackgroundResource(R.drawable.rect_blue1);
        mBtnHundredCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnThousandCoin.setBackgroundResource(R.drawable.rect_gray7);
        mBtnWanCoin.setBackgroundResource(R.drawable.rect_gray7);
        mEtOther.setTextColor(getResources().getColor(R.color.touzi_fenshu));
        mBtnHundredCoin.setTextColor(getResources().getColor(R.color.three_color));
        mBtnThousandCoin.setTextColor(getResources().getColor(R.color.three_color));
        mBtnWanCoin.setTextColor(getResources().getColor(R.color.three_color));
        mEtOther.setCursorVisible(true);
        mTvUserPay.setText("0元");
    }

    @OnClick(R.id.btn_bug_now)
    void bugCoin(){
        if (number<1){
           showToast("购买金额不能少于1元!");
            return;
        }
        if (number >mImoney){
            showToast("余额不足!");
            return;
        }
        if (!isok){
            showToast("请阅读并同意《花币使用协议》");
            return;
        }
        showDialog();
    }

    private void showDialog() {
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
                Subscription sub = wrapper.coinPay(mLogin,password,number+"", HeaderObject.getMD5(uuid+"bw"+HeaderObject.getMD5(edittext.getText().toString())))
                        .subscribe(newSubscriber(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                showToast("充值成功!");
                                SPUtils.put(BeewinApp.getContext(),"imoney",Float.parseFloat(df.format((mImoney-number))));
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }));
                mCompositeSubscription.add(sub);
            }
        });
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
