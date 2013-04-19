package com.example.lab02.utils;

public interface OnContentDownloaded<T> {
	
	public void onContentDownloaded(String content, int httpStatus) throws Exception;
	
	public T getResult();

}
