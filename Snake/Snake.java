package sad_Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	public static final int BLACK=0;
	public static final int GRAY=1;
	public static final int PINK=2;
	public static final int BLUE=3;
	public static final int GREEN=4;
	public static final int RED=5;
	public static final int CYAN=6;
	public static final int MAGENTA=7;
	public static final int WHITE=8;
	public static final int DARKGRAY=9;
	public static final int ORANGE=10;
	public static final int YELLOW=11;
	public static final int INITIALX=400;
	public static final int INITIALY=300;
	
	private ArrayList<Point> snake = new ArrayList<Point>();
	private int newX;
	private int newY;
	private int oldX;
	private int oldY;
	private Point lastPos;
	private Point aux = new Point();
	public boolean pause = false;
	public int color;
	
	public Snake(int c) {
		this.snake.add(new Point(INITIALX,INITIALY));
		this.color=c;
	}
	
	public ArrayList<Point> getSnake() {
		return snake;
	}
	
	public void drawSnake(Graphics g, int color) {
		switch(color) {
		case BLACK:
			g.setColor(Color.BLACK);
			break;
		case GRAY:
			g.setColor(Color.GRAY);
			break;
		case PINK:
			g.setColor(Color.PINK);
			break;
		case BLUE:
			g.setColor(Color.BLUE);
			break;
		case GREEN:
			g.setColor(Color.GREEN);
			break;
		case RED:
			g.setColor(Color.RED);
			break;
		case CYAN:
			g.setColor(Color.CYAN);
			break;
		case MAGENTA:
			g.setColor(Color.MAGENTA);
			break;
		case WHITE:
			g.setColor(Color.WHITE);
			break;
		case DARKGRAY:
			g.setColor(Color.DARK_GRAY);
			break;
		case ORANGE:
			g.setColor(Color.ORANGE);
			break;
		case YELLOW:
			g.setColor(Color.YELLOW);
			break;
		}
		for(int n = 0; n < snake.size(); n++) {
			g.fillRect(snake.get(n).x, snake.get(n).y, 10, 10);
        }
	}
	
	public boolean allowMove() {
		aux.x = snake.get(0).x + newX;
		aux.y = snake.get(0).y + newY;
		if(snake.size()>1 && snake.get(1).equals(aux)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void moveSnake() {
		if(pause == false) {
			lastPos=snake.get(snake.size()-1);
			if(allowMove()) {
				for(int n=snake.size()-1; n>0; n--) {
					snake.get(n).setLocation(snake.get(n-1));
				}
				snake.get(0).setLocation(aux);
			}
			else {
				for(int n=snake.size()-1; n>0; n--) {
					snake.get(n).setLocation(snake.get(n-1));
				}
				Point newPoint = new Point();
				newPoint.x=snake.get(0).x+oldX;
				newPoint.y=snake.get(0).y+oldY;
				snake.get(0).setLocation(newPoint);
			}
		} else {
			return;
		}
	}
	
	public void direction(String d) {
		oldX=newX;
		oldY=newY;
		switch(d) {
		case "UP":
			newX = 0;
			newY=(-10);
			break;
		case "DOWN":
			newX=0;
			newY=10;
			break;
		case "RIGTH":
			newX=10;
			newY=0;
			break;
		case "LEFT":
			newX=(-10);
			newY=0;
			break;
		}
	}
	
	public void grow() {
		snake.add(new Point(lastPos.x,lastPos.y));
		
	}
}
