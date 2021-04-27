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
	
	public Snake() {
		this.snake.add(new Point(initialX,initialY));
	}
	
	public ArrayList<Point> getSnake() {
		return snake;
	}
	
	public void drawInitialSnake(Graphics g) {
		g.create(initialX, initialY, 1, 1);
		for (int i=0; i<snake.size(); i++) {
			g.setColor(Color.GREEN);
			g.fillRect(initialX, initialY, 10, 10);
		}
	}
	
	public void drawSnake(Graphics g) {
		g.setColor(Color.GREEN);
		for(int n = 0; n < snake.size(); n++) {
			/*g.create(initialX, initialY, 1, 1);
			g.setColor(Color.GREEN);
			g.fillRect(initialX, initialY, 10, 10);*/
            // g.setColor(Color.GREEN);
            //Point p = snake.get(n);
            //System.out.println(p);
            //System.out.println(p.x);
            //System.out.println(p.y);
            //g.fillRect(p.x, p.y, 10, 10);		// Hemos quitado el p.x*10 a p.x
			g.fillRect(snake.get(n).x, snake.get(n).y, 10, 10);
        }
	}
	
	public void moveSnake() {
		for(int n=snake.size()-1; n>0; n--) {
			snake.get(n).setLocation(snake.get(n-1));
		}
		Point newPoint = new Point();
		newPoint.x = snake.get(0).x + newX;
		newPoint.y = snake.get(0).y + newY;
		snake.get(0).setLocation(newPoint);
		// fins aqui be
	}
	
	public void direction(String d) {
		/*for(int n=snake.size()-1; n>0; n--) {
			snake.get(n).setLocation(snake.get(n-1));
		}ç*/
		switch(d) {
		// aqui entra a tots els casos
		case "UP":
			newX = 0;
			//initialX=0;
			newY=(-1);
			//snake.get(0).y += 1;
			break;
		case "DOWN":
			newX=0;
			newY=1;
			break;
		case "RIGTH":
			newX=1;
			newY=0;
			break;
		case "LEFT":
			newX=(-1);
			newY=0;
			break;
		}
	}
	
	public void grow() {
		snake.add(new Point());
		
	}
}
