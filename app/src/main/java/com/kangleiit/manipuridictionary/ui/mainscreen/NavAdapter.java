package com.kangleiit.manipuridictionary.ui.mainscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kangleiit.manipuridictionary.R;

/**
 * Created by android on 16/3/18.
 */

class NavAdapter extends BaseAdapter {
    String[] items = { "Bookmarks","Search History","Learn Meitei Mayek", "Settings", "Rate It", "Invite Friends", "Help", "About"};
    LayoutInflater inflator;
    int currItemPos;
    Context context;

    public NavAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        this.currItemPos = currItemPos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflator.inflate(R.layout.list_item_nav, null);
            holder = new ViewHolder();
            holder.tvItem = (TextView) convertView.findViewById(R.id.tvItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItem.setText(items[position]);

        return convertView;
    }

    class ViewHolder {
        TextView tvItem;

    }
}
