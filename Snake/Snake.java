package sad_Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> snake = new ArrayList<Point>();
	private int initialX = 400;
	private int initialY = 300;
	private int newX;
	private int newY;
	private int oldX;
	private int oldY;
	private Point lastPos;
	private Point aux = new Point();
	public boolean pause = false;
	public int color=4;
	// 0 black, 1 gris, 2 rosa, 3 blau, 4 verd, 5 vermell, 6 cyan, 7 magenta, 8 blanc, 9 gris fosc, 10 tronja, 11 groc
	
	public Snake() {
		this.snake.add(new Point(initialX,initialY));
	}
	
	public ArrayList<Point> getSnake() {
		return snake;
	}
	
	public void drawSnake(Graphics g, int color) {
		switch(color) {
		case 0:
			g.setColor(Color.BLACK);
			break;
		case 1:
			g.setColor(Color.GRAY);
			break;
		case 2:
			g.setColor(Color.PINK);
			break;
		case 3:
			g.setColor(Color.BLUE);
			break;
		case 4:
			g.setColor(Color.GREEN);
			break;
		case 5:
			g.setColor(Color.RED);
			break;
		case 6:
			g.setColor(Color.CYAN);
			break;
		case 7:
			g.setColor(Color.MAGENTA);
			break;
		case 8:
			g.setColor(Color.WHITE);
			break;
		case 9:
			g.setColor(Color.DARK_GRAY);
			break;
		case 10:
			g.setColor(Color.ORANGE);
			break;
		case 11:
			g.setColor(Color.YELLOW);
			break;
		}
		//g.setColor(Color.GREEN);
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
		// aqui entra a tots els casos
		case "UP":
			newX = 0;
			//initialX=0;
			newY=(-10);
			//snake.get(0).y += 1;
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
