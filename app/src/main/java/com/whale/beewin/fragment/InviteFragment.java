package com.whale.beewin.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class InviteFragment extends BaseFragment {


    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.found_picture)
    RoundImageView mFoundPicture;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.Iv_Code)
    ImageView mIvCode;
    @Bind(R.id.tv_invite_code)
    TextView mTvInviteCode;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    String login;//用户名
    String pwd;//用户密码
    final ApiWrapper wrapper = new ApiWrapper();//网络请求类
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invite;
    }

    @Override
    protected void initView() {

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        Subscription subscription = wrapper.getInfo(login,pwd)
                .subscribe(newSubscriber(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {
                        mLlLoading.setVisibility(View.GONE);
                        mLlMainContent.setVisibility(View.VISIBLE);
                        mTvUserName.setText(userInfo.getAname());
                        mTvInviteCode.setText(userInfo.getUuid());
                        Glide.with(getActivity())
                                .load(userInfo.getUhead())
                                .into(mFoundPicture);

                        Glide.with(getActivity())
                                .load(userInfo.getTicket())
                                .into(mIvCode);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.btn_share_now)
    void  share(){
        UMImage image = new UMImage(getActivity(), R.mipmap.dl_logo);//网络图片
        UMWeb web = new UMWeb("http://m1.judayouyuan.com");
        web.setTitle("蜂赢金服-理财与时俱进");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("身边的朋友每天都在理财，和我一起来？");//描述

        new ShareAction(getActivity())
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
