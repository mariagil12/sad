import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Meal {
	private int maxX = 790; //para que no se cree en el borde
	private int maxY = 562; //restamos la longitud de la barra de la frame para que no se cree ahi
	private Random random;
	private Point food;
	
	public Meal() {
		this.random = new Random();
		this.food = new Point();
	}
	
	public void newFood() {
		food.x = 5+random.nextInt(maxX);
		food.y = 33 + random.nextInt(maxY);
	}
	
	public void drawFood(Graphics g) {
		g.create();
		g.setColor(Color.RED);
		// g.drawOval(food.x, food.y, 10, 10);
		g.fillOval(food.x, food.y, 10, 10);
	}
	
	public Point getFood() {
		return food;
	}
}
