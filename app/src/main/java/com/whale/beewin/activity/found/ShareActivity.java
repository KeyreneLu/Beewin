package com.whale.beewin.activity.found;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.RoundImageView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 分享界面
 * Created by Administrator on 2017/4/4 0004.
 */

public class ShareActivity extends BaseActivity {
    @Bind(R.id.iv_user_pic)
    RoundImageView mIvUserPic;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.iv_QR_Code)
    ImageView mIvQRCode;
    @Bind(R.id.tv_invite_code)
    TextView mTvInviteCode;
    String login;//用户名
    String pwd;//用户密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        httpData();
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        showLoadingDialog();
        Subscription subscription = wrapper.getInfo(login,pwd)
                .subscribe(newSubscriber(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        mTvUserName.setText(userInfo.getAname());
                        mTvInviteCode.setText(userInfo.getUuid());
                        Glide.with(activity)
                                .load(userInfo.getUhead())
                                .into(mIvUserPic);

                        Glide.with(activity)
                                .load(userInfo.getTicket())
                                .into(mIvQRCode);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @OnClick(R.id.btn_share_now)
    void share(){
        UMImage image = new UMImage(activity, R.mipmap.dl_logo);//网络图片
        UMWeb web = new UMWeb("http://m1.judayouyuan.com");
        web.setTitle("蜂赢金服-理财与时俱进");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("身边的朋友每天都在理财，和我一起来？");//描述

        new ShareAction(activity)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        showToast("分享成功啦");
                    }
                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        showToast("分享失败啦");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        showToast("分享取消了");
                    }
                })
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withMedia(web)
                .open();
    }
}
