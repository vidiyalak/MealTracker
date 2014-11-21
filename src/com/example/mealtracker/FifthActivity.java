package com.example.mealtracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class FifthActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		//to get values stored from previous activities
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		String getGender = settings.getString("gender", "You did not select anything");
		String getGoal = settings.getString("goal", "You did not select anything");
		int getAge = settings.getInt("age", 0);
		//added feet and inch to reflect changes in the scroll bar earlier to 2 spaces to collect user height info
		int getHeightFeet = settings.getInt("heightFeet", 0);
		int getHeightInch = settings.getInt("heightInch", 0);
		int getHeight = (getHeightFeet * 12) + getHeightInch;
		int getWeight = settings.getInt("weight", 0);
		int getLoseWeightAmount = settings.getInt("loseWeightAmount", -1);
		int getGainWeightAmount = settings.getInt("gainWeightAmount", -1);
		String getActivityLevel = settings.getString("activitylevel", "Nothing provided");
		// Get the message from the intent
	    //Intent intent = getIntent();
	    //String message = intent.getStringExtra(FourthActivity.WEIGHT_VALUE);
	    //String message1 = intent.getStringExtra(ThirdActivity.GENDER_VALUE);
	    //send the Weight Value from the fourth
		
		double caloriesRequired = calculateCalories(getAge, getWeight, getHeight, getGender, getActivityLevel, getLoseWeightAmount, getGainWeightAmount, getGoal);
		
		/*Random test cases. I just left it here so I can debug stuff later if needed
	    TextView textView = (TextView) findViewById(R.id.textView1);
	    textView.setTextSize(10);
	    textView.setText(getGender);
	    
	    TextView textView1 = (TextView) findViewById(R.id.textView2);
	    textView1.setTextSize(10);
	    textView1.setText(String.valueOf(getAge) + "years");
	    
	    TextView textView2 = (TextView) findViewById(R.id.textView3);
	    textView2.setTextSize(10);
	    textView2.setText(String.valueOf(getHeight) + "inches");
	    
	    TextView textView3 = (TextView) findViewById(R.id.textView4);
	    textView3.setTextSize(10);
	    textView3.setText("You are: " + String.valueOf(getWeight) + "pounds");
	    
	    TextView textView4 = (TextView) findViewById(R.id.textView5);
	    textView4.setTextSize(10);
	    textView4.setText("Your activity level is: " + getActivityLevel);
	    */
	    TextView textView5 = (TextView) findViewById(R.id.textView1);
	    //textView5.setTextSize(10);
	    textView5.setText("You require: " + Math.round(caloriesRequired) + " calories.");	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifth, menu);
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
	
	//to calculate the amount of calories required
		private double calculateCalories(int age, int weight, int height, String gender, String activityLevel, int loseWeightAmount, int gainWeightAmount, String goal)
		{
			double bmr = 10;//store the basal metabolic rate
			double rate = 10;//to multiply bmr by to get calorie value
			double loseAmount = 0;
			double gainAmount = 0;
			if(loseWeightAmount == -1)
			{
				loseAmount = 0;//since no lose weight amount is given , ie, user does not want to lose weight
			}
			else
			{
				loseAmount = ((loseWeightAmount / 2.0) * 3500) / 7;
			}
			
			if(gainWeightAmount == -1)
			{
				gainAmount = 0;//since no gain weight amount is given , ie, user does not want to gain weight
			}
			else
			{
				gainAmount = ((gainWeightAmount / 2.0) * 3500) / 7;
			}
			
			if(goal.equals("lose"))
			{
				gainAmount = 0;
			}
			else if(goal.equals("gain"))
			{
				loseAmount = 0;
			}
			else if(goal.equals("maintain"))
			{
				loseAmount = 0;
				gainAmount = 0;
			}
			
			if(gender.equals("male"))
			{
				bmr = (12.7 * height) + (6.23 * weight) - (6.8 * age) + 66;
			}
			else if(gender.equals("female"))
			{
				bmr = (4.7 * height) + (4.35 * weight) - (4.7 * age) + 655;
			}
			if(activityLevel.equals("sedentary"))
				rate = 1.2;
			else if(activityLevel.equals("light"))
				rate = 1.375;
			else if(activityLevel.equals("moderate"))
				rate = 1.55;
			else if(activityLevel.equals("active"))
				rate = 1.725;
			
			return bmr * rate - loseAmount + gainAmount;
			//return 0;
		}
}
