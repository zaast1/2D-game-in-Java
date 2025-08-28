package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class gamePanel extends JPanel implements Runnable {
    // screen settings
    final int originalTitleSize = 16; // 16x16 tileSet
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale; // tileset 48x48
    final int maxScreenCol = 16; // vertical tileset map
    final int maxScreenRow = 12; // horizontal tileset map
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // Add this line to define the game's FPS
    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread(this);
    Player player = new Player(this, keyH);

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public gamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread.start();
    }

    @Override
    public void run() {
        // The rest of the code is unchanged...
        double drawInterval = 1000000000.0/FPS; // Corrected calculation
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();
    }
}