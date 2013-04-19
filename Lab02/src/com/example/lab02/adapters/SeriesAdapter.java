package com.example.lab02.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.lab02.R;
import com.example.lab02.model.SerieItem;
import android.content.Context;
//import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SeriesAdapter extends BaseAdapter /*implements
		OnItemClickListener*/ {

	private List<SerieItem> items;
	private Context ctx;
	private LayoutInflater inflater;

	public SeriesAdapter(Context ctx) {
		items = new ArrayList<SerieItem>();
		this.ctx = ctx;
		inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public SeriesAdapter(List<SerieItem> items, Context ctx) {
		this.items = items;
		this.ctx = ctx;
		inflater = (LayoutInflater) this.ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class TodoHoler {
		public RelativeLayout itemLayout;
		public TextView title;
		public TextView rating;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SerieItem item = items.get(position);
		TodoHoler holder = null;
		if (convertView == null) {
			holder = new TodoHoler();
			holder.itemLayout = (RelativeLayout) inflater.inflate(
					R.layout.item_serie, null);

			holder.title = (TextView) holder.itemLayout
					.findViewById(R.id.todoName);
			holder.rating = (TextView) holder.itemLayout
					.findViewById(R.id.todoDate);
			convertView = holder.itemLayout;
			convertView.setTag(holder);

		}

		holder = (TodoHoler) convertView.getTag();

		holder.title.setText(item.getTitle());
		holder.rating.setText(item.getRating().toString());

	/*	if (item.isDone()) {
			holder.itemLayout.setBackgroundColor(Color.GREEN);
		} else {
			holder.itemLayout.setBackgroundColor(Color.RED);
		}*/
		return convertView;
	}

	public void add(SerieItem item) {
		items.add(item);
		notifyDataSetChanged();
	}
	
	public void addAll(List<SerieItem> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	
	public void clear(){
		items.clear();
		notifyDataSetInvalidated();
	}
	

	/*@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SerieItem item = items.get(position);
		item.setDone(!item.isDone());
		notifyDataSetChanged();
	}*/

}
