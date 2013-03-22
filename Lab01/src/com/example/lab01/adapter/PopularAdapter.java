package com.example.lab01.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lab01.R;
import com.example.lab01.item.PopularItem;


public class PopularAdapter extends ArrayAdapter<PopularItem> {
	
	Context context; 
    int layoutResourceId;    
    PopularItem data[] = null;
    
    public PopularAdapter(Context context, int layoutResourceId,
			PopularItem[] data) {
		super(context, layoutResourceId, data);
		// TODO Auto-generated constructor stub
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
	}
    
    class PopularHolder {
		public RelativeLayout itemLayout;
		public TextView title;
		public TextView rating;
	}
    
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;
	PopularHolder holder=null;
	if(row == null)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        
        holder = new PopularHolder();
        holder.title = (TextView)row.findViewById(R.id.txtTitle);
        holder.rating = (TextView)row.findViewById(R.id.txtRating);
        
        row.setTag(holder);
    }
    else
    {
        holder = (PopularHolder)row.getTag();
    }
    
    PopularItem popular = data[position];
    holder.title.setText(popular.title);
    holder.rating.setText(popular.rating);
    
    return row;
}
	
}
