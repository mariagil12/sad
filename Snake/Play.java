package sad_Snake;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Play extends JFrame implements KeyListener {
	public static final int WINDOWWIDTH=800;
	public static final int WINDOWHEIGHT=600;
	public static final int MINX=0;
	public static final int MINY=28;
	public static final int TIME=10;
	
	private Snake snake;
	private Meal meal;
	private int score;
	private long goal;
	private int timePassed;
	private int i=0;
	private int j=0;
	private int k=0;
	private boolean t=true;
	private boolean pressedMenu=false;
	private boolean pressedColor=false;
	private boolean pressedLose=false;
	private boolean menu=true;
	private boolean colors=false;
	private boolean play=false;
	private boolean first=true;
	private boolean lose=false;
		
	public static void main (String[] args) {
		new Play();
	}
	
	public Play() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
		this.setResizable(false);
		this.setLocation(350,100);
		this.setVisible(true);
		
		this.createBufferStrategy(2);	// Buscar info
		this.addKeyListener(this);
		
		while(true) {
			game();
		}
	}
	
	private void game() {
		if (first) {
			initializeObjects(Snake.GREEN);
			first=!first;
		}
		
		while(menu) {
			showMenu(i);
			sleepMenu();
			if (pressedMenu) {
				menu=false;
			}
			pressedMenu=false;
		}
		switch(i) {
		case 0:
			play=true;
			playSnake();
			play=false;
			break;
		case 1:
			colors=true;
			while(colors) {
				showColors(j,k);
				sleepMenu();
				if(pressedColor) { 
					colors=false;
					break;
				}
				pressedColor=false;	
			}
			colors=false;
			break;
		case 2:
			System.exit(0);
			break;
		}
	}
	
	private void playSnake() {
		initializeObjects(snake.color);
		while(!lose) {
			play();
			sleep();
		}
		while(lose) {
			showLose(t);
			sleepMenu();
			if(pressedLose) {
				lose=false;
			}
			pressedLose=false;
		}
	}
	
	private void initializeObjects(int c) {
		snake = new Snake(c);
		meal = new Meal();
		meal.newFood();
		score = 0;
	}
	
	private void play() {
		snake.moveSnake();
		checkCollition();
		showDraw();
	}
	
	private void showColors(int j, int k) {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();			
			g.setColor(Color.PINK);
			g.fillRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			
			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 66));
			g.drawString("COLORS", 280, 150);
			g.setColor(Color.BLACK);
			g.fillRect(125, 200, 100, 100);
			g.setColor(Color.BLUE);
			g.fillRect(275, 200, 100, 100);
			g.setColor(Color.CYAN);
			g.fillRect(425, 200, 100, 100);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(575, 200, 100, 100);
			g.setColor(Color.GRAY);
			g.fillRect(125, 330, 100, 100);
			g.setColor(Color.GREEN);
			g.fillRect(275, 330, 100, 100);
			g.setColor(Color.MAGENTA);
			g.fillRect(425, 330, 100, 100);
			g.setColor(Color.ORANGE);
			g.fillRect(575, 330, 100, 100);
			g.setColor(Color.PINK);
			g.fillRect(125, 460, 100, 100);
			g.setColor(Color.RED);
			g.fillRect(275, 460, 100, 100);
			g.setColor(Color.WHITE);
			g.fillRect(425, 460, 100, 100);
			g.setColor(Color.YELLOW);
			g.fillRect(575, 460, 100, 100);
			g.setColor(Color.BLACK);
			switch(j) {
			case 0:
				switch(k) {
				case 0:
					g.drawRect(122, 197, 106, 106);
					break;
				case 1:
					g.drawRect(122, 327, 106, 106);
					break;
				case 2:
					g.drawRect(122, 457, 106, 106);
					break;
				}
				break;
			case 1:
				switch(k) {
				case 0:
					g.drawRect(272, 197, 106, 106);
					break;
				case 1:
					g.drawRect(272, 327, 106, 106);
					break;
				case 2:
					g.drawRect(272, 457, 106, 106);
					break;
				}
				break;
			case 2:
				switch(k) {
				case 0:
					g.drawRect(422, 197, 106, 106);
					break;
				case 1:
					g.drawRect(422, 327, 106, 106);
					break;
				case 2:
					g.drawRect(422, 457, 106, 106);
					break;
				}
				break;
			case 3:
				switch(k) {
				case 0:
					g.drawRect(572, 197, 106, 106);
					break;
				case 1:
					g.drawRect(572, 327, 106, 106);
					break;
				case 2:
					g.drawRect(572, 457, 106, 106);
					break;
				}
				break;
			}
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void showMenu(int i) {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();			
			g.setColor(Color.PINK);
			g.fillRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			
			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 66));
			g.drawString("SNAKE", 280, 150);
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 46));
			g.drawString("1. Play", 200, 250);
			g.drawString("2. Change snake color", 200, 350);
			g.drawString("3. Exit", 200, 450);
			switch(i) {
			case 0:
				g.setColor(Color.RED);
				g.fillOval(170, 230, 15, 15);
				break;
			case 1:
				g.setColor(Color.RED);
				g.fillOval(170, 330, 15, 15);
				break;
			case 2:
				g.setColor(Color.RED);
				g.fillOval(170, 430, 15, 15);
				break;
			}
			
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void showDraw() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			
			meal.drawFood(g);
			snake.drawSnake(g,snake.color);
			showScore(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void showLose(boolean t) {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			
			g.setColor(Color.PINK);
			g.fillRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(MINX, MINY, WINDOWWIDTH, WINDOWHEIGHT);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 86));
			g.drawString("YOU LOSE :(", 150, 250);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 56));
			g.drawString("Score: " + score, 300, 150);
			g.setFont(new Font("Arial", Font.BOLD, 36));
			g.drawString("Options:", 125, 350);
			g.drawString("PLAY AGAIN", 125, 400);
			g.drawString("BACK TO MENU", 125, 450);
			if(t) {
				g.setColor(Color.RED);
				g.fillOval(100, 380, 15, 15);
			} else {
				g.setColor(Color.RED);
				g.fillOval(100, 430, 15, 15);
			}
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void checkCollition() {	
		if ((snake.getSnake().get(0).x >= (meal.getFood().x-9) && snake.getSnake().get(0).x <= (meal.getFood().x+9)) && (snake.getSnake().get(0).y >= (meal.getFood().y-9) && snake.getSnake().get(0).y <= (meal.getFood().y+9))) {
			snake.grow();
			meal.newFood();
			score += 10;
		}
		if (snake.getSnake().get(0).x<7 || snake.getSnake().get(0).y<26 || snake.getSnake().get(0).x>795 || snake.getSnake().get(0).y>591) {
			lose=true;
		}
		for (int n=1; n<snake.getSnake().size(); n++) {
			if(snake.getSnake().get(0).equals(snake.getSnake().get(n)) && snake.getSnake().size()>4) {
				lose=true;
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
	
	private void sleepMenu() {
		goal = (System.currentTimeMillis()+TIME);
		while (System.currentTimeMillis()<goal) {
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				if (menu) {
					i=i+2;
					i=i%3;
				} else if(play==true){
					snake.direction("UP");
				} else if(colors) {
					k=k+2;
					k=k%3;
				}
				if(lose) {
					t=!t;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (menu) {
					i ++;
					i=i%3;
				} else if(colors) {
					k++;
					k=k%3;
				} else if (play){
					snake.direction("DOWN");
				}
				if(lose) {
					t=!t;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(colors) {
					j++;
					j=j%4;
				} else if(play) {
					snake.direction("RIGTH");
				}
				break;
			case KeyEvent.VK_LEFT:
				if(colors) {
					j=j+3;
					j=j%4;
				} else if(play) {
					snake.direction("LEFT");
				}
				break;
			case KeyEvent.VK_E:
				System.exit(0);
				break;
			case KeyEvent.VK_SPACE:
				snake.pause = !snake.pause;
				break;
			case KeyEvent.VK_ENTER:
				if(colors) {
					switch(j) {
					case 0:
						switch(k) {
						case 0:
							snake.color=snake.BLACK; 
							break;
						case 1:
							snake.color=snake.GRAY; 
							break;
						case 2:
							snake.color=snake.PINK; 
							break;
						}
						break;
					case 1:
						switch(k) {
						case 0:
							snake.color=snake.BLUE;
							break;
						case 1:
							snake.color=snake.GREEN;
							break;
						case 2:
							snake.color=snake.RED;
							break;
						}
						break;
					case 2:
						switch(k) {
						case 0:
							snake.color=snake.CYAN;
							break;
						case 1:
							snake.color=snake.MAGENTA;
							break;
						case 2:
							snake.color=snake.WHITE;
							break;
						}
						break;
					case 3:
						switch(k) {
						case 0:
							snake.color=snake.DARKGRAY;
							break;
						case 1:
							snake.color=snake.ORANGE;
							break;
						case 2:
							snake.color=snake.YELLOW;
							break;
						}
						break;
					}
					menu=true;
					i=0;
					pressedColor=true;	
					return;
				} else if(menu) {
					pressedMenu=true;
				} else if(lose) {
					pressedLose=true;
					if(t) {
						i=0;
						play=true;
						menu=false;
					} else {
						menu=true;
						i=0;
					}
					t=true;
				}
		}
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override 
	public void keyTyped(KeyEvent e) {
		
	}
}
