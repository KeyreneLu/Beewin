package com.whale.beewin.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.activity.NoTitleCookieActivity;
import com.whale.beewin.activity.found.MyFriendActivity;
import com.whale.beewin.activity.found.ShareActivity;
import com.whale.beewin.activity.me.InvestRecordActivity;
import com.whale.beewin.activity.me.MyCashActivity;
import com.whale.beewin.activity.me.PileEarningActivity;
import com.whale.beewin.activity.me.ReckoningActivity;
import com.whale.beewin.activity.me.ScatterRecordActivity;
import com.whale.beewin.activity.me.SettingActivity;
import com.whale.beewin.activity.me.WalletActivity;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.HomeRequest;
import com.whale.beewin.bean.InvestRecord;
import com.whale.beewin.bean.MyCash;
import com.whale.beewin.bean.ScatterRecord;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.utils.Utils;
import com.whale.beewin.view.RoundImageView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func4;

import static com.whale.beewin.utils.Utils.saveBitmap;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class MeFragment extends BaseFragment {
    @Bind(R.id.circle_tou)
    RoundImageView mCircleTou;
    @Bind(R.id.tv_user_number)
    TextView mTvUserNumber;
    @Bind(R.id.tv_user_wallet)
    TextView mTvUserWallet;
    @Bind(R.id.rl_user_wallet)
    RelativeLayout mRlUserWallet;
    @Bind(R.id.tv_amount_investment)
    TextView mTvAmountInvestment;
    @Bind(R.id.tv_wallet_sum)
    TextView mTvWalletSum;
    @Bind(R.id.tv_tender_number)
    TextView mTvTenderNumber;
    @Bind(R.id.tv_record_number)
    TextView mTvRecordNumber;
    @Bind(R.id.tv_card_number)
    TextView mTvCardNumber;
    @Bind(R.id.tv_me_member)
    TextView mTvMeMember;
    @Bind(R.id.line_login)
    LinearLayout mLineLogin;
    @Bind(R.id.Main_content)
    FrameLayout mMainContent;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_main_content)
    LinearLayout mLlMainContent;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;//拍照状态码
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;//图片选择状态码
    private static final int RESULT_CANCELED = 0;//取消
    private static String picFileFullName;//图片名称
    private PopupWindow mPopupWindow;//弹窗
    private String pwd;//用户密码
    private String login;//用户名
    private int page = 1;//页数
    private int mIslogin; //是否登录
    final ApiWrapper wrapper = new ApiWrapper();//网络请求类
    private boolean isUser = true;//是否通过网络加载图片
    Subscription subscription;
    private boolean isChangeUser;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
//        refreshUi();
    }

    private void refreshUi() {
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        if (mIslogin == 1) {
            mLineLogin.setVisibility(View.GONE);
            mTvMeMember.setVisibility(View.VISIBLE);
        } else {
            mLlLoading.setVisibility(View.GONE);
            mLlMainContent.setVisibility(View.VISIBLE);
            mTvCardNumber.setVisibility(View.GONE);
            mTvRecordNumber.setVisibility(View.GONE);
            mTvTenderNumber.setVisibility(View.GONE);
            mTvMeMember.setVisibility(View.GONE);
            mTvUserNumber.setText("未登录");
            mTvWalletSum.setText("");
            mTvUserWallet.setText(R.string.none);
            mTvAmountInvestment.setText(R.string.none);
            mCircleTou.setImageResource(R.drawable.head_default);
            mLineLogin.setVisibility(View.VISIBLE);
            mLineLogin.setOnClickListener(null);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
//        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
//        if (mIslogin == 1) {
//            mLineLogin.setVisibility(View.GONE);
//            mTvMeMember.setVisibility(View.VISIBLE);
//        } else {
//            mLlLoading.setVisibility(View.GONE);
//            mLlMainContent.setVisibility(View.VISIBLE);
//            mTvCardNumber.setVisibility(View.GONE);
//            mTvRecordNumber.setVisibility(View.GONE);
//            mTvTenderNumber.setVisibility(View.GONE);
//            mTvMeMember.setVisibility(View.GONE);
//            mTvUserNumber.setText("未登录");
//            mTvWalletSum.setText("");
//            mTvUserWallet.setText(R.string.none);
//            mTvAmountInvestment.setText(R.string.none);
//            mCircleTou.setImageResource(R.drawable.head_default);
//            mLineLogin.setVisibility(View.VISIBLE);
//            mLineLogin.setOnClickListener(null);
//        }
        if (mIslogin == 1) {
            subscription = Observable.zip(wrapper.getInfo(login, pwd), wrapper.getInvestRecord()
                    , wrapper.getScatterRecord(login, pwd, page + ""), wrapper.getMyCash(login, pwd)
                    , new Func4<UserInfo, InvestRecord, ScatterRecord, MyCash, HomeRequest>() {
                        @Override
                        public HomeRequest call(UserInfo userInfo, InvestRecord investRecord, ScatterRecord scatterRecord, MyCash myCash) {
                            HomeRequest request = new HomeRequest();
                            request.setUserInfo(userInfo);
                            request.setInvestRecord(investRecord);
                            request.setMyCash(myCash);
                            request.setScatterRecord(scatterRecord);
                            return request;
                        }
                    })
                    .subscribe(newSubscriber(new Action1<HomeRequest>() {
                        @Override
                        public void call(HomeRequest request) {
                            mLlLoading.setVisibility(View.GONE);
                            mLlMainContent.setVisibility(View.VISIBLE);
                            //投资记录
                            if (request.getInvestRecord().getGoods() != null && request.getInvestRecord().getGoods().size() != 0) {
                                mTvRecordNumber.setVisibility(View.VISIBLE);
                                int sum = request.getInvestRecord().getGoods().size();
                                if (sum > 99) {
                                    mTvRecordNumber.setText("99");
                                } else {
                                    mTvRecordNumber.setText(sum + "");
                                }
                            } else {
                                mTvRecordNumber.setVisibility(View.GONE);
                            }
                            //散投记录
                            if (request.getScatterRecord().getMlist() != null && request.getScatterRecord().getMlist().size() != 0) {
                                mTvTenderNumber.setVisibility(View.VISIBLE);
                                int sum = request.getScatterRecord().getMlist().size();
                                if (sum > 99) {
                                    mTvTenderNumber.setText("99");
                                } else {
                                    mTvTenderNumber.setText(sum + "");
                                }
                            } else {
                                mTvTenderNumber.setVisibility(View.GONE);
                            }

                            //卡卷
                            if (request.getMyCash().getMlist() != null && request.getMyCash().getMlist().size() != 0) {
                                mTvCardNumber.setVisibility(View.VISIBLE);
                                int sum = request.getMyCash().getMlist().size();
                                if (sum > 99) {
                                    mTvCardNumber.setText("99");
                                } else {
                                    mTvCardNumber.setText(sum + "");
                                }
                            } else {
                                mTvCardNumber.setVisibility(View.GONE);
                            }
                            //用户信息
                            UserInfo userInfo = request.getUserInfo();
                            Log.e("userinfo",userInfo.toString());
                            SPUtils.put(BeewinApp.getContext(), "ulevel", Integer.parseInt(userInfo.getUlevel()));
                            for (int i = 0; i < 7; i++) {
                                if (userInfo.getUlevel().equals(i + "")) {
                                    mTvMeMember.setText("L" + i);
                                    break;
                                }
                            }
                            mTvUserNumber.setText(userInfo.getSstel());
                            mTvUserWallet.setText(userInfo.getTmoney());
                            SPUtils.put(getActivity(), "imoney", (float) Double.parseDouble(userInfo.getImoney()));
                            SPUtils.put(getActivity(),"ugmoney",(float) Double.parseDouble(userInfo.getUgmoney()));
                            mTvAmountInvestment.setText(userInfo.getUcmoney());
                            mTvWalletSum.setText(userInfo.getImoney());
                            Log.e("uhead", userInfo.getUhead());
                            if (!userInfo.getUhead().equals("http://m1.judayouyuan.com/")) {
                                Glide.with(getActivity())
                                        .load(userInfo.getUhead())
                                        .into(mCircleTou);
                            }
                        }
                    }));
            mCompositeSubscription.add(subscription);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isChangeUser = (boolean) SPUtils.get(BeewinApp.getContext(), "isChangeUser", false);
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        if (mIslogin == 1) {
            mLineLogin.setVisibility(View.GONE);
            mTvMeMember.setVisibility(View.VISIBLE);
            if (isChangeUser) {
                showLoadingDialog();
                loadData();
                SPUtils.put(BeewinApp.getContext(), "isChangeUser", false);
            }
        } else {
            mLlLoading.setVisibility(View.GONE);
            mLlMainContent.setVisibility(View.VISIBLE);
            mTvCardNumber.setVisibility(View.GONE);
            mTvRecordNumber.setVisibility(View.GONE);
            mTvTenderNumber.setVisibility(View.GONE);
            mTvMeMember.setVisibility(View.GONE);
            mTvUserNumber.setText("未登录");
            mTvWalletSum.setText("");
            mTvUserWallet.setText(R.string.none);
            mTvAmountInvestment.setText(R.string.none);
            mCircleTou.setImageResource(R.drawable.head_default);
            mLineLogin.setVisibility(View.VISIBLE);
            mLineLogin.setOnClickListener(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.circle_tou)
    void changePic() {
        showDialog();
        isUser = false;
    }

    void seeIncome() {
        startActivity(new Intent(getActivity(), PileEarningActivity.class));
    }

    void userReckoning() {
        startActivity(new Intent(getActivity(), ReckoningActivity.class));
    }

    @OnClick(R.id.ll_me_wallet)
    void userWallet() {
        startActivity(new Intent(getActivity(), WalletActivity.class));
    }

    void seeTender() {
        startActivity(new Intent(getActivity(), ScatterRecordActivity.class));
    }

    void seeRecord() {
        startActivity(new Intent(getActivity(), InvestRecordActivity.class));
    }

    void seeCash() {
        Intent intent = new Intent(getActivity(), MyCashActivity.class);
        intent.putExtra("flag", "0");
        startActivity(intent);
    }

    void seeMember() {
        Intent intent = new Intent(getActivity(), NoTitleCookieActivity.class);
        intent.putExtra("url", "http://m1.judayouyuan.com/content/vip.htm");
        intent.putExtra("title", "会员福利");
        startActivity(intent);
    }

    void seeConnection() {
        startActivity(new Intent(getActivity(), MyFriendActivity.class));
    }

    void seeCode() {
        startActivity(new Intent(getActivity(), ShareActivity.class));
    }

    @OnClick(R.id.ll_me_setting)
    void seeSetting() {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    @OnClick(R.id.button_me_login)
    void userLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        SPUtils.put(BeewinApp.getContext(), "isChangeUser", true);
    }

    private void showDialog() {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_choose, null);
        mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.SelectPicDialog);
        Button mTakePhotoBtn = (Button) view.findViewById(R.id.takePhotoBtn);
        Button mPickPhotoBtn = (Button) view.findViewById(R.id.pickPhotoBtn);
        Button mCancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        // 拍照按钮的监听事件
        mTakePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();// 拍照上传
                mPopupWindow.dismiss();
            }
        });
        mPickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();// 本地照片上传
                mPopupWindow.dismiss();
            }
        });
        // 拍照取消按钮的监听事件
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(mMainContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 相机拍照
     */
    private void takePicture() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir, "bee_head.jpg");
            picFileFullName = outFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            showToast("请确认已经插入SD卡");
        }
    }

    private void openAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == this.getActivity().RESULT_OK) {
                mCircleTou.setImageBitmap(BitmapFactory.decodeFile(picFileFullName));
                File file = Utils.saveBitmap(BitmapFactory.decodeFile(picFileFullName));
                updateImage(picFileFullName);
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                showToast("拍照失败");
            }
        } else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == this.getActivity().RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    ContentResolver resolver = getActivity().getContentResolver();
                    try {
                        Bitmap bm = MediaStore.Images.Media.getBitmap(resolver, uri);
                        mCircleTou.setImageBitmap(bm);
                        File file = saveBitmap(bm);
                        updateImage(file.getPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    showToast("从相册获取图片失败");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void updateImage(String picpath) {
        showLoadingDialog();
        subscription = wrapper.updateHeadPic(login, pwd, picpath)
                .subscribe(newSubscriber(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        showToast("上传成功");
                    }
                }));
    }
}
