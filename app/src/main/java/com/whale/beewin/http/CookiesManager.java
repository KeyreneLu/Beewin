package com.whale.beewin.http;

import android.content.Context;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.utils.SPUtils;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class CookiesManager implements CookieJar {
    public static String APP_PLATFORM = "app-platform";


    private static Context mContext;

    private static PersistentCookieStore cookieStore;

    public CookiesManager(Context context) {
        mContext = context;
        if (cookieStore == null ) {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies =cookieStore.get(url);
        for (int i = 0; i < cookies.size(); i++) {
            Cookie coo = cookies.get(i);
            if ("BWDATA".equals(coo.name())) {
                String mCookieString = coo.name() + "=" + coo.value()+ ";domain=."+coo.domain();
                SPUtils.put(BeewinApp.getContext(), "Cookie", mCookieString);
//                Log.e("Cookie", mCookieString);
            }
        }
        return cookies;
    }
}
