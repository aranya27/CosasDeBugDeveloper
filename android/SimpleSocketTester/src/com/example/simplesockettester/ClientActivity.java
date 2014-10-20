package com.example.simplesockettester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends ActionBarActivity {
	Socket socket;
	EditText txt_server_address,txt_server_port;
	TextView txt_info;
	Button btn_connect;

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
							socket.connect(new InetSocketAddress(server, Integer.parseInt(port)),3000); 
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
	}
	
	public void setMessageInfo(int idMessage, int idStyle){
		txt_info.setText(idMessage);
		txt_info.setTextAppearance(this, idStyle);
	}
	
	public void changeConnectButtonText(int idMessage){
		btn_connect.setText(idMessage);
	}
	
	
	
	
	class XXX implements Runnable{

		@Override
		public void run(){
			
		}
		
	}
}
