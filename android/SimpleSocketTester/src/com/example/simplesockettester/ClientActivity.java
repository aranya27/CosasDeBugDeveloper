package com.example.simplesockettester;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.sockettester.utils.Util;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends ActionBarActivity {
	Socket socket;
	EditText txt_server_address,txt_server_port,txt_input_client,txt_output_client;
	TextView txt_info;
	Button btn_connect, btn_send, btn_clear_info_to_send, btn_clear_info_from_server;
	CheckBox chk_hex_client, chk_hex_server;

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
		
		chk_hex_client.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Util.changeInfoToBytesStringOrToString(txt_input_client, isChecked);
			}
			
		});
		
		chk_hex_server.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Util.changeInfoToBytesStringOrToString(txt_output_client, isChecked);
			}
			
		});
		
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
