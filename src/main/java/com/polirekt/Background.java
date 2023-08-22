package com.polirekt;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Background {
    GamePanel gp;
    
    public Background(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2d) {

		int col = 0;
		int row = 0;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f));
	    g2d.setColor(Color.LIGHT_GRAY);
        while ( col < gp.maxScreenCol && row < gp.maxScreenRow) {
           g2d.drawRect(gp.tileSize*col,gp.tileSize*row,gp.tileSize,gp.tileSize);
            if (col < gp.maxScreenCol - 1) {
               col++;
            } else {
                col = 0;
                row++;
            }
        }
    }
    
}
