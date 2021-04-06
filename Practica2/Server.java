import java.io.*;
import java.net.*;

public class Server extends Thread {
	private Socket socket;
	private MyServerSocket server;
	private PrintWriter writer;
	
	public Server (Socket socket, MyServerSocket server) {
		this.socket = socket;
		this.server = server;
	}
	
	// Es notifica per broadcast quan es conecta i desconecta un user
	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader (new InputStreamReader (input));
			
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output,true);
			
			printUsers();
			
			String userName = reader.readLine();
			server.addUserName(userName, this);
			
			String serverMessage = "New user connected: " + userName;
			server.broadcast(serverMessage, userName);
			
			String clientMessage;
			
			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]: " + clientMessage;
				server.broadcast(serverMessage, userName); 
			} while (clientMessage != null);
			
			server.removeUser(userName, this);
			socket.close();
			
			serverMessage = userName + " has quitted";
			server.broadcast(serverMessage, userName);
		} catch (IOException ex) { ex.printStackTrace(); }
	}
	
	// S'envia llista de qui hi ha conectat quan es conecta un nou user
	void printUsers() {
		if (server.moreUsers()) {
			writer.println("Connected users: " + server.getUserNames());
		} else {
			writer.println("No users connected");
		}
	}
	
	// Enviar missatge
		void sendMessage(String message) {
			writer.println(message);
		}
}
