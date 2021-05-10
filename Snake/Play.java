package sad_Snake;

import java.awt.*;
import java.awt.color.ColorSpace;
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
	private int i=0;
	private int j=0;
	private int k=0;
	private int t=0;
	private boolean presed=false;
	private boolean presed2=false;
	private boolean menu=true;
	private boolean colors=false;
	private boolean play=false;
	private int time = 10;
	private boolean first=true;
	private boolean lose=true;
			
	
	// private int maxX = 800;
	// private int maxY = 600;
	private int minX = 0;
	private int minY = 28;
		
	public static void main (String[] args) {
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
		
		while(true) {
			if (first) {
				initializeObjects();
				first=!first;
			}
			
			while(menu==true) {
				showMenu(i);
				sleepMenu();
				if (presed==true) {
					menu=false;
				}
				presed=false;
			}
			switch(i) {
			case 0:
				System.out.println(snake.color);
				System.out.println('B');
				play=true;
				playSnake();
				play=false;
				break;
			case 1:
				colors=true;
				while(colors=true) {
				//while(i==1) {
					if(presed2) {
						colors=false;
						break;
					}
					showColors(j,k);
					sleepMenu();
				}
				colors=false;
				showMenu(i);
				break;
			case 2:
				// exit
				System.exit(0);
				break;
			}
		}
	}
	
	private void playSnake() {
		//initializeObjects();
		
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
	
	private void showColors(int j, int k) {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();			
			g.setColor(Color.PINK);
			g.fillRect(minX, minY, windowWidth, windowHeight);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, windowWidth, windowHeight);
			
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
			g.fillRect(minX, minY, windowWidth, windowHeight);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, windowWidth, windowHeight);
			
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
			// g.setColor(Color.BLACK);
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(minX, minY, windowWidth, windowHeight);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, windowWidth, windowHeight);
			
			meal.drawFood(g);
			snake.drawSnake(g,snake.color);
			showScore(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void showLose() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(minX, minY, windowWidth, windowHeight);
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, windowWidth, windowHeight);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 36));
			g.drawString("Score: " + score, 100, 150);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 56));
			g.drawString("YOU LOSE :(", 100, 250);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Play again", 100, 350);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Back to menu", 100, 450);
			switch(t) {
			case 0:
				g.setColor(Color.RED);
				g.fillOval(85, 340, 10, 10);
				break;
			case 1: 
				g.setColor(Color.RED);
				g.fillOval(85, 440, 10, 10);
				break;
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
			//initializeObjects();
			lose=true;
			while(lose) {
				showLose();
				sleepMenu();
			}
		}
		for (int n=1; n<snake.getSnake().size(); n++) {
			if(snake.getSnake().get(0).equals(snake.getSnake().get(n)) && snake.getSnake().size()>4) {
				//initializeObjects();
				lose=true;
				while(lose) {
					showLose();
					sleepMenu();
				}
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
		goal = (System.currentTimeMillis()+time);
		while (System.currentTimeMillis()<goal) {
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				if (menu==true) {
					i=i+2;
					i=i%3;
				} else if(play==true){
					snake.direction("UP");
				} else if(colors==true) {
					k=k+2;
					k=k%3;
				} else if(lose) {
					t++;
					t=t%2;
				}
				break;
			case KeyEvent.VK_DOWN:
				//snake.direction("DOWN");
				if (menu==true) {
					i ++;
					i=i%3;
				} else if(colors==true) {
					k++;
					k=k%3;
				} else if (play==true){
					snake.direction("DOWN");
				} else if(lose) {
					t++;
					t=t%2;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(colors==true) {
					j++;
					j=j%4;
				} else if(play==true) {
					snake.direction("RIGTH");
				}
				break;
			case KeyEvent.VK_LEFT:
				if(colors==true) {
					j=j+3;
					j=j%4;
				} else if(play==true) {
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
				//presed2=true;
				//if (i==1) {
				if(colors) {
					switch(j) {
					case 0:
						switch(k) {
						case 0:
							snake.color=0; // black
							break;
						case 1:
							snake.color=1; //gris
							break;
						case 2:
							snake.color=2; // rosa
							break;
						}
						break;
					case 1:
						switch(k) {
						case 0:
							snake.color=3;
							//blau
							break;
						case 1:
							snake.color=4;
							//verd
							break;
						case 2:
							snake.color=5;
							//vermell
							break;
						}
						break;
					case 2:
						switch(k) {
						case 0:
							snake.color=6;
							//cyan
							break;
						case 1:
							snake.color=7;
							//magenta
							break;
						case 2:
							snake.color=8;
							//blanc
							break;
						}
						break;
					case 3:
						switch(k) {
						case 0:
							snake.color=9;
							//grisfosc
							break;
						case 1:
							snake.color=10;
							//tronja
							break;
						case 2:
							snake.color=11;
							//groc
							break;
						}
						break;
					}
					menu=true;
					i=0;
					presed2=true;
					return;
				} else if(menu==true) {
					presed=true;
					menu=false;
				} else if(lose) {
					switch(t) {
					case 0:
						i=0;
						presed=true;
						break;
					case 1:
						menu=true;
						i=0;
						break;
					}
					lose=!lose;
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
