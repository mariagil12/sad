package sad_Snake;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Play extends JFrame implements KeyListener {
	private int windowWidth = 800;
	private int windowHeight = 600;
	private Snake snake;
	private Meal meal;
	private int score;
	private long goal;
	private int timePassed = 400;
	
	private int maxX = 800;
	private int maxY = 600;
	private int minX = 0;
	private int minY = 0;
	
	public static void main (String[] args) {
		// modificar para las diferentes versiones de juego i hacer tambien menu
		new Play();
	}
	
	public Play() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowWidth, windowHeight);
		this.setResizable(false);
		this.setLocation(100,100);
		this.setVisible(true);
		
		this.createBufferStrategy(2);
		this.addKeyListener(this);
		
		initializeObjects();
		
		while(true) {
			play();
			sleep();
		}
	}
	
	private void initializeObjects() {
		snake = new Snake();
		snake.grow();
		meal = new Meal();
		meal.newFood();
		score = 0;
	}
	
	private void play() {
		snake.moveSnake();
		System.out.println("3");
		checkCollition();
		showDraw();
	}
	
	private void showDraw() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			g.setColor(Color.BLACK);
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(minX, minY, maxX, maxY);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, maxX, maxY);
			
			meal.drawFood(g);
			snake.drawInitialSnake(g);
			showScore(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void checkCollition() {
		System.out.println("4");
		System.out.println(snake.getSnake().get(0).x);
		System.out.println(snake.getSnake().get(0).y);
		// cambien sense moure la snake (???)
		if (snake.getSnake().get(0).equals(meal.getFood())) {
			meal.newFood();
			snake.grow();
			score += 10;
			System.out.println("1");
		}
		if (snake.getSnake().get(0).x<1 || snake.getSnake().get(0).y<1) {
			// || snake.getSnake().get(0).x>699 || snake.getSnake().get(0).y<1 || snake.getSnake().get(0).y >599
			initializeObjects();
			System.out.println("5");
			//sempre entra aqui, nose whu?????
		}
		for (int n=1; n<snake.getSnake().size(); n++) {
			System.out.println("6");
			if(snake.getSnake().get(0).equals(snake.getSnake().get(n)) && snake.getSnake().size()>2) {
				initializeObjects();
				System.out.println("7");
			}
		}
	}
	
	private void showScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Score: " + score, 20, 50);
	}
	
	private void sleep() {
		goal = (System.currentTimeMillis()+timePassed);
		while(System.currentTimeMillis()<goal) {
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				snake.direction("UP");
				break;
			case KeyEvent.VK_DOWN:
				snake.direction("DOWN");
				break;
			case KeyEvent.VK_RIGHT:
				snake.direction("RIGTH");
				break;
			case KeyEvent.VK_LEFT:
				snake.direction("LEFT");
				break;
			case KeyEvent.VK_E:
				System.exit(0);
		}
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override 
	public void keyTyped(KeyEvent e) {
		
	}
}
