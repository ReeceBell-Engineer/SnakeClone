package com.polirekt;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class GameOverMenu {

    GamePanel gp;
    KeyHandler keyH;

    public GameOverMenu(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void tick() {
        if (keyH.enterPressed) {
            gp.player.resetGame();
            gp.gameState = GamePanel.GameState.RUNNING;
            
            keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        String gameOverTitle = "Game Over";
        String gameScore = "Score: " + gp.player.score;
        g2d.drawString(gameOverTitle, gp.screenWidth/2 - 120, gp.screenHeight/2 - 300);
        g2d.drawString(gameScore, gp.screenWidth/2 - 90, gp.screenHeight/2 - 200);
        g2d.drawString("Press Enter to start again", gp.screenWidth/2 - 210, gp.screenHeight/2 - 100);
    }
}
