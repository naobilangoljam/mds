package com.kangleiit.manipuridictionary.ui.searchscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.model.InputResult.InputResult;
import com.kangleiit.manipuridictionary.netwokclient.ApiClient;
import com.kangleiit.manipuridictionary.netwokclient.ApiInterface;
import com.kangleiit.manipuridictionary.ui.WordMeaningsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.editText)
    SearchView searchView;
    ArrayList<String> result = new ArrayList<>();
    ApiInterface apiInterface;
    String key = "chatlo_adda_chei_chani_heinu";
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);
        listView.setAdapter(adapter);
        searchView.requestFocus();
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //Here we request a search
                        search(newText);
                        return false;
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it=new Intent(ResultActivity.this, WordMeaningsActivity.class);
                it.putExtra("word",result.get(i));
                startActivity(it);
            }
        });



    }
    /*public void instantSearch(String searchTerm) {
        if (publishSubject == null) {
           // Observable<InputResult> observable= apiInterface.getSearchInputResults(key,searchTerm);
            publishSubject = PublishSubject.create();
            publishSubject
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .switchMap(searchValue -> apiInterface.getSearchInputResults(key,searchTerm)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()))
                    .subscribeWith(new DisposableObserver<InputResult>() {
                        @Override
                        public void onNext(InputResult response) {
                            //Update View here
                            InputResult inputResult = response;
                            if (inputResult.getSuccess()) {
                                result.clear();
                                for (int i = 0; i < inputResult.getResult().size(); i++) {
                                    result.add(inputResult.getResult().get(i).getWord());
                                }
                                adapter.notifyDataSetChanged();
                            }

                        }
                        @Override
                        public void onError(Throwable e) {
                            //On error
                        }
                        @Override
                        public void onComplete() {
                            //On complete
                        }
                    });
        }
        publishSubject.onNext(searchTerm);
    }*/

    void search(String keyword) {
        Call<InputResult> call = apiInterface.getSearchInputResult(key, keyword);
        call.enqueue(new Callback<InputResult>() {
            @Override
            public void onResponse(Call<InputResult> call, Response<InputResult> response) {
                InputResult inputResult = response.body();
                if (inputResult.getSuccess()) {
                    result.clear();
                    for (int i = 0; i < inputResult.getResult().size(); i++) {
                        result.add(inputResult.getResult().get(i).getWord());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<InputResult> call, Throwable t) {

            }
        });
    }
}
