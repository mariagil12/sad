package no_mvc;

import java.util.*;

	public class Line{
		ArrayList<Character> lineBuffer;
		Boolean insert;
		private int cursor;
		
		public Line() {
			this.insert = false;
			this.lineBuffer = new ArrayList<>();
			this.cursor = 0;
		}
		
		public int getPos() {
			return this.cursor;
		}
		
		public boolean getInsert() {
			return this.insert;
		}
		
		public char getLastChar() {
			return lineBuffer.get(lineBuffer.size());
		}
		
		public String toString() {
			return lineBuffer.toString();
		}
		
		public void removeChar(int pos) {
			lineBuffer.remove(pos);
		}
		
		public void addChar(char a) {
			if (insert) {
				if (cursor < lineBuffer.size()) {
					lineBuffer.set(cursor, a);
				} else {
					lineBuffer.add(cursor, a);
				}
			} else {
				int len = lineBuffer.size();
				if (cursor < len) {
					for(int i=len; i>cursor; i--) {
						lineBuffer.add(i,lineBuffer.get(i-1));
						lineBuffer.remove(i-1);
					}
				}
				lineBuffer.add(cursor,a);
			}
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
			if (cursor == lineBuffer.size()) {
				lineBuffer.remove(lineBuffer.size());
			}
		}
		
		public void backspace() {
			if ((cursor <= lineBuffer.size()) && (cursor >= 1) && (lineBuffer.size() > 0)) {
				lineBuffer.remove(cursor-1);
				left();
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

