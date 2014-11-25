package com.example.mealreminder;

import com.example.mealreminder.R;

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
import android.widget.TextView;

public class FifthActivity extends Activity {
	SharedPreferences settings;
	private SeekBar heightBar, weightBar;
	private TextView heightText, weightText;
	private int tempHeight;//to store value to pass to the fifth activity with shared preferences
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
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
	
	public void sendMessage(View view)
	{		
		Intent intent = new Intent(this, SixthActivity.class);		
		
		EditText editText = (EditText)findViewById(R.id.editWeightText);
		EditText editText1 = (EditText)findViewById(R.id.editFeetText);
		EditText editText2 = (EditText)findViewById(R.id.editInchText);
		//int tempWeight = Integer.parseInt(editText.getText().toString());	
		//int tempFeet = Integer.parseInt(editText1.getText().toString());
		//int tempInch = Integer.parseInt(editText2.getText().toString());		
		
		if(feetValidator(editText1.getText().toString()) == true)  
        {  
			 
        }  
		 else if(feetValidator(editText1.getText().toString()) == false) 
        {  
       	 //textView.setBackgroundColor(Color.RED);
       	 editText1.setError("Please enter a number between 1 and 9");
             //Toast.makeText(this, "Name field is required", Toast.LENGTH_LONG).show();  
        } 
		 if(inchValidator(editText2.getText().toString()) == true)  
        {  
			 
        }  
		 else if(inchValidator(editText2.getText().toString()) == false) 
        {  
       	 //textView.setBackgroundColor(Color.RED);
       	 editText2.setError("Please enter a number between 1 and 12");
             //Toast.makeText(this, "Name field is required", Toast.LENGTH_LONG).show();  
        }  
		 if(weightValidator(editText.getText().toString()) == true)  
	        {  
				 
	        }  
			 else if(weightValidator(editText.getText().toString()) == false) 
	        {  
	       	 //textView.setBackgroundColor(Color.RED);
	       	 editText.setError("Please enter a number between 5 and 1000");
	             //Toast.makeText(this, "Name field is required", Toast.LENGTH_LONG).show();  
	        } 
		if(feetValidator(editText1.getText().toString()) == true && inchValidator(editText2.getText().toString()) == true && weightValidator(editText.getText().toString()) == true)
		{			
			settings = PreferenceManager.getDefaultSharedPreferences(this);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("heightFeet", Integer.parseInt(editText1.getText().toString()));
		    editor.putInt("heightInch", Integer.parseInt(editText2.getText().toString()));
		    editor.putInt("weight", Integer.parseInt(editText.getText().toString()));
		    editor.commit();
			startActivity(intent);
		}		
		
		//startActivity(intent);		
	}


private boolean feetValidator(String values)  
	{  
     if((values.trim()).equals("") || values.equals(null))  
     {  
          return false;  
     }  
     else if(Integer.parseInt(values) < 1 || Integer.parseInt(values) > 9)
    	 return false;
     else 
     {  
          return true;  
     }  
	}  

private boolean inchValidator(String values)  
	{  
     if((values.trim()).equals("") || values.equals(null))  
     {  
          return false;  
     }  
     else if(Integer.parseInt(values) < 1 || Integer.parseInt(values) > 12)
    	 return false;
     else 
     {  
          return true;  
     }  
	} 

private boolean weightValidator(String values)  
{  
 if((values.trim()).equals("") || values.equals(null))  
 {  
      return false;  
 }  
 else if(Integer.parseInt(values) < 5 || Integer.parseInt(values) > 1000)
	 return false;
 else 
 {  
      return true;  
 }  
} 
}
