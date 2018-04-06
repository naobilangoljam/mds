package com.kangleiit.manipuridictionary.ui.mainscreen;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.model.InputResult.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Naobi on 25/03/2018.
 */

public class SearchAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<Result> results;
    InputResultListener listener;
    boolean isHistory = false;

    public SearchAdapter(Dialog dialog, List<Result> words) {
        this.results = words;
        listener = (InputResultListener) dialog;
        inflater = LayoutInflater.from(dialog.getContext());
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_search, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (isHistory) {
            holder.ivHistory.setVisibility(View.VISIBLE);
        } else {
            holder.ivHistory.setVisibility(View.GONE);
        }
        Result result = results.get(i);
        holder.tvWord.setText(result.getWord());
        return view;
    }

    public void setHistory(boolean isHistory) {
        this.isHistory = isHistory;
    }


    static class ViewHolder {
        @BindView(R.id.tvWord)
        TextView tvWord;
        @BindView(R.id.ivHistory)
        ImageView ivHistory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    interface InputResultListener {
        void onWordSelected(String word);
    }


}
