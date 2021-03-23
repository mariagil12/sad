import java.io.*;
import java.net.*;

public class WThread extends Thread{
	private PrintWriter write;
	private MySocket client;
	private Socket soc;
	
	public WThread(Socket s, MySocket ms) {
		this.soc=s;
		this.client=ms;
		try {
			OutputStream out = soc.getOutputStream();
			write=new PrintWriter(out, true);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void run() {
		Console console = System.console();
		String userName=console.readLine("Enter your name: \n");
		client.setUser(userName);
		write.println(userName);
		String msg;
		do {
			msg=console.readLine("["+userName+"]: ");
			write.println(msg);
		}while(msg!=null); 
		try {soc.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
