package com.example.skn_comp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomList_Downloads extends ArrayAdapter<String> {
    private String[] firsts;
    private String[] seconds;
    // private String[] ages;
    private Activity context;

    public CustomList_Downloads(Activity context, String[] firsts) {
        super(context, R.layout.listview_downloads, firsts);
        this.context = context;
        this.firsts = firsts;
        //this.seconds = seconds;
        // this.ages = ages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_downloads, null, true);
        // TextView textViewFirst = (TextView) listViewItem.findViewById(R.id.textViewFirst);
        TextView tv = (TextView) listViewItem.findViewById(R.id.tv);
        //TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewAge);

        //textViewFirst.setText(firsts[position]);
        tv.setText(firsts[position]);
        //textViewAge.setText(ages[position]);

        return listViewItem;
    }
}