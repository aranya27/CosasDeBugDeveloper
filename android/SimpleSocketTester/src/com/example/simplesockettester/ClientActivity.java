package com.example.simplesockettester;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

import com.sockettester.utils.Util;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ClientActivity extends ActionBarActivity {
	Socket socket;
	EditText txt_server_address,txt_server_port,txt_input_client,txt_output_client;
	TextView txt_info;
	Button btn_connect, btn_send, btn_clear_info_to_send, btn_clear_info_from_server;
	CheckBox chk_hex_client, chk_hex_server;
	Spinner spinner_encoding_client, spinner_encoding_server;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
		setContentView(R.layout.activity_client);
		btn_connect = (Button)this.findViewById(R.id.btn_connect);
		txt_server_address = (EditText)this.findViewById(R.id.txt_server_address);
		txt_server_port = (EditText)this.findViewById(R.id.txt_server_port);
		txt_info = (TextView)this.findViewById(R.id.txt_info);
		txt_input_client = (EditText)this.findViewById(R.id.txt_input_client);
		txt_output_client = (EditText)this.findViewById(R.id.txt_output_client);
		btn_clear_info_to_send = (Button)this.findViewById(R.id.btn_clear_info_to_send);
		btn_clear_info_from_server = (Button)this.findViewById(R.id.btn_clear_info_from_server);
		chk_hex_client = (CheckBox)this.findViewById(R.id.chk_hex_client);
		chk_hex_server = (CheckBox)this.findViewById(R.id.chk_hex_server);
		btn_send = (Button)this.findViewById(R.id.btn_send);
		spinner_encoding_client = (Spinner)this.findViewById(R.id.spinner_encoding_client);
		//spinner_encoding_server = (Spinner)this.findViewById(R.id.spinner_encoding_server);
		
		/*
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.encodings, R.layout.spinner_item_text);
		adapter.setDropDownViewResource(R.layout.spinner_item_text_dropdown);
		*/
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.encodings, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		
		
		spinner_encoding_client.setAdapter(adapter);
		
		btn_connect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				txt_info.setText("");
				if(socket!=null && socket.isConnected()){
					try {
						socket.close();
					} catch (IOException e) {}
					finally{
						socket = null;
					}
					setMessageInfo(R.string.socket_not_connected, R.style.txt_info);
					changeConnectButtonText(R.string.connect);
				}
				else{
					String server = txt_server_address.getText().toString().trim();
					String port = txt_server_port.getText().toString().trim();
					
					if( !server.equals("") && !port.equals("") ){
						try {
							socket = new Socket();
							socket.connect(new InetSocketAddress(server, Integer.parseInt(port)),2000); 
							ServerDataHandlerThread sd = new ServerDataHandlerThread();
							sd.execute();
							
							setMessageInfo(R.string.socket_connected, R.style.txt_success);
							changeConnectButtonText(R.string.disconnect);
						}catch (UnknownHostException e) {
							setMessageInfo(R.string.unknown_host, R.style.txt_error);
							e.printStackTrace();
						} catch (IOException e) {
							setMessageInfo(R.string.couldn_connect_to_host, R.style.txt_error);
							e.printStackTrace();
						}
						
					}
				}
			}
		});
		
		btn_send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String infoToSend = txt_input_client.getText().toString();
				if( socket!=null && socket.isConnected()){
					byte[] bytesToSend = null;
					try{
						if( chk_hex_client.isChecked() ){
							bytesToSend = Util.fromHexStringToByteArray(infoToSend);
						}
						else{
							bytesToSend = infoToSend.getBytes(Util.CHARSET);
						}
					}catch(Exception e){
						setMessageInfo(R.string.invalid_message, R.style.txt_error);
					}
					
					if(bytesToSend != null ){
						try{
							socket.getOutputStream().write(bytesToSend);
						}catch( Exception e){
							
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
				Util.changeInfoToBytesStringOrToString(txt_input_client, ((CheckBox)v).isChecked());
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
				Util.changeInfoToBytesStringOrToString(txt_output_client, ((CheckBox)v).isChecked());
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
		
		restoreInputsData();
	}
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		saveInputsData();
	}
	
	private void saveInputsData(){
		SharedPreferences prefs = getSharedPreferences(Util.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
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
		System.out.println("prefs = "+prefs);
		System.out.println("txt_server_address = "+txt_server_address);
		if( prefs != null){	
			Map<String, ?> allEntries = prefs.getAll();
			for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
			} 
			txt_server_address.setText( prefs.getString("clientview_server_address", "") );
			txt_server_port.setText( prefs.getString("clientview_server_port", "") );
			txt_input_client.setText( prefs.getString("clientview_info_to_send", "") );
			chk_hex_client.setChecked( prefs.getBoolean("clientview_client_hex_info", false) );
			txt_output_client.setText( prefs.getString("clientview_info_received", "") );
			chk_hex_server.setChecked( prefs.getBoolean("clientview_server_hex_info", false) );
		}
	}
	
	private void setMessageInfo(int idMessage, int idStyle){
		txt_info.setText(idMessage);
		txt_info.setTextAppearance(this, idStyle);
	}
	
	private void changeConnectButtonText(int idMessage){
		btn_connect.setText(idMessage);
	}
	
	private void appendServerAnswer(byte[] answer){
		StringBuilder result = new StringBuilder( txt_output_client.getText().toString() );
		if(chk_hex_server.isChecked()){
			result.append( Util.fromByteArrayToHexString(answer) );
		}
		else{
			try{
				result.append(new String(answer, Util.CHARSET) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		txt_output_client.setText( result.toString() );
		
	}
	
	
	
	
	private class ServerDataHandlerThread extends AsyncTask <Void, byte[], Void> {
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // while  
			}
			
			System.out.println("SE acaba el hilo");
			return null;
		}
	}
	
	
	
	
	
}
