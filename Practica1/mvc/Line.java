package mvc;

import java.util.*;

//Model
@SuppressWarnings("deprecation")
public class Line extends Observable{
	
	ArrayList<Integer> buf;
	private int pos;
	private int len;
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
	
	public int getPos() {
		return this.pos;
	}
	
	public int getLength() {
		return this.str.length();
	}
	
	public Boolean getInsert() {
		return this.ins;
	}
	
