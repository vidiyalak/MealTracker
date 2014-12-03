package com.heartbleed.mealtracker;

import com.heartbleed.mealtracker.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class AlarmListActivity extends ListActivity {

	private AlarmListAdapter mAdapter;
	private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		
		setContentView(R.layout.activity_alarm_list);

		mAdapter = new AlarmListAdapter(this, dbHelper.getAlarms());
		
		setListAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.alarm_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.action_show_calories: {
				//do show calories here
				//get the number of items in the list view that represents number of meals
				int count = getListView().getChildCount();
				if(count == 0)//if list view is empty
					count = -1;
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
				int totalCalories = settings.getInt("totalCalories", 10);//default value of 10 in case no value available
				//create an alert dialog to display the number of calories per meal
				AlertDialog ad = new AlertDialog.Builder(this).create();  
				ad.setCancelable(false); // This blocks the 'BACK' button  
				if(count != -1)
				{
					ad.setTitle("Calories Per Meal");
					ad.setMessage("You require " + totalCalories/count + " calories per meal\n" + Math.round((totalCalories/count)*.55/4) + " grams of carbs\n" + Math.round((totalCalories/count)*.3/9) + " grams of fat\n" + Math.round((totalCalories/count)*.15/4) + " grams of protein");
				}
				else if(count == -1)
				{
					ad.setTitle("Calories Per Day");
					ad.setMessage("You require " + totalCalories + " calories per day\n" + Math.round((totalCalories)*.55/4) + " grams of carbs\n" + Math.round((totalCalories)*.3/9) + " grams of fat\n" + Math.round((totalCalories)*.15/4) + " grams of protein");
				}
				//ad.setTitle("Calories Per Meal");
				ad.setButton("OK", new DialogInterface.OnClickListener() {  
				    @Override  
				    public void onClick(DialogInterface dialog, int which) {  
				        dialog.dismiss();                      
				    }  
				});  
				ad.show(); 
				break;
			}
			
			case R.id.action_add_new_alarm: {
				startAlarmDetailsActivity(-1);
				break;
			}
			case R.id.action_go_gome: {
				//////////
				Intent intent = new Intent(this, FirstActivity.class);
				startActivity(intent);
			}
			
		}

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
	        mAdapter.setAlarms(dbHelper.getAlarms());
	        mAdapter.notifyDataSetChanged();
	    }
	}
	
	public void setAlarmEnabled(long id, boolean isEnabled) {
		AlarmManagerHelper.cancelAlarms(this);
		
		AlarmModel model = dbHelper.getAlarm(id);
		model.isEnabled = isEnabled;
		dbHelper.updateAlarm(model);
		
		AlarmManagerHelper.setAlarms(this);
	}

	public void startAlarmDetailsActivity(long id) {
		Intent intent = new Intent(this, AlarmDetailsActivity.class);
		intent.putExtra("id", id);
		startActivityForResult(intent, 0);
	}
	
	public void deleteAlarm(long id) {
		final long alarmId = id;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Please confirm")
		.setTitle("Delete set?")
		.setCancelable(true)
		.setNegativeButton("Cancel", null)
		.setPositiveButton("Ok", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//Cancel Alarms
				AlarmManagerHelper.cancelAlarms(mContext);
				//Delete alarm from DB by id
				dbHelper.deleteAlarm(alarmId);
				//Refresh the list of the alarms in the adaptor
				mAdapter.setAlarms(dbHelper.getAlarms());
				//Notify the adapter the data has changed
				mAdapter.notifyDataSetChanged();
				//Set the alarms
				AlarmManagerHelper.setAlarms(mContext);
			}
		}).show();
	}
}
