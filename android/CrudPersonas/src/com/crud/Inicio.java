package com.crud;

import com.crud.altas.AltaPersona;
import com.crud.bd.PersonasSQLiteHelper;
import com.crud.beans.Opcion;
import com.crud.busqueda.BuscarPersona;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Inicio extends Activity {
	Inicio context = this;
	
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
					
					case 1:
						intent  = new Intent(view.getContext(),BuscarPersona.class);
						break;
					
					case 2:
						AlertDialog.Builder builder = new AlertDialog.Builder(context);
						
						builder.setTitle("Confirmación");
						builder.setMessage("¿Seguro desea eliminar todos los registros existentes?");
						builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
							   public void onClick(DialogInterface dialog, int which) {
								   	PersonasSQLiteHelper helper = new PersonasSQLiteHelper(getApplicationContext());
									SQLiteDatabase db = helper.getWritableDatabase();
									db.execSQL("delete from persona");
									db.close();
									
									String ns = Context.NOTIFICATION_SERVICE;
									NotificationManager notManager = (NotificationManager) getSystemService(ns);
									
									int icono = android.R.drawable.stat_sys_warning;
									CharSequence textoEstado = "Alerta!";
									long hora = System.currentTimeMillis();
									Notification notif = new Notification(icono, textoEstado, hora);
									
									Context contexto = getApplicationContext();
									CharSequence titulo = "Alerta de que se borro todo";
									CharSequence descripcion = "Se la pelaron todas las personas";
									 
									Intent notIntent = new Intent(contexto,Inicio.class);
									 
									PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
									 
									notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
									
									notManager.notify(1, notif);
									dialog.cancel();
							   }
							});
						builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
							   public void onClick(DialogInterface dialog, int which) {
								   //dialog.cancel();
							   }
							});
						
						
						builder.create().show();
						break;
				}

				
				if(intent!=null)
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
