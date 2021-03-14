package no_mvc;

import java.util.*;

	public class Line{
		ArrayList<Integer> lineBuffer;
		Boolean insert;
		char lastChar;
		private int length;
		private int cursor;
		
		public Line() {
			this.insert = false;
			this.lineBuffer = new ArrayList<>();
			this.cursor = 0;
			this.length = 0;
		}
		
		public int getPos() {
			return this.cursor;
		}
		
		public boolean getInsert() {
			return this.insert;
		}
		
		public char getLastChar() {
			return lastChar;
		}
		
		public String toString() {
			String str = "";
			int aux = 0;
			for(int i=0; i<lineBuffer.size();i++) {
				aux = lineBuffer.get(i);
				str += (char) aux;
			}
			return str;
		}
		
		public void removeChar(int pos) {
			lineBuffer.remove(pos);
		}
		
		public void addChar(int a) {
			if (insert) {
				if (cursor < lineBuffer.size()) {
					lineBuffer.set(cursor, a);
				} else {
					lineBuffer.add(cursor, a);
				}
			} else {
				length = lineBuffer.size();
				if (cursor < length) {
					for(int i=length; i>cursor; i--) {
						lineBuffer.add(i,lineBuffer.get(i-1));
						lineBuffer.remove(i-1);
					}
				}
				lineBuffer.add(cursor,a);
			}
			lastChar = (char) a;
			cursor ++;
		}
		
		public void changeInsert() {
			insert = !insert;
		}
		
		public void home() {
			cursor = 0;
		}
		
		public void end() {
			cursor = lineBuffer.size();
		}
		
		public void delete() {
			if (cursor == length) {
				lineBuffer.remove(length);
				length --;
			}
		}
		
		public void backspace() {
			if ((cursor <= lineBuffer.size()) && (cursor >= 1) && (lineBuffer.size() > 0)) {
				lineBuffer.remove(cursor-1);
				left();
				length --;
				
			}
		}
		
		public void right() {
			if (cursor < lineBuffer.size()) {
				cursor ++;
			}
		}
		
		public void left() {
			if ((cursor > 0) && (cursor <= lineBuffer.size()) && (lineBuffer.size() > 0)) {
				cursor --;
			}
		}
		
	}

