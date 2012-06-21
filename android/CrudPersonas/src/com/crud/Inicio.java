package com.crud;

import com.crud.altas.AltaPersona;
import com.crud.beans.Opcion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Inicio extends Activity {
    /** Called when the activity is first created. */
	
	//Inicio context=this;
	private Opcion[] opciones = new Opcion[]{
			new Opcion("Alta","Agregar una persona"),
			new Opcion("Buscar","Buscar una persona"),
			new Opcion("Eliminar todo","Eliminar todos los registros existentes")
			
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        AdaptadorOpciones adaptador = new AdaptadorOpciones(this);
         
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
         
        lstOpciones.setAdapter(adaptador);
        
        lstOpciones.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*System.out.println("parent = "+parent);
				System.out.println("view = "+view);
				System.out.println("position = "+position);
				System.out.println("id = "+id);
				System.out.println("desc = "+((TextView)view.findViewById(R.id.descripcionDeLaOpcion)).getText());*/
				
				Intent intent=null;
				//intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				
				switch(position){
				case 0:
					intent = new Intent(view.getContext(),AltaPersona.class);
					break;
				}
				
				
				startActivity(intent);
				
			}
        	
        });
        
        
    }
    
    
    
    class AdaptadorOpciones extends ArrayAdapter{
    	Activity context;
    	
		public AdaptadorOpciones(Activity context) {
			super(context, R.layout.rowdelistado, opciones);
			this.context=context;
		}
		
		 public View getView(int position, View convertView, ViewGroup parent) {
			 View item = convertView;
			 
			 if(item==null){
				 LayoutInflater inflater = context.getLayoutInflater();
				 item = inflater.inflate(R.layout.rowdelistado, null);
			 }
			 
			 TextView lblTitulo = (TextView)item.findViewById(R.id.Opcion);
			 lblTitulo.setText(opciones[position].getOpcion());
			 TextView lblSubtitulo = (TextView)item.findViewById(R.id.descripcionDeLaOpcion);
			 lblSubtitulo.setText(opciones[position].getDescripcion());
			 return(item);
		 }
    	
    }
    
}