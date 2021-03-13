package no_mvc;

import java.io.*;

public class EditableBufferedReader extends BufferedReader{
	
	public static final int ESC_I = 27; //ESC int
	public static final char ESC_C = '\033'; //ESC char
	public static final int RIGHT = 39;
	public static final int LEFT = 37;
	public static final int HOME = 36;
	public static final int END = 35;
	public static final int INS = 155;
	public static final int DEL = 127;
	public static final int BACKSPACE = 8;
	public static final int ENTER = 10;
	
	private Line linea;
	
	/**
	 * CÓDIGO XTERM
	 * CSI == ESC [
	 * right: 		CSI C
	 * left: 		CSI D
	 * home:		CSI H
	 * end: 		CSI F
	 * insert:		CSI 2 ~
	 * delete:		CSI 3 ~
	 * backspace:   0x7F -> 127
	 * ESC:			/033
	 * 
	 * 
	 * CÓDIGO DECIMAL
	 * https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
	 * right:		39
	 * left: 		37
	 * home:		36
	 * end: 		35
	 * insert:		155
	 * delete:		127
	 * backspace: 	8
	 * ESC: 		27
	 * ENTER: 		10 
	 * 
	 */
	
	public EditableBufferedReader(Reader in) {
		super(in);
		this.linea=new Line();
	}

	protected void setRaw() {
		try{
			String[] cmd = {"/bin/sh","-c", "stty -echo raw </dev/tty"};
			Runtime.getRuntime().exec(cmd);
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}

	protected void unsetRaw() {
		try{
			String[] cmd = {"/bin/sh","-c", "stty -raw echo </dev/tty"};
			Runtime.getRuntime().exec(cmd);
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int read() throws IOException{
		int key=0;
		key=super.read(); //Si no entra en el if será un carácter
		if(key==ESC_C) { //será una secuencia de escape (CSI ... == ESC [ ...)
			key=super.read(); //leemos '[' y pasamos al siguiente carácter
			if(key==-1) {
				key=ESC_I; //Si no hay '[' es que la secuencia de escape es ESC 
			}
			else {
				switch(key=super.read()) { //leemos el 3r carácter
					case 'C':
							key=RIGHT;
					case 'D':
							key=LEFT;
					case 'H':
							key=HOME;
					case 'F':
							key=END;
					case '2':
						if((key=super.read())=='~') { key=INS;}
					case '3':
						if((key=super.read())=='~') { key=DEL;}
				}
			}
		}
		return key; 
	}
 
	@Override
	public String readLine() throws IOException{
		int caracter=0;
		while(caracter!=ENTER) {
			caracter=this.read(); //No ponemos super porque sinó no usaríamos nuestro método "read()"
			switch(caracter) { //Primero vemos si es una secuencia de escape
				case RIGHT:
					this.linea.right();
				break;
				case LEFT:
					this.linea.left();
				break;
				case INS:
					this.linea.changeInsert();
				break;
				case DEL:
					this.linea.delete();
				break;
				case HOME:
					this.linea.home();
				break;
				case END:
					this.linea.end();
				break;
				case BACKSPACE:
					this.linea.backspace();
				break;
				case ESC_I:
					System.out.println("Invalid input");
				break;
				default: //Si no es una secuencia de escape supondremos que es un caracter
					this.linea.addChar(caracter);
				break;
			}
		}
		return this.linea.toString();			
	}



}
