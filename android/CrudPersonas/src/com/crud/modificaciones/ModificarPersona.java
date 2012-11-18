package com.crud.modificaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.crud.R;
import com.crud.bd.PersonasSQLiteHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ModificarPersona extends Activity {
	ModificarPersona miActivity = this;
	int idDeLaPersona;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.formapersona);
	    Bundle extras = getIntent().getExtras();
	    idDeLaPersona = extras.getInt("idDeLaPersona");
	    llenarFormaConLosDatosDeLaPersona();
		
	    OnClickListener accion = new Accion();
	    Button boton;
		boton = (Button)findViewById(R.id.btnAceptarUsuario);
		boton.setOnClickListener(accion);
		boton = (Button)findViewById(R.id.btnCancelarUsuario);
		boton.setOnClickListener(accion);
	}

	private void llenarFormaConLosDatosDeLaPersona() {
		View v;
		PersonasSQLiteHelper helper = new PersonasSQLiteHelper(getApplicationContext());
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c;
		String query = "select nombre,apaterno,amaterno,email,sexo,date(fechaNacimiento)  from persona where id = "+idDeLaPersona;
		c = db.rawQuery(query,null);
		c.moveToFirst();
		
		v = findViewById(R.id.txtNombre);
		((EditText)v).setText(c.getString(0));
		
		v = findViewById(R.id.txtApaterno);
		((EditText)v).setText(c.getString(1));
		
		v = findViewById(R.id.txtAmaterno);
		((EditText)v).setText(c.getString(2));
		
		v = findViewById(R.id.txtEmail);
		((EditText)v).setText(c.getString(3));
		
		
		String sexo = c.getString(4);

		if(sexo.equals("M"))
			((RadioButton)findViewById(R.id.rbSexoMasculino)).setChecked(true);
		else
			((RadioButton)findViewById(R.id.rbSexoFemenino)).setChecked(true);
		
		String[] fecha = c.getString(5).split("-");
		
		DatePicker d = (DatePicker)findViewById(R.id.dateFechaNacimiento);
		d.init(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1])-1,Integer.parseInt(fecha[2]), null);
		c.close();
	}
	
	
	
	
	
	class Accion implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			switch (v.getId()){
			case R.id.btnAceptarUsuario:
				guardarUsuario();
				break;
				
			case R.id.btnCancelarUsuario:
				miActivity.finish();
				break;
			
			}
		}
		
		public void guardarUsuario(){
			String nombre  = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
			String apaterno  = ((EditText)findViewById(R.id.txtApaterno)).getText().toString();
			String amaterno  = ((EditText)findViewById(R.id.txtAmaterno)).getText().toString();
			String email  = ((EditText)findViewById(R.id.txtEmail)).getText().toString();			
			String sexo = getSexoSeleccionado();			
			Date fechaNacimiento = getFechaNacimiento();
			
			StringBuilder errores = new StringBuilder("");
			
			if(nombre.trim().equals("")){
				errores.append("\n-El nombre no debe ir vacio");
			}
			if(apaterno.trim().equals("")){
				errores.append("\n-El apellido paterno no debe ir vacio");
			}
			if(amaterno.trim().equals("")){
				errores.append("\n-El apellido materno no debe ir vacio");
			}
			if(email.trim().equals("")){
				errores.append("\n-El email no debe ir vacio");
			}
			if(sexo==null){
				errores.append("\n-Se debe seleccionar el sexo");
			}
			
			
			if(!errores.toString().equals("")){
				AlertDialog alertDialog = new AlertDialog.Builder(miActivity).create();
				alertDialog.setTitle("Faltaron datos por ingresar");
				alertDialog.setMessage("Checar lo siguiente:"+errores);
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int which) {
				      
				   }
				});
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.show();
			}
			else{
				ActualizarPersona(nombre,apaterno,amaterno,email,fechaNacimiento,sexo);
				Toast toast1 = Toast.makeText(getApplicationContext(),"Se ha modificado una persona", Toast.LENGTH_LONG);
				toast1.show();
				miActivity.finish();
			}
			
		}

		

		private Date getFechaNacimiento() {
			DatePicker dateFechaNacimiento = (DatePicker)findViewById(R.id.dateFechaNacimiento);
			Date d = null;
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			
			try{
				d = format.parse(dateFechaNacimiento.getDayOfMonth()+"/"+(1+dateFechaNacimiento.getMonth())+"/"+dateFechaNacimiento.getYear());
			}
			catch(ParseException e){
				e.printStackTrace();
			}
			return d;
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
    	
		
		private void ActualizarPersona(String nombre, String apaterno,String amaterno, String email, Date fechaNacimiento, String sexo) {
			PersonasSQLiteHelper helper = new PersonasSQLiteHelper(getApplicationContext());
			SQLiteDatabase db = helper.getWritableDatabase();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			
			ContentValues valores = new ContentValues();
			valores.put("nombre",nombre);
			valores.put("apaterno",apaterno);
			valores.put("amaterno",amaterno);
			valores.put("email",email);
			valores.put("fechaNacimiento",format.format(fechaNacimiento));
			valores.put("sexo",sexo);
			
			//db.insert("persona", null, valores);
			db.update("persona", valores, "id="+idDeLaPersona, null);
			
			db.close();
		}
    }
}
