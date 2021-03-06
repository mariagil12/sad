package mvc;

import java.io.*;

//Controller
public class EditableBufferedReader extends BufferedReader{
	
	private Line linea;
	private Console consola;
		
	@SuppressWarnings("deprecation")
	public EditableBufferedReader(Reader in) {
		super(in);
		this.linea=new Line();
		this.consola=new Console();
		this.linea.addObserver(this.consola);
	}
}

