package com.whale.beewin.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.whale.beewin.http.RetrofitUtil;
import com.whale.beewin.view.DialogLoading;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


/**
 * Fragment的基类
 * Created by Administrator on 2016/11/23 0023.
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = "RxJava";
    private View view;
    private DialogLoading loading;
    protected Toast mToast = null;
    protected Activity mActivity;
    private boolean isPrepared;
    private boolean isFirst = true;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected Activity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //log("   1__onAttach");
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //log("    3_onCreateView");
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            initView();
        }
        return view;
    }

    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Subscriber newSubscriber(final Action1<? super T> onNext) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                hideLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof RetrofitUtil.APIException) {
                    RetrofitUtil.APIException exception = (RetrofitUtil.APIException) e;
                    showToast(exception.message);
                } else if (e instanceof SocketTimeoutException) {
                    showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    showToast(e.getMessage());
                }
                Log.e(TAG, String.valueOf(e.getMessage()));
                hideLoadingDialog();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }

        };
    }

    /**
     * 显示一个Toast信息
     *
     * @param content
     */
    public void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }

    protected void showLoadingDialog() {
        if (loading == null) {
            loading = new DialogLoading(getActivity());
        }
        loading.show();
    }

    protected void hideLoadingDialog() {
        if (loading != null) {
            loading.dismiss();
        }
    }

    //是否可见
    protected boolean isVisble;

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            mCompositeSubscription = new CompositeSubscription();
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
//        if (!isPrepared || !isVisble || !isFirst) {
//            return;
//        }
//        Log.d("TAG", getClass().getName() + "->loadData()");
        loadData();
//        isFirst = false;
        //log("loadData");
        //加载数据
    }

    protected abstract void initView();

    protected abstract void loadData();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //log("    2__onCreate");
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        isPrepared = true;
//        onVisible();
//    }

    @Override
    public void onStart() {
        super.onStart();
        //log("   5__onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //log("   6__onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //log("   7__onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //log("   8__onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //log("   9__onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //log("   10__onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //log("   11__onDetach");
    }

    private void log(String methodName) {
        Log.e(TAG, "-------->" + methodName);
    }
}
