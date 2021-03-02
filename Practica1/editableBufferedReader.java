import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;

public class editableBufferedReader extends BufferedReader {
	protected void setRaw() {
		try{
			String[] cmd = {"/bin/sh","-c", "stty -echo raw </dev/tty"};
			Runtime.getRuntime().exec(cmd);
		} catch ( IOException e) {
			e.printStackTrace();;
		}
	}

	protected void unsetRaw() {
		try{
			String[] cmd = {"/bin/sh","-c", "stty -raw echo </dev/tty"};
			Runtime.getRuntime().exec(cmd);
		} catch ( IOException e) {
			e.printStackTrace();;
		}
	}
	
