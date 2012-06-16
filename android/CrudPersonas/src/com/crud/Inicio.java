package com.crud;

import com.crud.altas.AltaPersona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Inicio extends Activity {
    /** Called when the activity is first created. */
	
	Inicio context=this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnAgregarPersona = (Button)findViewById(R.id.btnAgregarPersona);
        
        btnAgregarPersona.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,AltaPersona.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
        	
        });
        
    }
}