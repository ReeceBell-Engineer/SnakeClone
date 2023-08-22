package com.polirekt;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Color;

public class Tail {
    GamePanel gp;
    private int x;
    private int y;

    public Tail(GamePanel gp) {
        this.gp = gp;
    }

    public void tick() {
    }

    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
		g2d.setColor(Color.green);

        for (int i = 1; i < gp.player.playerCoords.size(); i++) {
            x = Integer.parseInt(gp.player.playerCoords.get(i).split(",")[0]);
            y = Integer.parseInt(gp.player.playerCoords.get(i).split(",")[1]);
            g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
        }

	}
}