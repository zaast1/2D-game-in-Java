package entity;

import main.gamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    gamePanel gp;
    KeyHandler keyH;

    public Player(gamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyH.upPressed) { // Simplified boolean check
            y -= speed;
        } else if (keyH.downPressed) {
            y += speed;
        } else if (keyH.leftPressed) {
            x -= speed;
        } else if (keyH.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.cyan);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
