package com.whale.beewin.activity.invest;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.whale.beewin.R;
import com.whale.beewin.activity.LoginActivity;
import com.whale.beewin.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 网页支付
 * Created by Administrator on 2017/3/31 0031.
 */

public class WebPayActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.webview)
    WebView mView;
    String title;//头文字
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpay);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("texthtml");
        initView();
    }

    private void initView() {
        mTitle.setText(title);
        Log.e("intent-------------", getIntent().getStringExtra("texthtml"));
        mView.addJavascriptInterface(this, "document.title");
        WebSettings webSettings = mView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

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
        // settings.setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
        mView.setWebViewClient(new WebViewClient() {
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
                WebChromeClient wvcc = new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                    }
                };
                mView.setWebChromeClient(wvcc);
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        mView.loadData(getIntent().getStringExtra("texthtml"), "text/html", "UTF-8");
    }

    @OnClick(R.id.iv_home_left)
    void Finish() {
        Intent intent = new Intent();
        intent.putExtra("result","ok");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
