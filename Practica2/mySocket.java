import java.net.*;
import java.io.*;

public class MySocket {
	private int port;
	private String user;
	private String host;
	
	public MySocket(String host, int port) {
		this.host=host;
		this.port=port;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String u) {
		this.user=u;
	}
	
	public void execute() {
		try {
			Socket soc=new Socket(this.host, this.port);
			System.out.println("Successfully connected to server"); 
			new RThread(soc,this).start();
			new WThread(soc,this).start();
		} catch(UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		if(args.length<2) { // Si no hay 2 argumentos...
			System.out.println("Syntax error. Syntax should be: java MySocket <hostName> <portNumber>");
			System.exit(0);
		}
		String host=args[0];
		int port= Integer.parseInt(args[1]);
		
		MySocket client = new MySocket(host,port);
		client.execute();
	}
}
