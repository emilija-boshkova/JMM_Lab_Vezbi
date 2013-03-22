package com.example.lab01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showAbout(View view) {
		Intent intent=new Intent(this,DisplayAbout.class);
		startActivity(intent);
	}
	
	public void showPopular(View view) {
		Intent intent=new Intent(this,DisplayPopular.class);
		startActivity(intent);
	}

	public void showAll(View view) {
		Intent intent=new Intent(this,DisplayAll.class);
		startActivity(intent);
	}
	

}
