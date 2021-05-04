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
	private int timePassed;
			
	
	// private int maxX = 800;
	// private int maxY = 600;
	private int minX = 0;
	private int minY = 31;
		
	public static void main (String[] args) {
		// modificar para las diferentes versiones de juego i hacer tambien menu
		new Play();
	}
	
	public Play() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowWidth, windowHeight);
		this.setResizable(false);
		this.setLocation(350,100);
		this.setVisible(true);
		
		this.createBufferStrategy(2);	// Buscar info
		this.addKeyListener(this);
		
		initializeObjects();
		
		while(true) {
			play();
			sleep();
		}
	}
	
	private void initializeObjects() {
		snake = new Snake();
		// snake.grow();			// borrado
		meal = new Meal();
		meal.newFood();
		score = 0;
	}
	
	private void play() {
		snake.moveSnake();
		checkCollition();
		showDraw();
	}
	
	private void showDraw() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			// g.setColor(Color.BLACK);
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(minX, minY, windowWidth, windowHeight);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, windowWidth, windowHeight);
			
			meal.drawFood(g);
			snake.drawSnake(g);
			showScore(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void checkCollition() {	// no revisat
		if ((snake.getSnake().get(0).x >= (meal.getFood().x-9) && snake.getSnake().get(0).x <= (meal.getFood().x+9)) && (snake.getSnake().get(0).y >= (meal.getFood().y-9) && snake.getSnake().get(0).y <= (meal.getFood().y+9))) {
			snake.grow();
			meal.newFood();
			score += 10;
			System.out.println("Comer fruta");
		}
		if (snake.getSnake().get(0).x<7 || snake.getSnake().get(0).y<30 || snake.getSnake().get(0).x>793 || snake.getSnake().get(0).y>585) {
			initializeObjects();
			System.out.println("Fuera margen");
		}
		for (int n=1; n<snake.getSnake().size(); n++) {
			if(snake.getSnake().get(0).equals(snake.getSnake().get(n)) && snake.getSnake().size()>4) {
				initializeObjects();
				System.out.println("Comer snake");
			}
		}
	}
	
	private void showScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Score: " + score, 20, 50);
	}
	
	private void sleep() {
		if (score <= 50) {
			timePassed=60;
		} else if (score <= 100) {
			timePassed=50;
		} else if (score <= 150) {
			timePassed=40;
		} else if (score <= 200) {
			timePassed=30;
		} else if (score <= 250) {
			timePassed=20;
		} else if (score <= 300) {
			timePassed=10;
		} else {
			timePassed=5;
		}
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
