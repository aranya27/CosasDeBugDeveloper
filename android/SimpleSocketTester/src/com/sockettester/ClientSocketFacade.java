package com.sockettester;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketFacade {
	Socket socket;
	
	public void connect(String server, int port) throws IOException{
		socket = new Socket(server, port);
	}
	
	
	
	
	
}
