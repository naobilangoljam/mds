package com.kangleiit.manipuridictionary.ui.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kangleiit.manipuridictionary.model.login.LoginResult;
import com.kangleiit.manipuridictionary.netwokclient.ApiClient;
import com.kangleiit.manipuridictionary.netwokclient.ApiInterface;
import com.kangleiit.manipuridictionary.ui.base.BasePresenter;
import com.kangleiit.manipuridictionary.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Naobi on 08/04/2018.
 */

public class LoginPresenter extends BasePresenter implements LoginPresInterface {
    LoginView view;
    ApiInterface apiInterface;
    Gson gson;
    GsonBuilder builder;
    public LoginPresenter(LoginView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        builder=new GsonBuilder();
        gson=builder.create();
    }

    @Override
    public void login(String email, String google_id, String imgUrl) {
        Call<LoginResult> call = apiInterface.login(Constants.KEY_SECRET, email,google_id,imgUrl);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code()==200){
                    if(response.body().getSuccess()){
                        view.onLoginSuccess(gson.toJson(response.body().getResult()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }
}
