package com.crud.busqueda;

import java.util.*;

import com.crud.R;
import com.crud.altas.AltaPersona;
import com.crud.bd.PersonasSQLiteHelper;
import com.crud.beans.Opcion;
import com.crud.modificaciones.ModificarPersona;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class BuscarPersona extends Activity{
	Activity context = this;
	private Opcion[] personasEncontradas;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda);
        
        
        //Llenamos el spinner con las opciones de busqueda
        /*Spinner s = (Spinner)findViewById(R.id.seleccionBusqueda);
    	List<String> list = new ArrayList<String>();
    	list.add("Nombre");
    	list.add("Apellido Paterno");
    	list.add("Apellido Materno");
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	s.setAdapter(dataAdapter);
        */
        
        
        Button b = (Button)findViewById(R.id.btnBuscar);
        b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = new AlertDialog.Builder(context).create();
				alertDialog.setTitle("Información Incompleta");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					   public void onClick(DialogInterface dialog, int which) {
					      
					   }
					});
				alertDialog.setIcon(R.drawable.ic_launcher);
				
				
				String textoABuscar = ((EditText)findViewById(R.id.txtBusqueda)).getText().toString().trim();
				String buscarPor = ((Spinner)findViewById(R.id.seleccionBusqueda)).getSelectedItem().toString();
				
				if(textoABuscar.equals("")){
					alertDialog.setMessage("Favor de ingresar un texto para buscar por "+buscarPor);
					alertDialog.show();
				}
				else{
					PersonasSQLiteHelper helper = new PersonasSQLiteHelper(getApplicationContext());
					SQLiteDatabase db = helper.getReadableDatabase();
					Cursor c;
					String parametro = null;
					
					if(buscarPor.equals("Nombre"))
						parametro = "nombre";
					else if(buscarPor.equals("Apellido Paterno"))
						parametro = "apaterno";
					else
						parametro = "amaterno";

					
					String query = "select id, nombre||' '||apaterno||' '||amaterno, email from persona where upper("+parametro+") like upper('%"+textoABuscar+"%')";
					c = db.rawQuery(query,null);
					
					if(c.getCount()>0){
						personasEncontradas = new Opcion[c.getCount()];
						Opcion o;
						if (c.moveToFirst()) {
						     do {
						    	 o = new Opcion( c.getInt(0),c.getString(1),c.getString(2) );
						    	 personasEncontradas[c.getPosition()] = o;
						     } while(c.moveToNext());
						}
						db.close();
						
						AdaptadorPersonasEncontradas adaptador = new AdaptadorPersonasEncontradas(context);
						ListView lstOpciones = (ListView)findViewById(R.id.listaPersonasEncontradas);
				        lstOpciones.setAdapter(adaptador);
					}
					else{
						alertDialog.setMessage("No se encontraron coincidencias");
						alertDialog.show();
					}
				}
				
			}
        	
        });
        
        
        
        ListView l = (ListView)findViewById(R.id.listaPersonasEncontradas);
        l.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				int idDeLaPersona = ((Opcion)parent.getItemAtPosition(position)).getId();
				Intent intent = new Intent(context,ModificarPersona.class);
				intent.putExtra("idDeLaPersona", idDeLaPersona);
				startActivity(intent);
			}
        	
        });
        
        
	}
	
	
	class AdaptadorPersonasEncontradas extends ArrayAdapter{

		Activity context;
    	
		public AdaptadorPersonasEncontradas(Activity context) {
			super(context, R.layout.rowdebusqueda, personasEncontradas);
			this.context=context;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			 View item = convertView;
			 
			 if(item==null){
				 LayoutInflater inflater = context.getLayoutInflater();
				 item = inflater.inflate(R.layout.rowdebusqueda, null);
			 }

			 TextView personaEncontrada = (TextView)item.findViewById(R.id.personaEncontrada);
			 personaEncontrada.setText(personasEncontradas[position].getOpcion());
			 TextView emailEncontrado = (TextView)item.findViewById(R.id.emailEncontrado);
			 emailEncontrado.setText(personasEncontradas[position].getDescripcion());
			 return(item);
		}
	}
	

}
