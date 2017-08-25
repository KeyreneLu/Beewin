package com.whale.beewin.fragment;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.activity.found.LabelActivity;
import com.whale.beewin.activity.found.MallActivity;
import com.whale.beewin.activity.found.MyCoinActivity;
import com.whale.beewin.activity.found.MyFriendActivity;
import com.whale.beewin.activity.found.ShareActivity;
import com.whale.beewin.adapter.GameAdapter;
import com.whale.beewin.adapter.ImageAdapter;
import com.whale.beewin.base.BaseFragment;
import com.whale.beewin.bean.Game;
import com.whale.beewin.http.ApiWrapper;
import com.whale.beewin.utils.SPUtils;
import com.whale.beewin.view.MyGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class FoundFragment extends BaseFragment {

    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.gallery)
    MyGallery mGallery;
    @Bind(R.id.tv_huaBi)
    TextView mTvHuaBi;
    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.main_content)
    LinearLayout mMainContent;
    private ImageAdapter adapter;//VIP标签适配器
    private int mIslogin;//是否登录
    private int mJifen;//花币数量
    final ApiWrapper wrapper = new ApiWrapper();
    public List<Game.HlistBean> list = new ArrayList<>();//游戏集合
    private GameAdapter mAdapter;//游戏列表适配器
    private boolean isRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView() {

        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        mJifen = (int) SPUtils.get(BeewinApp.getContext(), "jifen", 0);
        if (mIslogin == 1) {
            mTvHuaBi.setText(mJifen + "个");
        } else {
            mTvHuaBi.setText("0个");
        }

        adapter = new ImageAdapter(getActivity());
        mGallery.setAdapter(adapter); // gallery添加ImageAdapter图片资源
        mGallery.setGravity(Gravity.CENTER_HORIZONTAL); // 设置水平居中显示
        mGallery.setSelection(adapter.imgs.length * 70);// 设置起始图片显示位置（可以用来制作gallery循环显示效果）
        mGallery.setUnselectedAlpha(1.0f); // 设置未选中图片的透明度
        mGallery.setSpacing(10); // 设置图片之间的间距
//        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int vip = position % 6;
//                Intent mintent = new Intent(getActivity(), VipActivity.class);
//                mintent.putExtra("vip", vip);
//                startActivity(mintent);
//            }
//        });

//        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mIslogin == 1) {
//                    Intent mIntent = new Intent(getActivity(), WebActivity.class);
//                    mIntent.putExtra("game", list.get(position));
//                    mIntent.putExtra("flag", 0);
//                    startActivity(mIntent);
//                } else {
//                    Intent intent = new Intent();
//                    intent.setClass(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
        mJifen = (int) SPUtils.get(BeewinApp.getContext(), "jifen", 0);
        if (mIslogin == 1) {
            mTvHuaBi.setText(mJifen + "个");
        } else {
            mTvHuaBi.setText("0个");
        }
//        isRefresh = (boolean) SPUtils.get(BeewinApp.getContext(), "isRefresh", false);
//        if (isRefresh) {
//            initView();
//        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void loadData() {
//        mIslogin = (int) SPUtils.get(BeewinApp.getContext(), "islogin", -1);
//        mJifen = (int) SPUtils.get(BeewinApp.getContext(), "jifen", 0);
//        if (mIslogin == 1) {
//            mTvHuaBi.setText(mJifen + "个");
//        } else {
//            mTvHuaBi.setText("0个");
//        }
        Subscription subscription = wrapper.getGame()
                .subscribe(newSubscriber(new Action1<Game>() {
                    @Override
                    public void call(Game game) {
                        mLlLoading.setVisibility(View.GONE);
                        mMainContent.setVisibility(View.VISIBLE);
                        if (list != null) {
                            list.clear();
                        }
                        list.addAll(game.getHlist());
                        mAdapter = new GameAdapter(getActivity(), list);
                        mGridview.setAdapter(mAdapter);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    void share() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), ShareActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void label() {
        startActivity(new Intent(getActivity(), LabelActivity.class));
    }


    void findCoin() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), MyCoinActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void goMarket() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), MallActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }


    void goFriend() {
        if (mIslogin == 1) {
            startActivity(new Intent(getActivity(), MyFriendActivity.class));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
}
