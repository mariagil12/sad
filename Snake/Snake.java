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
	
	public Snake() {
		this.snake.add(new Point(initialX,initialY));
	}
	
	public ArrayList<Point> getSnake() {
		return snake;
	}
	
	public void drawSnake(Graphics g) {
		g.setColor(Color.GREEN);
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
