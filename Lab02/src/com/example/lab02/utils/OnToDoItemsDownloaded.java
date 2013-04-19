package com.example.lab02.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.lab02.model.SerieItem;

import org.json.JSONArray;
import org.json.JSONObject;

public class OnToDoItemsDownloaded implements
		OnContentDownloaded<List<SerieItem>> {

	private List<SerieItem> items = new ArrayList<SerieItem>();

	@Override
	public void onContentDownloaded(String content, int httpStatus)
			throws Exception {
		JSONArray jsonItems = new JSONArray(content);

		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			SerieItem item = new SerieItem();
			item.setTitle(jObj.getString("title"));
			item.setRating(jObj.getString("imdb"));
			//item.setDone(false);
			items.add(item);
		}

	}

	@Override
	public List<SerieItem> getResult() {
		return items;
	}

}
