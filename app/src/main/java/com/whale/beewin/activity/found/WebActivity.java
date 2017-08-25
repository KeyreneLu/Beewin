package com.whale.beewin.activity.found;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.activity.invest.ScatterRealActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.Game;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.btn_share)
    ImageButton mBtnShare;
    private WebView webView;
    private TextView mTitle;
    private ImageView mIvHomeLeft;
    String URL;
    String title;
    Game.HlistBean game = new Game.HlistBean();
    private int flag;
    private boolean isOnPause;
    UMWeb web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        game = getIntent().getParcelableExtra("game");
        flag = getIntent().getIntExtra("flag", 1);
        URL = game.getHurl();
        title = game.getHtitle();
        initView();
    }

    private void initView() {

        UMImage image = new UMImage(activity, game.getHimg());//网络图片
        web = new UMWeb(game.getHurl());
        web.setTitle(game.getHtitle());//标题
        web.setThumb(image);  //缩略图
        web.setDescription("身边的朋友都在玩");//描述

        webView = (WebView) findViewById(R.id.webView);
        mTitle = (TextView) findViewById(R.id.title);
        mIvHomeLeft = (ImageView) findViewById(R.id.iv_home_left);
        mBtnShare.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        final String Json = "var script = document.createElement('script');script.type = "
                + "'text/javascript';script.text = \"function hiddentopbar() {document.getElementById('iswxtop').style.display"
                + "= 'none';}\"; document.getElementsByTagName('head')[0].appendChild(script);hiddentopbar();";
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = (String) SPUtils.get(BeewinApp.getContext(), "Cookie", "");
        Log.e("cookie", cookie);
        cookieManager.setCookie(URL, cookie);
        CookieSyncManager.getInstance().sync();

        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }
        //加载需要显示的网页
        webView.loadUrl(URL);
        //设置Web视图
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Log.e("url",url);
                if (url != null && url.equals("http://m1.judayouyuan.com/")){
                    finish();
                    return true;
                }else if (url != null && url.equals("http://m1.judayouyuan.com/index.php")){
                    finish();
                    return true;
                }else if (url != null && url.contains("http://m1.judayouyuan.com/index.php?g=App&m=Index&a=index")){
                    finish();
                    return true;
                } else if (url != null && url.contains("login")) {
                    startActivity(new Intent(activity, LoginActivity.class));
                    finish();
                    return true;
                }else if (url!=null&&url.equals("http://m1.judayouyuan.com/index.php?g=App&m=Member&a=registernormal")){
                    startActivity(new Intent(activity, LoginActivity.class));
                    finish();
                    return true;
                }else if (url!=null&&url.equals("http://m1.judayouyuan.com/index.php?g=App&m=deal&a=joinus")){
                    startActivity(new Intent(activity, ScatterRealActivity.class));
                    finish();
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, url);
            }

            public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString) {
                super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
                Log.e("title加载成功啦=========", title);
                webView.loadUrl("javascript:" + Json);// + addonjs
                webView.setWebChromeClient(wvcc);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick(R.id.btn_share)
    void share() {
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

    WebChromeClient wvcc = new WebChromeClient() {


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            if (title.equals("蜂赢提示")) {
//                startActivity(new Intent(activity, LoginActivity.class));
//                finish();
//            }else if (title.equals("奔跑吧金币2")||title.equals("沙漠淘金币")||title.equals("蜂蜂小镇")){
//
//            }else {
//                finish();
//                MainActivity.mViewpager.setCurrentItem(3, false);
//                MainActivity.mRadioFound.setChecked(true);
//            }
            webView.setWebChromeClient(wvcc);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
            webView.setWebChromeClient(wvcc);
        }
    };

    @Override
    protected void onDestroy() {
        if(webView!=null){
            webView.destroy();
            webView.setVisibility(View.GONE);
        }
        super.onDestroy();
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
