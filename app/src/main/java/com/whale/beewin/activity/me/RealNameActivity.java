package com.whale.beewin.activity.me;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.invest.WebPayActivity;
import com.whale.beewin.adapter.BankAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Bank;
import com.whale.beewin.http.RestClient;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class RealNameActivity extends BaseActivity {
    @Bind(R.id.et_real_name)
    EditText mEtRealName;
    @Bind(R.id.et_person_ID)
    EditText mEtPersonID;
    @Bind(R.id.tv_bank)
    TextView mTvBank;
    @Bind(R.id.et_bank_card)
    EditText mEtBankCard;
    @Bind(R.id.et_bank_address)
    EditText mEtBankAddress;
    @Bind(R.id.rl_bank_address)
    RelativeLayout mRlBankAddress;
    @Bind(R.id.tv_one_yuan)
    TextView mTvOneYuan;
    @Bind(R.id.main_content)
    LinearLayout mMainContent;
    //得到银行信息的集合
    int[] logo = {R.drawable.sy_nyyh, R.drawable.sy_pfyh, R.drawable.sy_jtyh, R.drawable.sy_gsyh, R.drawable.sy_ycyh, R.drawable.sy_gfyh
            , R.drawable.sy_msyh, R.drawable.sy_payh, R.drawable.sy_zsyh, R.drawable.sy_zgyh, R.drawable.sy_jsyh, R.drawable.sy_gdyh, R.drawable.sy_xyyh
            , R.drawable.sy_zxyh, R.drawable.sy_hxyh};//银行图标
    private String[] names;//银行名字
    private String[] costs;//银行手续费
    List<Bank> Banks;
    private View mRootView;

    PopupWindow mPopupWindow;//弹窗
    //    银行ListView，适配器
    ListView mLvBank;//银行listview
    TextView mBtnCancel, mBtnSure;
    BankAdapter mBankAdapter;
    int pos= -1;//选中的item
    private String login;//用户名
    private String pwd;//用户密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realname);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        initView();
    }

    private void initView() {
        Banks = new ArrayList<>();
        //        得到银行的信息
        names = getResources().getStringArray(R.array.Bank);
        costs = getResources().getStringArray(R.array.Cost);

        for (int i = 0; i < logo.length; i++) {
            Bank bank = new Bank(logo[i], names[i], costs[i]);
            Banks.add(bank);
        }
        //改变1元的颜色
        SpannableStringBuilder str = new SpannableStringBuilder(mTvOneYuan.getText().toString());
        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.touzi_fenshu)),
                str.length() - 7, str.length() - 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvOneYuan.setText(str);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.rl_card_bank)
    void showPopu(){
        showBankPopuWindow();
    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_real_name)
    void realName(){
        if (pos == -1){
            showToast("请选择所属银行!");
            return;
        }
        if (names[pos].equals("工商银行")||names[pos].equals("农业银行")||names[pos].equals("中国银行")||names[pos].equals("招商银行")
                ||names[pos].equals("光大银行")||names[pos].equals("浦发银行")||names[pos].equals("建设银行")){
            if (mEtBankAddress.getText().toString().isEmpty()){
                showToast("开户行不能为空!");
                return;
            }
        }
        if (mEtRealName.getText().toString().isEmpty()){
            showToast("姓名不能为空");
            return;
        }
        if (mEtPersonID.getText().toString().isEmpty()){
            showToast("身份证号不能为空");
            return;
        }
        if (mEtBankCard.getText().toString().isEmpty()){
            showToast("银行卡号不能为空");
            return;
        }
        showLoadingDialog();
        RestClient mRestClient = new RestClient();
        Call<ResponseBody> userCall = mRestClient.getRectService().realName(login,pwd,mEtRealName.getText().toString(),mEtPersonID.getText().toString(),
                mTvBank.getText().toString(),mEtBankCard.getText().toString(),mEtBankAddress.getText().toString());
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
    //银行选择窗口
    private void showBankPopuWindow() {
        mRootView = LayoutInflater.from(activity).inflate(R.layout.popu_bank, null, false);
        mPopupWindow = new PopupWindow(mRootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mLvBank = (ListView) mRootView.findViewById(R.id.lv_bank);
        mBtnSure = (TextView) mRootView.findViewById(R.id.tv_sure);
        mBtnCancel = (TextView) mRootView.findViewById(R.id.tv_cancel);
        mBankAdapter = new BankAdapter(activity, Banks);
        mLvBank.setAdapter(mBankAdapter);
        mLvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBankAdapter.changeSelected(position);
                pos = position;
            }
        });
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (names[pos].equals("工商银行")||names[pos].equals("农业银行")||names[pos].equals("中国银行")||names[pos].equals("招商银行")
                        ||names[pos].equals("光大银行")||names[pos].equals("浦发银行")||names[pos].equals("建设银行")){
                    mRlBankAddress.setVisibility(View.VISIBLE);
                }else {
                    mRlBankAddress.setVisibility(View.GONE);
                }
                mTvBank.setText(names[pos]);
                mPopupWindow.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.SelectPicDialog);
        mPopupWindow.showAtLocation(mMainContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
