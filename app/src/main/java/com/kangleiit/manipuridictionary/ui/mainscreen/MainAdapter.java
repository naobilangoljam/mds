package com.kangleiit.manipuridictionary.ui.mainscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kangleiit.manipuridictionary.R;


/**
 * Created by Win 7 on 3/20/2018.
 */

public class MainAdapter extends RecyclerView.Adapter {
    Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    class MYViewHolder extends RecyclerView.ViewHolder {

        public MYViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MYViewHolder(itemView1);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MYViewHolder myHolder = (MYViewHolder) holder;

    }


    @Override
    public int getItemCount() {
        return 20;
    }

}

