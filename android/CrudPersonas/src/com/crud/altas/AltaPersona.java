package com.crud.altas;

import com.crud.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AltaPersona extends Activity{
	AltaPersona context = this;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formapersona);
        Button boton;
        OnClickListener a = new Accion();
        
        boton = (Button)findViewById(R.id.btnAceptarUsuario);
        boton.setOnClickListener(a);
        boton = (Button)findViewById(R.id.btnCancelarUsuario);
        boton.setOnClickListener(a);
        
        
    }
    
    
    
    
    class Accion implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			switch (v.getId()){
			case R.id.btnAceptarUsuario:
				
				break;
				
			case R.id.btnCancelarUsuario:
				context.finish();
				break;
			
			}
			
			
		}
    	
    }
}
