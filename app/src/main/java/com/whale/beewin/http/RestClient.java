package com.whale.beewin.http;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.http.api.DownloadService;
import com.whale.beewin.utils.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class RestClient {

    private Retrofit mRetrofit;


    private static final String BASE_URL = Constant.API_HOST;
    private DownloadService mService;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    public RestClient() {
        initRestClint(BASE_URL);
    }

    public void initRestClint(String baseUrl) {
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);
//        httpClient.addInterceptor(new ReceivedCookiesInterceptor(BeewinApp.getContext()));
//        httpClient.addInterceptor(new AddCookiesInterceptor(BeewinApp.getContext()));
        httpClient.cookieJar(new CookiesManager(BeewinApp.getContext()));

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        mService = mRetrofit.create(DownloadService.class);
    }


    public DownloadService getRectService() {
        if (mService != null) {
            return mService;
        }
        return null;
    }
}
