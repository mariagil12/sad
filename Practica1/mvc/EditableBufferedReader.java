package mvc;

import java.io.*;


public class EditableBufferedReader extends BufferedReader{
	
	private Line linea;
	
	public EditableBufferedReader(Reader in) {
		super(in);
		this.linea=new Line();
	}
	
}
