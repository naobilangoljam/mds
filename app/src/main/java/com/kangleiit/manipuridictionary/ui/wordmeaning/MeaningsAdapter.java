package com.kangleiit.manipuridictionary.ui.wordmeaning;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.model.wordMeanings.Result;
import com.kangleiit.manipuridictionary.ui.addEditWord.AddEditActivity;

import java.util.List;

/**
 * Created by Naobi on 21/03/2018.
 */

public class MeaningsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Result> results;
    Typeface custom_font;
    MeaningAdapterInterface meaningAdapterInterface;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public MeaningsAdapter(Dialog dialog, List<Result> results) {
        meaningAdapterInterface = (MeaningAdapterInterface) dialog;
        this.context = dialog.getContext();
        this.results = results;
        custom_font = Typeface.createFromAsset(this.context.getAssets(), "fonts/mayek.TTF");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.meaning_item, parent, false);
            MyViewHolder holder = new MyViewHolder(itemView);
            holder.tvMeaningMan.setTypeface(custom_font);
            return holder;
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.meaning_header, parent, false);
            HeaderViewHolder holder = new HeaderViewHolder(itemView);
            return holder;
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder mHolder = (MyViewHolder) holder;
            final Result result = results.get(position);
            if (result.getMeaningMm().trim().length() < 1 || result.getMeaningEngMan().trim().length() < 1) {
                mHolder.tvUpdate.setText("Add Meaning");
                if (result.getMeaningMm().isEmpty())
                    mHolder.tvMeaningMan.setVisibility(View.GONE);
                if (result.getMeaningEngMan().isEmpty())
                    mHolder.tvMeaningManEng.setVisibility(View.GONE);
            } else {
                mHolder.tvUpdate.setText("Edit");
            }
            mHolder.tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AddEditActivity.class));
                }
            });
            mHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meaningAdapterInterface.speak(result.getWord());
                }
            });
            mHolder.ivBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meaningAdapterInterface.bookmark(result.getWord(), result.getId());
                }
            });
            mHolder.tvWord.setText(stripNull(result.getWord()) + " (" + stripNull(result.getWordtype()) + ")");
            mHolder.tvMeaningMan.setText(stripNull(result.getMeaningMm()) + "");
            mHolder.tvMeaningManEng.setText(stripNull(result.getMeaningEngMan()) + "");
            mHolder.tvEnglishDef.setText(stripNull(result.getDefinition().trim()) + "");
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder mHolder= (HeaderViewHolder) holder;
            mHolder.ivBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    String stripNull(String word) {
        return word == null ? "" : word;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public interface MeaningAdapterInterface {
        void speak(String word);

        void bookmark(String word, String wordId);
    }

}

class HeaderViewHolder extends RecyclerView.ViewHolder {
    ImageView ivBookmark;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ivBookmark = (ImageView) itemView.findViewById(R.id.ivBookmark);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvMeaningManEng, tvMeaningMan, tvEnglishDef, tvWord, tvUpdate;
    ImageView ivPlay, ivBookmark;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvMeaningManEng = (TextView) itemView.findViewById(R.id.tvMeaningManEng);
        tvMeaningMan = (TextView) itemView.findViewById(R.id.tvMeaningMan);
        tvEnglishDef = (TextView) itemView.findViewById(R.id.tvEnglishDef);
        tvWord = (TextView) itemView.findViewById(R.id.tvWord);
        tvUpdate = (TextView) itemView.findViewById(R.id.tvUpdate);
        ivPlay = (ImageView) itemView.findViewById(R.id.ivPlay);
        ivBookmark = (ImageView) itemView.findViewById(R.id.ivBookmark);

    }

}






