package sad_Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Meal {
	private int maxX = 800;
	private int maxY = 600;
	private Random random;
	private Point food;
	
	public Meal() {
		this.random = new Random();
		this.food = new Point();
	}
	
	public void newFood() {
		food.x = random.nextInt(maxX);
		food.y = random.nextInt(maxY);
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

