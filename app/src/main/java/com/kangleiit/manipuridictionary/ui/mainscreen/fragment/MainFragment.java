package com.kangleiit.manipuridictionary.ui.mainscreen.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.ui.mainscreen.MainAdapter;


/**
 * Created by Win 7 on 3/20/2018.
 */

public class MainFragment extends Fragment {
    RecyclerView rvMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main, container, false);
        rvMain = (RecyclerView) v.findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMain.setAdapter(new MainAdapter(getActivity()));
        return v;
    }
}
