package com.whale.beewin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;

import static com.whale.beewin.R.id.webView;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class WithCookieActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(webView)
    WebView mWebView;
    String URL;//展示的url
    String title;//头文字

    private String mCookieString;
    private boolean isOnPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withcookie);
        URL = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initView();
    }

    private void initView() {
//        //去掉网页的头部
//        final String Json = "var script = document.createElement('script');script.type = "
//                + "'text/javascript';script.text = \"function hiddentopbar() {document.getElementById('iswxtop').style.display"
//                + "= 'none';}\"; document.getElementsByTagName('head')[0].appendChild(script);hiddentopbar();";
        mTitle.setText(title);
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        mCookieString = (String) SPUtils.get(BeewinApp.getContext(), "Cookie", "");
//        Log.e("withcookieactivity", mCookieString);
        cookieManager.setCookie(URL, mCookieString);
        CookieSyncManager.getInstance().sync();
//        mWebView.addJavascriptInterface(this, "document.title");
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
//        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
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
        mWebView.loadUrl(URL);
        //设置Web视图
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Log.e("url",url);
                if (url != null && url.equals("http://m1.judayouyuan.com/")){
                    finish();
                    return true;
                }else if (url != null && url.equals("http://m1.judayouyuan.com/index.php")){
                    finish();
                    return true;
                }else if (url != null && url.contains("login")) {
                    startActivity(new Intent(activity, LoginActivity.class));
                    finish();
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, url);
            }

            public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString) {
                super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
//                mWebView.loadUrl("javascript:" + Json);
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        finish();
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        if(mWebView!=null){
            mWebView.destroy();
            mWebView.setVisibility(View.GONE);
        }
        super.onDestroy();
    }
}
