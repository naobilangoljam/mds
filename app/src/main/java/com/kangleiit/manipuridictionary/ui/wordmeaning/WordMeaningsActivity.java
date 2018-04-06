package com.kangleiit.manipuridictionary.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.model.wordMeanings.Result;
import com.kangleiit.manipuridictionary.model.wordMeanings.WordMeanings;
import com.kangleiit.manipuridictionary.netwokclient.ApiClient;
import com.kangleiit.manipuridictionary.netwokclient.ApiInterface;
import com.kangleiit.manipuridictionary.ui.wordmeaning.MeaningsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordMeaningsActivity extends AppCompatActivity {
    String key = "chatlo_adda_chei_chani_heinu";
    @BindView(R.id.rvMeanings)
    RecyclerView rvMeanings;
    ApiInterface apiInterface;
    List<Result> results=new ArrayList<>();
    MeaningsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_meanings);
        ButterKnife.bind(this);
        //adapter=new MeaningsAdapter(this,results);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvMeanings.setLayoutManager(mLayoutManager);
        rvMeanings.setItemAnimator(new DefaultItemAnimator());
        rvMeanings.setAdapter(adapter);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Bundle bd=getIntent().getExtras();
        if(bd!=null){
            search(bd.getString("word"));
        }
    }
    void search(String keyword) {
        Call<WordMeanings> call = apiInterface.getWordMeanings(key, keyword);
        call.enqueue(new Callback<WordMeanings>() {
            @Override
            public void onResponse(Call<WordMeanings> call, Response<WordMeanings> response) {
                WordMeanings wordMeanings = response.body();
                if (wordMeanings.getSuccess()) {
                    results.addAll(wordMeanings.getResult());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<WordMeanings> call, Throwable t) {

            }

        });
    }
}
