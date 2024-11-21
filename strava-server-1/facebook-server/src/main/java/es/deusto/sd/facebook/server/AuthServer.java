package es.deusto.sd.facebook.server;

import java.io.IOException;
import java.net.ServerSocket;

public class AuthServer {
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: FacebookAuth [PORT]");
			System.exit(1);
		}
		
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - FacebookAuth: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				System.out.println(" - FacebookAuth: New client connection accepted. Client number: " + ++numClients);
				
			}
		} catch (IOException e) {
			System.err.println("# FacebookAuth: IO error:" + e.getMessage());
		}
	}
	
}
