import java.io.*;

public class EditableBufferedReader extends BufferedReader{
	
	public static final int ESC_I = 27; //ESC int
	public static final char ESC_C = '\033'; //ESC char
	public static final int RIGHT = 39;
	public static final int LEFT = 37;
	public static final int HOME = 36;
	public static final int END = 35;
	public static final int INS = 45;
	public static final int DEL = 127;
	public static final int BACKSPACE = 8;
	public static final int ENTER = 13;
	
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
	 * https://keycode.info/
	 * right:		39
	 * left: 		37
	 * home:		36
	 * end: 		35
	 * insert:		45
	 * delete:		127
	 * backspace: 	8
	 * ESC: 		27
	 * 
	 * 
	 */
	
	public EditableBufferedReader(Reader in) {
		super(in);
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
 




}
