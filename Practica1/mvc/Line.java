package mvc;

import java.util.*;

//Model
@SuppressWarnings("deprecation")
public class Line extends Observable{
	public static final String BACKSPACE = "\033[D"+"\033[3~";
	public static final String CSI = "\033[";
	public static final String RIGHT = "\033[C";
	public static final String LEFT = "\033[D";
	public static final String HOME = "\033[H";
	public static final String END = "\033[F";
	public static final String INS = "\033[@";
	public static final String DEL = "\033[P";
	
	
	/**
	 * CÓDIGO XTERM
	 * CSI == ESC [
	 * right: 		CSI C
	 * left: 		CSI D
	 * home:		CSI H
	 * end: 		CSI F
	 * insert:		CSI @ -> Inserta 1 carácter en blanco (Ps=1)
	 * delete:		CSI P -> Borra 1 carácter (Ps=1)
	 * backspace:   0x7F -> 127
	 * ESC:			\033
	 **/
	
	ArrayList<Integer> buf;
	private int pos;
	private boolean ins;
	private String[] action;
	private StringBuilder str;
	
	public Line() {
		this.buf=new ArrayList<>();
		this.str=new StringBuilder();
		this.pos=0;
		this.len=0;
		this.ins=false;
		this.action=new String[2];
	}
	
	public StringBuilder getLine() {
		return this.str;
	}
	
	public void changeInsert() {
		ins=!ins;
	}
	
	public int getPos() {
		return this.pos;
	}

	public Boolean getInsert() {
		return this.ins;
	}
	
	public int getLength() {
		return this.str.length();
	}
	
	public void invalidInput() {
		action[0]="false";
		this.setChanged();
		this.notifyObservers(action);
	}
	
	public void addCar(char car) {
		String str=""+car;
		if(getInsert()) { //Si está en modo insert
			this.str.replace(this.pos, this.pos+1,str); 
			action[0]="true";
			action[1]=str;
			this.setChanged();
			this.notifyObservers(action);
		}else { //Si no está en modo insert
			this.str=this.str.insert(this.pos,car);
			action[0]="true";
			action[1]=INS+car;
			this.setChanged();
			this.notifyObservers(action);
		}
		this.pos++;
	}
	
	public void backspace() {
		if(this.getLength()>0 && this.pos>0) {
			this.str.deleteCharAt(this.pos-1);
			this.pos--;
			action[0]="true";
			action[1]=BACKSPACE;
			this.setChanged();
			this.notifyObservers(action);
		}
	}
	
	public void supr() {
		if(this.pos<this.getLength()) {
			this.str.deleteCharAt(this.pos);
			action[0]="true";
			action[1]=DEL;
			this.setChanged();
			this.notifyObservers(action);
		}
	}
	
	public void home() {
		this.pos=0;
		action[0]="true";
		action[1]=DEL;
		this.setChanged();
		this.notifyObservers(action);
	}
	
	public void end() {
		int dif=this.getLength()-this.pos;
		this.pos=this.getLength();
		action[0]="true";
		action[1]=CSI+dif+"C";
		this.setChanged();
		this.notifyObservers(action);
	}
	
	public void right() {
		if(this.pos<this.getLength()) {
			this.pos++;
			action[0]="true";
			action[1]=RIGHT;
			this.setChanged();
			this.notifyObservers(action);
		}
	}
	
	public void left() {
		if(this.pos>0) {
			this.pos--;
			action[0]="true";
			action[1]=LEFT;
			this.setChanged();
			this.notifyObservers(action);
		}
	}
}
