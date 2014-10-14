package com.example.simplesockettester;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TAG", "onCreate ejecutado");
		setContentView(R.layout.activity_main);
		Button btn_exit = (Button)this.findViewById(R.id.btn_exit);
		Button btn_client = (Button)this.findViewById(R.id.btn_client);
		
		btn_exit.setOnClickListener(
				new OnClickListener(){
					@Override
					public void onClick(View v) {
						try {
							MainActivity.this.finish();
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("TAG", "onDestroy ejecutado");
	}
	
	
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart ejecutado");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d("TAG", "onResume ejecutado");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("TAG", "onPause ejecutado");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("TAG", "onStop ejecutado");
    }
   
}
