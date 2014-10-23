package com.example.mealtracker;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class FourthActivity extends Activity {
	public final static String WEIGHT_VALUE = "com.example.mealtracker.WEIGHTMESSAGE";
	SharedPreferences settings;
	private SeekBar heightBar, weightBar;
	private TextView heightText, weightText;
	private int tempHeight;//to store value to pass to the fifth activity with shared preferences
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);		
		
		//create listener for seekbar for height
		heightBar = (SeekBar)findViewById(R.id.heightSeekBar); // make seek bar object        
        heightBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
                heightText = (TextView)findViewById(R.id.heightTextView);
                heightText.setText("Height:: " + progress);
                tempHeight = progress;
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
	
	public void sendMessage(View view)
	{		
		Intent intent = new Intent(this, FifthActivity.class);		
		
		EditText editText = (EditText)findViewById(R.id.editWeightText);
		int tempWeight = Integer.parseInt(editText.getText().toString());		
		
		settings = PreferenceManager.getDefaultSharedPreferences(this);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putInt("height", tempHeight);
	    editor.putInt("weight", tempWeight);
	    editor.commit();
		startActivity(intent);		
	}
}
