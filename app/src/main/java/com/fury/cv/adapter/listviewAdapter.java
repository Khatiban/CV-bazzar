package com.fury.cv.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fury.cv.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fury on 5/25/2017.
 */
public class listviewAdapter  extends BaseAdapter {

    public static final String FIRST_COLUMN = "Column 1";
    public static final String SECOND_COLUMN = "Column 2";

    public ArrayList<HashMap<String, String>> list;

    ArrayList<HashMap<String, String>> mAllData = new ArrayList<HashMap<String, String>>();

    HashMap<String, String> map;

    ViewHolder holder;

    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity = activity;
        this.list = list;
        this.mAllData = list;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.rows, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.column1);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.column2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        map = list.get(position);

        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));


        return convertView;
    }

}
