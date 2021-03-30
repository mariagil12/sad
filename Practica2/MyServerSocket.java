package sad_practica2;

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyServerSocket {
	private int port;
	private static ConcurrentHashMap<String, Server> clients = new ConcurrentHashMap<String, Server>();
	// Guarda els clients en un map on key es el client i value es el server
	// El Concurrent map s'encarrega de bloquejar i desbloquejar
	public MyServerSocket(int port) {
		this.port=port;
	}
	
	public void execute() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Successfully connected to "+port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New connection!");
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
	
	// Enviar missatge a bradcast
	void broadcast(String message, String excludeUser) {
		for(String key : this.clients.keySet() ) {
			if (!key.equals(excludeUser)) {
				this.clients.get(key).sendMessage(message);
			}
		}
	}
	
  // Afegir clients: guarda username i el thread server 
	void addUserName(String userName, Server server) {
		this.clients.put(userName,server);
	}
	
  // Eliminar client: eliminar username i threads
  // S'envia missatge als altres users
	void removeUser(String userName, Server server) {
		this.clients.remove(userName,server);
		System.out.println("The user " + userName + " has quitted.");
	}
	
	Set<String> getUserNames() {
		Set<String> userNames = new HashSet<>();
		for (String key : this.clients.keySet()) {
			userNames.add(key);
		}
		return userNames;
	}
	
	boolean hasUser() {
		return !this.clients.isEmpty();
	}
}
