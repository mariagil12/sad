import java.io.*;
import java.net.*;

public class RThread extends Thread{
	private BufferedReader buf;
	private MySocket client;
	private Socket soc;
	
	public RThread (Socket s, MySocket ms) {
		this.client=ms;
		this.soc=s;
		try {
			InputStream in = soc.getInputStream();
			buf=new BufferedReader(new InputStreamReader(in));
		}catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				String read=buf.readLine();
				System.out.println(read);
				if(client.getUser()!=null) {
					System.out.println("From: ["+client.getUser()+"]\n");
				}
			}catch(IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}

}
