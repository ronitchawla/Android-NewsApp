package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GuardianAdapter extends ArrayAdapter<Guardian> {
    public GuardianAdapter(Context context) {
        super(context, -1, new ArrayList<Guardian>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Guardian currentNews = getItem(position);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(currentNews.getmTitle());
        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(currentNews.getmDate());
        TextView section = (TextView) convertView.findViewById(R.id.section);
        section.setText(currentNews.getmSection());
        TextView author = (TextView) convertView.findViewById(R.id.author);
        author.setText(currentNews.getmAuthor());


        return convertView;
    }
}
