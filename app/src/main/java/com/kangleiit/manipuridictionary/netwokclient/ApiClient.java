package com.kangleiit.manipuridictionary.netwokclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Naobi on 21/03/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://api.manipuridictionary.com/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        }
        return retrofit;
    }
}
