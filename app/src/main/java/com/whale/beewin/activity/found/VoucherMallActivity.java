package com.whale.beewin.activity.found;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Cash;
import com.whale.beewin.bean.CashInfo;
import com.whale.beewin.utils.SPUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class VoucherMallActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.cash_money)
    TextView mCashMoney;
    @Bind(R.id.tv_card_type)
    TextView mTvCardType;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_deadline)
    TextView mTvDeadline;
    @Bind(R.id.rl_cash)
    RelativeLayout mRlCash;
    @Bind(R.id.et_bug_number)
    EditText mEtBugNumber;
    @Bind(R.id.tv_silver_number)
    TextView mTvSilverNumber;
    @Bind(R.id.tv_coin_existing)
    TextView mTvCoinExisting;
    @Bind(R.id.tv_product_state)
    TextView mTvProductState;
    @Bind(R.id.tv_use_way)
    TextView mTvUseWay;
    @Bind(R.id.tv_mall_earning)
    TextView mTvMallEarning;
    @Bind(R.id.iv_mall_protocol)
    ImageButton mIvMallProtocol;
    @Bind(R.id.tv_mall_protocol)
    TextView mTvMallProtocol;
    @Bind(R.id.rl_main_content)
    LinearLayout mRlMainContent;

    private Cash.MlistBean mVoucher;//代金券对象
    private String syslv;
    private int jifen;//花币数量
    private String mLogin;//用户名
    private String mPwd;//用户密码
    DecimalFormat df = new DecimalFormat("#.00");//格式化数据
    private boolean isOk = true;//协议是否阅读
    private int number = 1;//购买数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchermall);
        syslv = (String) SPUtils.get(BeewinApp.getContext(), "syslv", "");
        mLogin = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        mPwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        jifen = (int) SPUtils.get(BeewinApp.getContext(), "jifen", 0);
        mVoucher = getIntent().getParcelableExtra("voucher");
        InitView();
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        Subscription sub = wrapper.getCashInfo(mVoucher.getId())
                .subscribe(newSubscriber(new Action1<CashInfo>() {
                    @Override
                    public void call(CashInfo cashInfo) {
                        mLlLoading.setVisibility(View.GONE);
                        mRlMainContent.setVisibility(View.VISIBLE);
                        mTvProductState.setText(cashInfo.getSinfo().getDesp());
                        mTvUseWay.setText(cashInfo.getSinfo().getDespuse());
                    }
                }));
        mCompositeSubscription.add(sub);
    }

    private void InitView() {
        mTvCoinExisting.setText(jifen+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mRlCash.setBackground(new BitmapDrawable(ImageLoader.getInstance().loadImageSync(mVoucher.getFile_url())));
                }
            }
        }).start();
        mTvTime.setText(mVoucher.getBscore());
        mTvDeadline.setText(mVoucher.getDays());
        mTvCardType.setText(mVoucher.getTitle().substring(mVoucher.getTitle().length() - 3, mVoucher.getTitle().length()));
        mCashMoney.setText(mVoucher.getTitle().substring(0, mVoucher.getTitle().length() - 3));
        mTvSilverNumber.setText(mVoucher.getBscore());
        if (mVoucher.getCid().equals("0")) {
            mTvMallEarning.setText("￥" + df.format(Float.valueOf(syslv.substring(0, syslv.length() - 1)) / 100 * (Float.valueOf(mVoucher.getScore())) / 365) + "");
        } else if (mVoucher.getCid().equals("1")) {
            mTvMallEarning.setText("购买散投标可抵￥" + mVoucher.getScore());
        } else if (mVoucher.getCid().equals("2")) {
            mTvMallEarning.setText("钱包七日年化收益率增加" + mVoucher.getScore() + "%");
        }

        mEtBugNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(mEtBugNumber.getText().toString().trim())) {
                    number = Integer.parseInt(mEtBugNumber.getText().toString().trim());
                    mTvSilverNumber.setText(Integer.parseInt(mVoucher.getBscore()) * number + "");
                } else {
                    mEtBugNumber.setHint("1");
                    number = 1;
                    mTvSilverNumber.setText(mVoucher.getBscore());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.iv_mall_protocol)
    void seeProtocol(){
        if (isOk) {
            mIvMallProtocol.setBackgroundResource(R.drawable.agree_hui);
            isOk = false;
        } else {
            mIvMallProtocol.setBackgroundResource(R.drawable.agree_cai);
            isOk = true;
        }
    }

    @OnClick(R.id.tv_mall_protocol)
    void protocol(){
        Intent mIntent = new Intent(activity, WithNoCookieActivity.class);
        mIntent.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Index&a=detail&id=1");
        mIntent.putExtra("title","蜂赢+协议");
        startActivity(mIntent);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.iv_add)
    void add(){
        if (mVoucher.getBscore().equals("1")){
            showToast( "最多购买一份!");
            return;
        }
        number++;
        mEtBugNumber.setText(number + "");
        mTvSilverNumber.setText(Integer.parseInt(mVoucher.getBscore()) * number + "");
    }

    @OnClick(R.id.iv_reduce)
    void reduce(){
        if (number == 1) {
            showToast( "最少购买一份!");
            return;
        }
        number--;
        mEtBugNumber.setText(number + "");
        mTvSilverNumber.setText(Integer.parseInt(mVoucher.getBscore()) * number + "");
    }

    @OnClick(R.id.btn_sure)
    void bugCash(){
        if (Integer.parseInt(mTvSilverNumber.getText().toString().trim()) > jifen) {
            showToast("花币数量不足");
            return;
        }
        if (!isOk) {
            showToast( "请仔细阅读协议,若同意请勾选!");
        }

        AlertDialog dialog = new AlertDialog.Builder(VoucherMallActivity.this)
                .setIcon(R.mipmap.dl_logo)
                .setTitle("提示")
                .setMessage("是否确认购买？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        httpBug();
                    }
                })
                .setNegativeButton("再考虑考虑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @SuppressWarnings("unchecked")
    private void httpBug() {
        showLoadingDialog();
        Subscription sub = wrapper.getCashPay(mLogin,mPwd,number + "",mVoucher.getId())
                .subscribe(newSubscriber(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        SPUtils.put(BeewinApp.getContext(), "jifen", (jifen - Integer.parseInt(mTvSilverNumber.getText().toString().trim())));
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }));
    }

}
