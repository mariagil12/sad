import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.net.*;

public class MyServerSocket {
	private int port;
	private static ConcurrentHashMap<String, Server> clients = new ConcurrentHashMap<String, Server>();
	// Guarda els clients en un map on key es el client i value es el server
	// El Concurrent map s'encarrega de bloquejar i desbloquejar
	public MyServerSocket(int port) {
		this.port=port;
	}
	
	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Server listening to port"+port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client successfully connected");
				Server newUsr = new Server (socket, this);
				newUsr.start();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) {
		if(args.length<1) {
			System.out.println("Syntax error. Syntax should be: java MyServerSocket <portNumber>");
			System.exit(0);
		}
		int port= Integer.parseInt(args[0]);
		MyServerSocket server = new MyServerSocket(port);
		server.execute();
	}
	
	// Enviar missatge a broadcast
	void broadcast(String message, String excludeUser) {
		for(String key : clients.keySet() ) {
			if (!key.equals(excludeUser)) {
				clients.get(key).sendMessage(message);
			}
		}
	}
	
  // Afegir clients: guarda username i el thread server 
	void addUserName(String userName, Server server) {
		clients.put(userName,server);
	}
	
  // Eliminar client: eliminar username i threads
  // S'envia missatge als altres users
	void removeUser(String userName, Server server) {
		clients.remove(userName,server);
		System.out.println("The user " + userName + " quitted.");
	}
	
	Set<String> getUserNames() {
		Set<String> userNames = new HashSet<>();
		for (String key : clients.keySet()) {
			userNames.add(key);
		}
		return userNames;
	}
	
	// Retorna true si hi ha més usuaris connectats
	boolean moreUsers() {
		return !clients.isEmpty();
	}
}
