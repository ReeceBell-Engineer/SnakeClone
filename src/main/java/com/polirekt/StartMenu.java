package com.polirekt;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

public class StartMenu {

    GamePanel gp;
    KeyHandler keyH;

    public StartMenu(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void tick() {
        if (keyH.enterPressed) {
            gp.gameState = GamePanel.GameState.RUNNING;
            keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2d.drawString("Press Enter to start", gp.screenWidth/2 - 200, gp.screenHeight/2 - 200);
    }
}
