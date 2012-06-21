package com.crud.altas;

import java.util.Date;

import com.crud.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AltaPersona extends Activity{
	AltaPersona context = this;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formapersona);
        Button boton;
        OnClickListener accion = new Accion();
        
        boton = (Button)findViewById(R.id.btnAceptarUsuario);
        boton.setOnClickListener(accion);
        boton = (Button)findViewById(R.id.btnCancelarUsuario);
        boton.setOnClickListener(accion);
        
        
    }
    
    class Accion implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			switch (v.getId()){
			case R.id.btnAceptarUsuario:
				guardarUsuario();
				break;
				
			case R.id.btnCancelarUsuario:
				context.finish();
				break;
			
			}
			
			
		}
		
		public void guardarUsuario(){
			/*String nombre  = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
			String apaterno  = ((EditText)findViewById(R.id.txtApaterno)).getText().toString();
			String amaterno  = ((EditText)findViewById(R.id.txtAmaterno)).getText().toString();
			String email  = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
				*/			
			String sexo = getSexoSeleccionado();			
			Date fechaNacimiento = getFechaNacimiento();
			
			RadioButton sexoMasculino;
			RadioButton sexoFemenino;
			
			
			
			
			
			
			
			
		}

		private Date getFechaNacimiento() {
			DatePicker dateFechaNacimiento = (DatePicker)findViewById(R.id.dateFechaNacimiento);
			
			System.out.println("dia = "+dateFechaNacimiento.getDayOfMonth());
			System.out.println("mes = "+dateFechaNacimiento.getMonth());
			System.out.println("año = "+dateFechaNacimiento.getYear());
			
			return null;
		}

		private String getSexoSeleccionado() {
			RadioButton rbSexoMasculino = (RadioButton)findViewById(R.id.rbSexoMasculino);
			RadioButton rbSexoFemenino = (RadioButton)findViewById(R.id.rbSexoFemenino);
			String salida = null;
			
			if(rbSexoMasculino.isChecked())
				salida = "M";
			if(rbSexoFemenino.isChecked())
				salida = "F";
			
			return salida;
		}
    	
    }
}
