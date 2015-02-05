package com.example.danco.bonus1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danco.bonus1.R;

import java.util.List;

/**
 * Custom adapter to manage separator in grid view
 */
public class GridAdapter extends ArrayAdapter<String> {

    // Per docs, first view type must start at 0
    private static final int DEFAULT_VIEW_TYPE = 0;
    private int numColumns = 2;

    public GridAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        numColumns = 2;
    }

    @Override
    public int getViewTypeCount() {
        // Two types of views if only 1 column
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        return DEFAULT_VIEW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.grid_item, parent, false);
            // Using a view holder as adapter views are recycled. Only
            // need to create a view if the convert view is null
            view.setTag(new ViewHolder(view));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(getItem(position));

        return view;
    }

    /* package */ static class ViewHolder {
        final ImageView image;
        final TextView title;

        ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
