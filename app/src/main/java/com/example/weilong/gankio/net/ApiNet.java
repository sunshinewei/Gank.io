package com.example.weilong.gankio.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by weilong on 2016/12/22.
 */

public class ApiNet {
    public static ApiService initApiService(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES);
        Retrofit retrofit=new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
