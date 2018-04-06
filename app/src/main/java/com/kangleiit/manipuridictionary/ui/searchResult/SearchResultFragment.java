package com.kangleiit.manipuridictionary.ui.searchResult;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kangleiit.manipuridictionary.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Win 7 on 3/24/2018.
 */

public class SearchResultFragment extends Fragment {
    @BindView(R.id.tvKeyword)
    TextView tvKeyword;
    Unbinder unbinder;
    @BindView(R.id.ivListenKeyword)
    ImageView ivListenKeyword;
    TextToSpeech textToSpeech;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_search, container, false);
        unbinder = ButterKnife.bind(this, v);
        //getting data from Bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String val = bundle.getString("searchKey");
            tvKeyword.setText(val);
        }

        //Initialize goole text to speech listner.
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.ivListenKeyword)
    public void onViewClicked() {
        String toSpeak = tvKeyword.getText().toString();
        Toast.makeText(getActivity(), toSpeak, Toast.LENGTH_SHORT).show();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}

