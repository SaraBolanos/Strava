package es.deusto.sd.FacebookAuth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class AuthService extends Thread{
	private final UserService userService = new UserService();
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	
	private static String DELIMITER = "#";
	
	public AuthService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# FacebookAuth - TCPConnection IO error:" + e.getMessage());
		}
	}
	
	public void run() {
		try {
			String data = this.in.readUTF();			
			System.out.println("   - FacebookAuth - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			
			data = this.login(data);
					
			this.out.writeUTF(data);					
			System.out.println("   - FacebookAuth - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FacebookAuth - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookAuth - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookAuth - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String login(String msg) {
		String respuesta = "401";
		if (msg != null && !msg.trim().isEmpty()) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(msg, DELIMITER);		
				String email = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				if(this.userService.login(email, password)) {
					respuesta = "200";
				}
				
			} catch (Exception e) {
				System.err.println("   # TranslationService - Translation API error:" + e.getMessage());
				respuesta = "500";
			}
		}
		return respuesta;
	}
}
