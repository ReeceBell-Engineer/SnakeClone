package com.polirekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Screen Settings
	public final int originalTileSize = 8; // 8 x 8 tiles
	public final int scale = 3; // 3 may be more suitable 
	
	public final int tileSize = originalTileSize * scale;
	
	public final int maxScreenCol = 40;
	public final int maxScreenRow = 40;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	// World settings
	public final int maxWorldCol = 40;
	public final int maxWorldRow = 40;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	final int FPS = 60;
	
	public GameState gameState = GameState.START;
	private Thread gameThread;

	KeyHandler keyH = new KeyHandler();

	StartMenu startMenu = new StartMenu(this,keyH);
	GameOverMenu gameOverMenu = new GameOverMenu(this,keyH);

	Background bgGrid = new Background(this);
	Tail tail = new Tail(this);
	Player player = new Player(this,keyH,tail);
	Nugget nugget = new Nugget(this,keyH);

	public enum GameState {
		START, RUNNING, PAUSED, GAMEOVER
	}
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		System.out.println("game loop running");
		System.out.println("gameThread: " + gameThread.getName());
		double frameInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int frameCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / frameInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				tick();
				repaint();
				delta--;
				frameCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + frameCount);
				frameCount = 0;
				timer = 0;
			}
		}
	}
	
	public void tick() {
		if (gameState == GameState.START) {
			startMenu.tick();
		} else if (gameState == GameState.RUNNING) {
			player.tick();
		} else if (gameState == GameState.PAUSED) {

		} else if (gameState == GameState.GAMEOVER) {
			gameOverMenu.tick();
		}
		System.out.println("gameState: " + gameState);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
    
		Graphics2D g2d = (Graphics2D) g;

		if (gameState == GameState.START) {
			startMenu.draw(g2d);
		} else if (gameState == GameState.RUNNING) {
			bgGrid.draw(g2d); // this will display the black background and the grid. This must be first.
			nugget.draw(g2d);
			player.draw(g2d);
			tail.draw(g2d);
		} else if (gameState == GameState.PAUSED) {

		} else if (gameState == GameState.GAMEOVER) {
			gameOverMenu.draw(g2d);
		}



		g2d.dispose();
	}
}
