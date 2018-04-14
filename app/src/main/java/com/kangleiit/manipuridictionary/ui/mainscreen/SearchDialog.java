package com.kangleiit.manipuridictionary.ui.mainscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.model.InputResult.InputResult;
import com.kangleiit.manipuridictionary.model.InputResult.Result;
import com.kangleiit.manipuridictionary.model.wordMeanings.WordMeanings;
import com.kangleiit.manipuridictionary.netwokclient.ApiClient;
import com.kangleiit.manipuridictionary.netwokclient.ApiInterface;
import com.kangleiit.manipuridictionary.ui.wordmeaning.MeaningsAdapter;
import com.kangleiit.manipuridictionary.utils.Constants;
import com.kangleiit.manipuridictionary.utils.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kangleiit.manipuridictionary.utils.Utilities.getStringPrefDef;

/**
 * Created by Naobi on 25/03/2018.
 */

public class SearchDialog extends Dialog implements SearchAdapter.InputResultListener, MeaningsAdapter.MeaningAdapterInterface {
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.lvSearch)
    ListView lvSearch;
    @BindView(R.id.toolbarSearch)
    Toolbar toolbarSearch;
    ApiInterface apiInterface;
    SearchAdapter adapter;
    List<Result> results = new ArrayList<>();
    String key = "chatlo_adda_chei_chani_heinu";
    Context context;
    @BindView(R.id.rvMeanings)
    RecyclerView rvMeanings;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    MeaningsAdapter adapterMeanings;
    List<com.kangleiit.manipuridictionary.model.wordMeanings.Result> meanings = new ArrayList<>();
    TextToSpeech textToSpeech;
    List<Result> searchHistories = new ArrayList<>();
    Gson gson;
    GsonBuilder builder;
    boolean isInputClear=false;

    public SearchDialog(@NonNull Context context) {
        super(context);
    }

    public SearchDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_search);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        adapter = new SearchAdapter(this, results);
        adapterMeanings = new MeaningsAdapter(this, meanings);
        lvSearch.setAdapter(adapter);
        getHistory();
        loadHistory();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        rvMeanings.setLayoutManager(mLayoutManager);
        rvMeanings.setItemAnimator(new DefaultItemAnimator());
        rvMeanings.setAdapter(adapterMeanings);


        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        isInputClear=false;
                        onWordSelected(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //Here we request a search
                        Log.e("input", newText);
                        rvMeanings.setVisibility(View.GONE);
                        if (newText.trim().length() > 0) {
                            isInputClear=false;
                            lvSearch.setVisibility(View.VISIBLE);
                            searchOnline(newText);
                        } else {
                            Log.e("query", "empty");
                            isInputClear=true;
                            loadHistory();

                        }
                        return false;
                    }


                });
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchView.setQuery(results.get(i).getWord(), true);

            }
        });


    }

    void loadHistory() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lvSearch.setVisibility(View.VISIBLE);
                results.clear();
                adapter.setHistory(true);
                results.addAll(searchHistories);
                adapter.notifyDataSetChanged();
            }
        }, 200);
    }

    @Override
    public void show() {
        super.show();
        searchView.setQuery("", false);
        searchView.requestFocus();
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        if (textToSpeech == null)
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.UK);
                    }
                }
            });

    }
    void getHistory(){
        String sh = Utilities.getStringPrefDef(context, Constants.SEARCH_HISTORY, "");
        if (!sh.isEmpty()) {
            Type listType = new TypeToken<List<Result>>() {
            }.getType();
            searchHistories = gson.fromJson(sh, listType);
            loadHistory();
        }
    }

    public void search(String keyword) {
        show();
        searchView.setQuery(keyword, true);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    void searchOnline(String keyword) {
        Call<InputResult> call = apiInterface.getSearchInputResult(key, keyword);
        call.enqueue(new Callback<InputResult>() {
            @Override
            public void onResponse(Call<InputResult> call, Response<InputResult> response) {
                InputResult inputResult = response.body();
                results.clear();
                adapter.setHistory(false);
                Log.e("res", response.body().getResult().toString());
                if(isInputClear)return;
                if (inputResult.getSuccess()) {
                    results.addAll(inputResult.getResult());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<InputResult> call, Throwable t) {

            }
        });
    }

    void getMeaning(final String keyword) {
        progressBar.setVisibility(View.VISIBLE);
        Call<WordMeanings> call = apiInterface.getWordMeanings(key, keyword);
        call.enqueue(new Callback<WordMeanings>() {
            @Override
            public void onResponse(Call<WordMeanings> call, Response<WordMeanings> response) {
                progressBar.setVisibility(View.GONE);
                rvMeanings.setVisibility(View.VISIBLE);
                WordMeanings wordMeanings = response.body();
                meanings.clear();
                adapterMeanings.notifyDataSetChanged();
                if (wordMeanings.getSuccess()) {
                    if (wordMeanings.getResult().size() > 0) {
                        meanings.add(new com.kangleiit.manipuridictionary.model.wordMeanings.Result());
                        List<com.kangleiit.manipuridictionary.model.wordMeanings.Result> res = wordMeanings.getResult();
                        for (
                                com.kangleiit.manipuridictionary.model.wordMeanings.Result r : res) {
                            meanings.add(r);
                        }
                        //meanings.addAll(wordMeanings.getResult());
                        adapterMeanings.notifyDataSetChanged();
                        Result resHistory = new Result();
                        resHistory.setWord(keyword);
                        if (!searchHistories.contains(resHistory)) {
                            searchHistories.add(resHistory);
                            Utilities.updateStringPref(context, Constants.SEARCH_HISTORY, gson.toJson(searchHistories));
                        }
                    } else {

                    }

                }
            }

            @Override
            public void onFailure(Call<WordMeanings> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    @Override
    public void onWordSelected(String word) {
        lvSearch.setVisibility(View.GONE);
        getMeaning(word);
    }


    @Override
    public void speak(String word) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void bookmark(String word, String wordId) {
        String bookmarks = getStringPrefDef(context, Constants.BOOKMARKS, "{}");
        try {
            JSONObject object = new JSONObject(bookmarks);
            if (object.has(word)) {
                object.remove(word);
            } else {
                object.put(word, wordId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
