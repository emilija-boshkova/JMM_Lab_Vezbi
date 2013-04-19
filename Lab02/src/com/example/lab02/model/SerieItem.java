package com.example.lab02.model;

//import java.util.Date;

public class SerieItem {

	private Long id;

	private String title;
	private String rating;

	public SerieItem() {
	}

	public SerieItem(String title, String rating) {
		super();
		this.title = title;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating() {
		return rating;
	}
	public String getTitle() {
		return title;
	}

}
