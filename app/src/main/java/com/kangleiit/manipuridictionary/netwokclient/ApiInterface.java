package com.kangleiit.manipuridictionary.netwokclient;

import com.kangleiit.manipuridictionary.model.InputResult.InputResult;
import com.kangleiit.manipuridictionary.model.login.LoginResult;
import com.kangleiit.manipuridictionary.model.wordMeanings.WordMeanings;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Naobi on 21/03/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("input_search")
    Call<InputResult> getSearchInputResult(@Field("md_key") String apiKey,@Field("keyword")String keyword);
    /*@FormUrlEncoded
    @POST("input_search")
    Observable<InputResult> getSearchInputResults(@Field("md_key") String apiKey, @Field("keyword")String keyword);*/
    @FormUrlEncoded
    @POST("find_word")
    Call<WordMeanings> getWordMeanings(@Field("md_key") String apiKey, @Field("keyword")String keyword);

    @FormUrlEncoded
    @POST("loginwithgoogle")
    Call<LoginResult> login(@Field("md_key") String apiKey, @Field("email")String email, @Field("google_id")String google_id,@Field("img_url")String img_url);
}
