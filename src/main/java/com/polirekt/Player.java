package com.polirekt;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Player extends Entity {

    GamePanel gp;
	Tail tail;
    KeyHandler keyH;

	public Boolean collision = false;
	public int score = 0;

    public final int screenX;
	public final int screenY;
	
	private int timeCounter = 0;

	LinkedList<String> playerCoords = new LinkedList<>();

    public Player(GamePanel gp, KeyHandler keyH, Tail tail) {
        this.gp = gp;
        this.keyH = keyH;
		this.tail = tail;

        screenX = (gp.maxScreenCol*gp.tileSize)/2 - gp.tileSize;
		screenY = (gp.maxScreenRow*gp.tileSize)/2 - gp.tileSize;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 20;
		worldY = gp.tileSize * 20;
		speed = 1;
		direction = "down";
		timeCounter = 0;
		score = 0;
		playerCoords.addFirst(worldX + "," + worldY);
		collision = false;
	}

    public void tick() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed == true) {
				direction = "up";
                keyH.upPressed = false;
			}
			if (keyH.downPressed == true) {
				direction = "down";
                keyH.downPressed = false;
			}
			if (keyH.leftPressed == true) {
				direction = "left";
                keyH.leftPressed = false;
			}
			if (keyH.rightPressed == true) {
				direction = "right";
                keyH.rightPressed = false;
			}
		}
		timeCounter++;
		// check location against map boundries
		checkCollision();
		// if collision false can move
		if (timeCounter == 15) {
			if (!collision) {
				switch(direction) {
				case "up":
					worldY -= gp.tileSize;
					break;
				case "down":
					worldY += gp.tileSize;
					break;
				case "left":
					worldX -= gp.tileSize;
					break;
				case "right":
					worldX += gp.tileSize;
					break;
				}
				
				playerCoords.addFirst(worldX + "," + worldY);

				for (int i = 1; i < playerCoords.size(); i++) {
					if (playerCoords.get(0).equals(playerCoords.get(i))) {
						System.out.println("Collision");
						gp.gameState = GamePanel.GameState.GAMEOVER;
					}
				}

				if (worldX == gp.nugget.worldX && worldY == gp.nugget.worldY) {
					playerCoords.addFirst(worldX + "," + worldY);
					gp.nugget.generateNewCoords();
					System.out.println("Nugget collected");
					score++;
				}

				playerCoords.removeLast();
				System.out.println(playerCoords);
				timeCounter = 0;
			} else {
				System.out.println("Collision");
				gp.gameState = GamePanel.GameState.GAMEOVER;
			}
		}
        
    }

	public void resetGame() {
		playerCoords.clear();
		setDefaultValues();		
		gp.nugget.generateNewCoords();
	}

	public boolean checkCollision() {
		if (worldX < 0 || worldX > gp.worldWidth - gp.tileSize || worldY < 0 || worldY > gp.worldHeight - gp.tileSize) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
		g2d.setColor(Color.yellow);
		g2d.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);
	}
}