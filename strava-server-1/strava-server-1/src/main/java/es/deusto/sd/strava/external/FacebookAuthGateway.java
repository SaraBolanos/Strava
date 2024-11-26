package es.deusto.sd.strava.external;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FacebookAuthGateway implements IFacebookAuthGateway {
	//data for socket connection
    private static String serverIP = "0.0.0.0";
	private static int serverPort = 9550;
    private static String DELIMITER = "#";
    
    public FacebookAuthGateway() {
    	
    }
    
    @Override
    public boolean verifyFacebookAuth(String email, String password) {
    	String message = email+DELIMITER+password;
    	String response = null;
    	
    	try (Socket socket = new Socket(serverIP, serverPort);
    			//Streams to send and receive information are created from the Socket
    			DataInputStream in = new DataInputStream(socket.getInputStream());
    			DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
    		out.writeUTF(message);
    		System.out.println(" - Sending data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
    		response = in.readUTF();
    		
    	}catch (UnknownHostException e) {
			System.err.println("# FacebookAuth. SocketClient: Socket error: " + e.getMessage());	
		} catch (EOFException e) {
			System.err.println("# FacebookAuth. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# FacebookAuth. SocketClient: IO error: " + e.getMessage());
		}
    	if(response.equals("200")) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
