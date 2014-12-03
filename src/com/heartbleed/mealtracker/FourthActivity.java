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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class FourthActivity extends Activity {
	SharedPreferences settings;
	private SeekBar ageBar;
	private TextView ageText;
	int tempAge;//to pass the age value from seek bar to fifth activity for calorie calculations
	String str;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		
		ageBar = (SeekBar)findViewById(R.id.ageSeekBar); // make seek bar object
        //PRICEbar.setOnSeekBarChangeListener(this); 
        ageBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // TODO Auto-generated method stub
                ageText = (TextView)findViewById(R.id.ageTextView);
                ageText.setText("Age:: " + progress);
                tempAge = progress;
            }
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fourth, menu);
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
		settings = PreferenceManager.getDefaultSharedPreferences(this);
	    SharedPreferences.Editor editor = settings.edit();
	    
		boolean checked = ((RadioButton) view).isChecked();				
		switch(view.getId()){
			case R.id.radio0:
				if(checked)
					str = "male";
				editor.putString("gender", "male");
				break;
			case R.id.radio1:
				if(checked)
					str = "female";
				editor.putString("gender", "female");
				break;
		}	
		editor.commit();		
	}
	
	public void sendMessage(View view)
    {
    Intent intent = new Intent(this, FifthActivity.class);      
    settings = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = settings.edit();
    editor.putInt("age", tempAge);
    editor.commit();
    startActivity(intent);
    }
}
