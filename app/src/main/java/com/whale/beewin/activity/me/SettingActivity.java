package com.whale.beewin.activity.me;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.ModifyActivity;
import com.whale.beewin.activity.NoTitleCookieActivity;
import com.whale.beewin.activity.WithNoCookieActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;


/**
 * 个人中心界面
 * Created by Administrator on 2017/4/7 0007.
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @Bind(R.id.iv_member_icon)
    ImageView mIvMemberIcon;
    @Bind(R.id.tv_realm_status)
    TextView mTvRealmStatus;
    @Bind(R.id.tv_app_version)
    TextView mTvAppVersion;
    @Bind(R.id.rl_realm_name)
    RelativeLayout mRlRealmName;
    @Bind(R.id.iv_sign_code)
    ImageView mIvSignCode;
    @Bind(R.id.main_content)
    LinearLayout mMainContent;
    private int levelImageId[] = {R.drawable.vip_a, R.drawable.vip_b, R.drawable.vip_c, R.drawable.vip_d, R.drawable.vip_e, R.drawable.vip_f, R.drawable.vip_g};
    private int level;//会员等级
    private String sstel;//手机号
    private String login;//用户名
    private String pwd;//用户密码
    private boolean isOpen;
    private PopupWindow mPopupWindow;
    private int bcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        level = (int) SPUtils.get(BeewinApp.getContext(), "ulevel", 0);
        sstel = (String) SPUtils.get(BeewinApp.getContext(), "sstel", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        bcard = (int) SPUtils.get(BeewinApp.getContext(), "bcard", 0);
        initView();
    }

    private void initView() {
        if (bcard == 1) {
            mRlRealmName.setClickable(false);
            mTvRealmStatus.setText("已认证");
        } else {
            mTvRealmStatus.setText("未认证");
        }
        for (int i = 0; i < 7; i++) {
            if (level == i) {
                mIvMemberIcon.setImageResource(levelImageId[i]);
                break;
            }
        }
        mTvUserPhone.setText(sstel);
        mTvAppVersion.setText("V" + Utils.getVersionName(activity));
        if (SPUtils.contains(BeewinApp.getContext(), (String) SPUtils.get(BeewinApp.getContext(), "login", "")) && (Boolean) SPUtils.get(BeewinApp.getContext(), "isShowJiuGongGe", false) == false) {
            mIvSignCode.setImageResource(R.drawable.kai);
            isOpen = true;
        } else {
            mIvSignCode.setImageResource(R.drawable.guan);
            isOpen = false;
        }
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    void callService() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:4000275671"));
        startActivity(intent);
    }

    @OnClick(R.id.iv_sign_code)
    void toggle() {
        if (isOpen) {
            mIvSignCode.setImageResource(R.drawable.guan);
            SPUtils.put(BeewinApp.getContext(), "isShowJiuGongGe", true);
            isOpen = false;
        } else {
            mIvSignCode.setImageResource(R.drawable.kai);
            SPUtils.put(BeewinApp.getContext(), "isShowJiuGongGe", false);
            isOpen = true;
        }
    }

    @OnClick(R.id.rl_user_info)
    void seeInfo() {
        startActivity(new Intent(activity, AccountActivity.class));
    }

    void modifyPwd() {
        startActivity(new Intent(activity, ModifyActivity.class));
    }

    @OnClick(R.id.rl_about_us)
    void about() {
        Intent intent = new Intent(activity, WithNoCookieActivity.class);
        intent.putExtra("url", "http://m1.judayouyuan.com/index.php?g=App&m=Index&a=detail&id=2");
        intent.putExtra("title", "关于我们");
        startActivity(intent);
    }

    void userCode() {
        startActivity(new Intent(activity, QRCodeActivity.class));
    }

    void feedback() {
        startActivity(new Intent(activity, FeedBackActivity.class));
    }

    void daily() {
        Intent intent = new Intent(activity, WithNoCookieActivity.class);
        intent.putExtra("url", "http://m1.judayouyuan.com/content/questton.htm");
        intent.putExtra("title", "常见问题");
        startActivity(intent);
    }

    void realName() {
        startActivity(new Intent(activity, RealNameActivity.class));
    }

    @OnClick(R.id.tv_app_exit)
    void exitApplication() {
        View view = getLayoutInflater().inflate(R.layout.dialog_exit_app, null);
        mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.SelectPicDialog);
        RelativeLayout mRlExit = (RelativeLayout) view.findViewById(R.id.rl_exit);
        RelativeLayout mRlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        //取消
        mRlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mRlExit.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Subscription subscription = wrapper.appexit()
                        .subscribe(newSubscriber(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                mPopupWindow.dismiss();
                                SPUtils.put(BeewinApp.getContext(), "isChangeUser", true);
                                SPUtils.put(BeewinApp.getContext(), "islogin", 0);
                                showToast("退出成功");
                                finish();
                            }
                        }));
                mCompositeSubscription.add(subscription);
            }
        });
        mPopupWindow.showAtLocation(mMainContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    void member() {
        Intent intent = new Intent(activity, NoTitleCookieActivity.class);
        intent.putExtra("url", "http://m1.judayouyuan.com/content/vip.htm");
        intent.putExtra("title", "会员福利");
        startActivity(intent);
    }
}
