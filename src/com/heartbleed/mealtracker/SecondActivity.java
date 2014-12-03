package com.heartbleed.mealtracker;

import com.heartbleed.mealtracker.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
		String str = null;
		switch(view.getId()){
			case R.id.radio0:
				if(checked)	
					str = "lose";
				//editor.putString("goal", "lose");
				break;
			case R.id.radio1:
				if(checked)	
					str = "maintain";
				//editor.putString("goal", "maintain");
				break;
			case R.id.radio2:
				if(checked)	
					str = "gain";
				//editor.putString("goal", "gain");
				break;
		}	
		editor.putString("goal", str);
		editor.commit();
		Intent intent;
		if(str.equals("lose"))
		{
			intent = new Intent(this, LoseWeight.class);
		}
		else if(str.equals("gain"))
		{
			intent = new Intent(this, GainWeight.class);
		}
		else if(str.equals("maintain"))
		{
			intent = new Intent(this, ThirdActivity.class);
		}
		else
			intent = new Intent(this, ThirdActivity.class);
		
		startActivity(intent);
		//Intent intent = new Intent(this, FourthActivity.class);
		//intent.putExtra(GENDER_VALUE, str);
	}	
}
