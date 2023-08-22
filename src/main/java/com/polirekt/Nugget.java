package com.polirekt;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Color;

public class Nugget extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public int x,y;
    
    public Nugget(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
		generateNewCoords();
	}

    public void tick() {
    }

    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
		g2d.setColor(Color.red);
		g2d.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);	
    }

    public void generateNewCoords() {
        Random rand = new Random();
        x = rand.nextInt(gp.maxWorldCol - 0) + 0;
        y = rand.nextInt(gp.maxWorldRow - 0) + 0;
		worldX = gp.tileSize * x;
		worldY = gp.tileSize * y;
    }
}
