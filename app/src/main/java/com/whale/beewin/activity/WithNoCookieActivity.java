package com.whale.beewin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.whale.beewin.R;
import com.whale.beewin.activity.invest.ScatterRealActivity;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;



/**
 * 隐藏头部不携带cookie的网页展示
 * Created by Administrator on 2017/3/27 0027.
 */

public class WithNoCookieActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.webView)
    WebView mWebView;
    String URL;//展示的url
    String title;//头文字
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withnocookie);
        URL = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initView();
    }

    private void initView() {
        final String Json = "var script = document.createElement('script');script.type = "
                + "'text/javascript';script.text = \"function hiddentopbar() {document.getElementById('iswxtop').style.display"
                + "= 'none';}\"; document.getElementsByTagName('head')[0].appendChild(script);hiddentopbar();";//去掉网页的头部
        mTitle.setText(title);
        mWebView.addJavascriptInterface(this, "document.title");
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
        //加载需要显示的网页
        mWebView.loadUrl(URL);
        //设置Web视图
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (url != null && url.equals("http://m1.judayouyuan.com/")){
                    finish();
                    return true;
                }else if (url != null && url.equals("http://m1.judayouyuan.com/index.php?g=App&m=Index&a=index")){
                    finish();
                    return true;
                } else if (url!=null&&url.equals("http://m1.judayouyuan.com/index.php?g=App&m=deal&a=joinus")){
                    startActivity(new Intent(activity, ScatterRealActivity.class));
                    finish();
                    return true;
                } else if (url != null && url.equals("http://m1.judayouyuan.com/index.php")){
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
                mWebView.loadUrl("javascript:" + Json);// + addonjs
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
