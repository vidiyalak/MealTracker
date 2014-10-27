package com.example.mealtracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class LoseWeight extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lose_weight);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lose_weight, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onRadioButtonClicked(View view){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    SharedPreferences.Editor editor = settings.edit();
	    
		boolean checked = ((RadioButton) view).isChecked();		
		//String str = null;
		switch(view.getId()){
			case R.id.radio0:
				if(checked)					
				editor.putInt("loseWeightAmount", 1);//will divide by 2 later. Did this since not allowed to put doubles
				break;
			case R.id.radio1:
				if(checked)					
					editor.putInt("loseWeightAmount", 2);
				break;
			case R.id.radio2:
				if(checked)					
					editor.putInt("loseWeightAmount", 3);
				break;
			case R.id.radio3:
				if(checked)					
					editor.putInt("loseWeightAmount", 4);
				break;
		}			
		editor.commit();
		Intent intent = new Intent(this, SecondActivity.class);		
		startActivity(intent);
	}
	
	public void sendMessage(View view)
    {
    Intent intent = new Intent(this, SecondActivity.class);    
    startActivity(intent);
    }
}
