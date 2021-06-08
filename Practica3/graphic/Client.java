package graphic;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Client {

	public static volatile boolean success = false;
	public static volatile boolean contacts = false;
	private static volatile String name = "name";
	private static Selector selector;
	private static SocketChannel socketChannel;
	private static ByteBuffer buffer = ByteBuffer.allocate(1024);
	private final static InetSocketAddress address = new InetSocketAddress("127.0.0.1", 1234);

	static private String rec(SocketChannel channel) throws IOException {
		buffer.clear();
		int count = channel.read(buffer);
		return new String(buffer.array(), 0, count, StandardCharsets.UTF_8);
	}

	static private void write(SocketChannel channel, String content) throws IOException {
		buffer.clear();
		buffer.put(content.getBytes(StandardCharsets.UTF_8));
		buffer.flip();
		channel.write(buffer);
	}

	public static class MessageThread extends Thread{
		private JTextArea target;
		private JTextArea contactTarget;
		public MessageThread(JTextArea target, JTextArea contactTarget){
			this.target = target;
			this.contactTarget = contactTarget;
		}
		@Override
		public void run() {
			SocketChannel client = null;
			try {
				while (true) {
					selector.select();
					Set<SelectionKey> set = selector.selectedKeys();
					Iterator<SelectionKey> iterator = set.iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						iterator.remove();
						if (key.isReadable()) {
							client = ((SocketChannel) key.channel());
							String msg = rec(client);
							if (msg.contains("hello")) {
								name = msg.substring(6);
								success = true;
							}
							if(msg.contains("namesXX")){
								String[] contacts = msg.split(",");
								String from = contactTarget.getText();
								if (from.length() > 0)
									contactTarget.replaceRange(" ",0,from.length());
								contactTarget.append("Contacts\nYou\n");
								for(int i =1; i<contacts.length; i++){
									if(!contacts[i].equals(name)){
										contactTarget.append(contacts[i] + "\n");
									}
								}
							}else{
								target.append(msg + "\r\n");
								target.setCaretPosition(target.getText().length());
								key.interestOps(SelectionKey.OP_READ);
							}
						}
					}
					set.clear();
				}
			} catch (Exception e) {
				if (client != null) {
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void start() throws IOException{
		socketChannel = SocketChannel.open(address);
		selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
	}
	
	public static void setName(String name) throws IOException {
		Client.name = name;
		write(socketChannel, name);
	}
	
	public static void sendMsg(String msg) throws IOException {
		write(socketChannel, name + "###" + msg);
	}
	
	public static void main(String[] args)  {

	}
}
