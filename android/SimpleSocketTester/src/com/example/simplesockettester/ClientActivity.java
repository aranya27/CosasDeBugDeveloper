package com.example.simplesockettester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.sockettester.utils.Util;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ClientActivity extends  Activity {
	ServerSocket serverSocket;
	ArrayList<Socket> clientSocketList;
	Socket socket;
	EditText txt_server_address,txt_server_port,txt_input_client,txt_output_client;
	TextView txt_info;
	ToggleButton btn_connect_disconnect, btn_start_stop_server;
	RadioGroup radioG_behavior;
	RadioButton radio_client, radio_server;
	Button btn_send, btn_clear_info_to_send, btn_clear_info_from_server;
	CheckBox chk_hex_client, chk_hex_server;
	Spinner spinner_encoding_client, spinner_encoding_server;
	boolean serverOn = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		
        findViews();
		addListenersToViews();
        
		
		restoreInputsData();
	}
	
	private void findViews(){
		
		btn_connect_disconnect = (ToggleButton)this.findViewById(R.id.btn_connect_disconnect);
		btn_start_stop_server = (ToggleButton)this.findViewById(R.id.btn_start_stop_server);
		txt_server_address = (EditText)this.findViewById(R.id.txt_server_address);
		txt_server_port = (EditText)this.findViewById(R.id.txt_server_port);
		txt_info = (TextView)this.findViewById(R.id.txt_info);
		radioG_behavior = (RadioGroup)this.findViewById(R.id.radioG_behavior);
		radio_client = (RadioButton)this.findViewById(R.id.radio_client);
		radio_server = (RadioButton)this.findViewById(R.id.radio_server);
		txt_input_client = (EditText)this.findViewById(R.id.txt_input_client);
		txt_output_client = (EditText)this.findViewById(R.id.txt_output_client);
		btn_clear_info_to_send = (Button)this.findViewById(R.id.btn_clear_info_to_send);
		btn_clear_info_from_server = (Button)this.findViewById(R.id.btn_clear_info_from_server);
		chk_hex_client = (CheckBox)this.findViewById(R.id.chk_hex_client);
		chk_hex_server = (CheckBox)this.findViewById(R.id.chk_hex_server);
		btn_send = (Button)this.findViewById(R.id.btn_send);
		spinner_encoding_client = (Spinner)this.findViewById(R.id.spinner_encoding_client);
		spinner_encoding_server = (Spinner)this.findViewById(R.id.spinner_encoding_server);
	}
	
	private void closeAllSockets(){
		if( socket!=null && socket.isConnected() ){
			try {
				socket.close();
			} catch (IOException e) {}
			finally{
				socket = null;
			}
		}
		
		if( clientSocketList != null ){
			for(Socket s : clientSocketList){
				try {
					s.close();
				} catch (IOException e) {}
			}
		}
		
		if( serverSocket != null && !serverSocket.isClosed() ){
			try {
				serverSocket.close();
			} catch (IOException e) {}
			finally{
				serverSocket = null;
			}
		}
		
		
		btn_connect_disconnect.setChecked(false);
		btn_start_stop_server.setChecked(false);
		setMessageInfo("", null);
	}
	
	private void changeToClientMode(){
		closeAllSockets();
		txt_server_address.setFocusable(true);
		txt_server_address.setFocusableInTouchMode(true);
		txt_server_address.setClickable(true);
		btn_connect_disconnect.setVisibility( android.view.View.VISIBLE );
		btn_start_stop_server.setVisibility( android.view.View.GONE );
		txt_output_client.setHint(R.string.server_answer);
		showHideCharsetOptions();
		txt_info.setText("");
		txt_server_address.setText("");
	}
	
	private void changeToServerMode(){
		closeAllSockets();
		txt_server_address.setFocusable(false);
		txt_server_address.setFocusableInTouchMode(false);
		txt_server_address.setClickable(false);
		txt_server_address.setText("");
		btn_connect_disconnect.setVisibility( android.view.View.GONE );
		btn_start_stop_server.setVisibility( android.view.View.VISIBLE );
		txt_output_client.setHint(R.string.clients_answer);
		showHideCharsetOptions();
		txt_info.setText("");
		txt_server_address.setText("");
	}
	
	private void addListenersToViews(){
		
		radioG_behavior.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener(){


			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.radio_client){
					changeToClientMode();
				}else{
					changeToServerMode();
				}
				
			}
			
		});
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.encodings, R.layout.spinner_item_text);
		adapter.setDropDownViewResource(R.layout.spinner_item_text_dropdown);
		spinner_encoding_client.setAdapter(adapter);
		spinner_encoding_server.setAdapter(adapter);
		
		btn_connect_disconnect.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				txt_info.setText("");
				if( !isChecked && socket != null ){
					closeAllSockets();
					setMessageInfo(R.string.socket_not_connected, R.style.txt_info);
				}
				else{
					String server = txt_server_address.getText().toString().trim();
					String port = txt_server_port.getText().toString().trim();
					
					if( !server.equals("") && !port.equals("") ){
						try {
							socket = new Socket();
							socket.connect(new InetSocketAddress(server, Integer.parseInt(port)),2000); 
							ClientHandlerThread sd = new ClientHandlerThread();
							sd.execute();
							
							setMessageInfo(R.string.socket_connected, R.style.txt_success);
						}catch (UnknownHostException e) {
							setMessageInfo(R.string.unknown_host, R.style.txt_error);
							buttonView.setChecked(false);
							e.printStackTrace();
						} catch (IOException e) {
							setMessageInfo(R.string.couldn_connect_to_host, R.style.txt_error);
							buttonView.setChecked(false);
							e.printStackTrace();
						}
					}
					else{
						buttonView.setChecked(false);
					}
				}
			}
			
		});
		
		btn_start_stop_server.setOnCheckedChangeListener( new android.widget.CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				txt_info.setText("");
				if( !isChecked ){
					closeAllSockets();
					setMessageInfo(R.string.server_off, R.style.txt_info);
					serverOn = false;
				}
				else{
					String port = txt_server_port.getText().toString().trim();
					if( !port.equals("") ){
						try {
							serverSocket = new ServerSocket( Integer.parseInt(port) );
							ServerHandlerThread sd = new ServerHandlerThread();
							serverOn = true;
							clientSocketList = new ArrayList<Socket>();
							sd.execute();
							refreshInfoIPAndClientsConnected();
							setMessageInfo(R.string.server_on, R.style.txt_success);
						} catch (IOException e) {
							setMessageInfo(R.string.couldn_start_server, R.style.txt_error);
							buttonView.setChecked(false);
							e.printStackTrace();
						}
					}
					else{
						buttonView.setChecked(false);
					}
				}
				
			}
			
		});
		
		btn_send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String infoToSend = txt_input_client.getText().toString();
				byte[] bytesToSend = null;
				try{
					if( chk_hex_client.isChecked() ){
						bytesToSend = Util.fromHexStringToByteArray(infoToSend);
					}
					else{
						bytesToSend = infoToSend.getBytes( spinner_encoding_client.getSelectedItem().toString() );
					}
				}catch(Exception e){
					setMessageInfo(R.string.invalid_message, R.style.txt_error);
				}
				
				
				
				
				
				if(bytesToSend != null ){
					if( socket!=null && socket.isConnected() ){
						try{
							socket.getOutputStream().write(bytesToSend);
						}catch( Exception e){
							
						}
					}
					
					if( clientSocketList != null){
						for(Socket s : clientSocketList){
							try{
								s.getOutputStream().write(bytesToSend);
							}catch( Exception e){
								
							}
						}
					}
				}
				
				
					
			}
			
		});
		
		btn_clear_info_to_send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				txt_input_client.setText("");
			}
		});
		
		btn_clear_info_from_server.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				txt_output_client.setText("");
			}
		});
		
		chk_hex_client.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				txt_input_client.setText("");
				showHideCharsetOptions();
				//Util.changeInfoToBytesStringOrToString(txt_input_client, ((CheckBox)v).isChecked());
			}
			
		});
		
		/*
		chk_hex_client.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Util.changeInfoToBytesStringOrToString(txt_input_client, isChecked);
			}
			
		});
		*/
		
		chk_hex_server.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				txt_output_client.setText("");
				showHideCharsetOptions();
				//Util.changeInfoToBytesStringOrToString(txt_output_client, ((CheckBox)v).isChecked());
			}
			
		});
		
		/*
		chk_hex_server.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Util.changeInfoToBytesStringOrToString(txt_output_client, isChecked);
			}
			
		});
		*/
		
	}
	
	
	private void showHideCharsetOptions(){
		spinner_encoding_client.setVisibility( chk_hex_client.isChecked() ? android.view.View.INVISIBLE : android.view.View.VISIBLE );
		spinner_encoding_server.setVisibility( chk_hex_server.isChecked() ? android.view.View.INVISIBLE : android.view.View.VISIBLE );
	}
	
	private void saveInputsData(){
		SharedPreferences prefs = getSharedPreferences(Util.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("client_mode", radio_client.isChecked() );
		editor.putString("clientview_server_address", txt_server_address.getText().toString() );
		editor.putString("clientview_server_port", txt_server_port.getText().toString() );
		editor.putString("clientview_info_to_send", txt_input_client.getText().toString() );
		editor.putBoolean("clientview_client_hex_info", chk_hex_client.isChecked() );
		editor.putString("clientview_info_received", txt_output_client.getText().toString() );
		editor.putBoolean("clientview_server_hex_info", chk_hex_server.isChecked() );
		editor.commit();
		
		Map<String, ?> allEntries = prefs.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
		    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
		} 
	}
	
	private void restoreInputsData(){
		SharedPreferences prefs = getSharedPreferences(Util.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		if( prefs != null){	
			Map<String, ?> allEntries = prefs.getAll();
			for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
			}
			
			boolean client_mode = prefs.getBoolean("client_mode", true);
			if( client_mode ){
				radio_client.setChecked(true);
			}else{
				radio_server.setChecked(true);
			}
			
			
			txt_server_address.setText( prefs.getString("clientview_server_address", "") );
			txt_server_port.setText( prefs.getString("clientview_server_port", "") );
			txt_input_client.setText( prefs.getString("clientview_info_to_send", "") );
			chk_hex_client.setChecked( prefs.getBoolean("clientview_client_hex_info", false) );
			txt_output_client.setText( prefs.getString("clientview_info_received", "") );
			chk_hex_server.setChecked( prefs.getBoolean("clientview_server_hex_info", false) );
			
			if(socket != null && socket.isConnected() ){
				btn_connect_disconnect.setChecked( true );
			}
			
			if(serverSocket != null && !serverSocket.isClosed() ){
				btn_start_stop_server.setChecked( true );
			}
			
		}
	}
	
	private void setMessageInfo(int idMessage, Integer idStyle){
		txt_info.setText(idMessage);
		if(idStyle != null) txt_info.setTextAppearance(this, idStyle);
	}
	
	private void setMessageInfo(String message, Integer idStyle){
		txt_info.setText(message);
		if(idStyle != null) txt_info.setTextAppearance(this, idStyle);
	}
	
	private void appendServerAnswer(byte[] answer){
		StringBuilder result = new StringBuilder( txt_output_client.getText().toString() );
		if(chk_hex_server.isChecked()){
			result.append( Util.fromByteArrayToHexString(answer) );
		}
		else{
			try{
				result.append(new String(answer, spinner_encoding_server.getSelectedItem().toString() ) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		txt_output_client.setText( result.toString() );
		
	}
	
	private void refreshInfoIPAndClientsConnected(){
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
		String clients_connected_message = getResources().getString(R.string.clients_connected);
		String local_ip_message = getResources().getString(R.string.local_ip);
		
		int clientsConnected = 0;
		
		if(clientSocketList != null) clientsConnected = clientSocketList.size();
		
		
		txt_server_address.setText(local_ip_message+": "+ip+" - "+clients_connected_message+": "+clientsConnected);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        setContentView(R.layout.activity_client);

	    } else {
	        setContentView(R.layout.activity_client);
	    }
	    setContentView(R.layout.activity_client);
	    restoreInputsData();*/
	}
	
	
	private class ServerHandlerThread extends AsyncTask <Void, byte[], Void> {
		@Override
	    protected void onPreExecute() {
			clientSocketList = new ArrayList<Socket>();
		}
		
		@Override
	    protected void onProgressUpdate(byte[]... bytes) {
			appendServerAnswer(bytes[0]);
		}
		
		//appendServerAnswer(content);
		
		@Override
		protected Void doInBackground(Void... params) {
			Socket so;
			
			while(serverOn){
				try{
					so = serverSocket.accept();
					clientSocketList.add(so);
					runOnUiThread( 
	            		new Runnable() {
	                        @Override
	                        public void run() {
	                        	refreshInfoIPAndClientsConnected();
	                        }
	                    }
	            	);
					
					Thread cliThread = new Thread( new ClientServiceThread(so) );
	                cliThread.start(); 
	                Log.d("MSG", "Nuevo hilo creado");
					
				}catch(Exception e){
					Log.d("MSG", "Se termino");
					e.printStackTrace();
				}
			}
			
			return null;
		}
		
		@Override 
		protected void onPostExecute(Void res) {
			
		}
	}
	
	
	class ClientServiceThread implements Runnable{
		Socket clientSocket;
		
		public ClientServiceThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			InputStream is = null;
            
            try{
            	is = clientSocket.getInputStream();
	            if(serverOn){
	            	final byte[] bytes = new byte[2048];
	            	int count;
	                while ((count = is.read(bytes)) > 0) {
	                	Log.d("MSG", "Nomas esperando respuestas");
	                	final int finalCount = count;
	                	runOnUiThread( 
	                		new Runnable() {
		                        @Override
		                        public void run() {
		                        	appendServerAnswer( Arrays.copyOfRange(bytes, 0, finalCount) );
		                        }
		                    }
	                	);
	                	
	                	
	                }
	            }
            }catch(Exception e){
            	//e.printStackTrace();
            }
            finally{
            	Log.d("MSG", "ADIOS PUTO");
            	if( clientSocketList != null ) clientSocketList.remove(clientSocket);
            	runOnUiThread( 
            		new Runnable() {
                        @Override
                        public void run() {
                        	refreshInfoIPAndClientsConnected();
                        }
                    }
            	);
            	
            }
		}
		
	}
	
	
	
	private class ClientHandlerThread extends AsyncTask <Void, byte[], Void> {
		InputStream in = null;
		
		@Override
	    protected void onPreExecute() {
			try {
				in = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
	    protected void onProgressUpdate(byte[]... bytes) {
			appendServerAnswer(bytes[0]);
		}
		
		//appendServerAnswer(content);
		
		@Override
		protected Void doInBackground(Void... params) {
			byte[] content = new byte[ 2048 ];  
			
			if(in != null){
				int bytesRead;  
				try {
					while( ( bytesRead = in.read( content ) ) != -1 ) {  
						publishProgress(Arrays.copyOfRange(content, 0, bytesRead));
						//http://stackoverflow.com/questions/10475898/receive-byte-using-bytearrayinputstream-from-a-socket
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Se acaba el hilo");
			return null;
		}
		
		@Override 
		protected void onPostExecute(Void res) {
			System.out.println("Ejecutando onPostExecute");
			setMessageInfo(R.string.socket_not_connected, R.style.txt_info);
			try {
				socket.close();
			} catch (Exception e1) { }
		}
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		saveInputsData();
	}
	
	
}
