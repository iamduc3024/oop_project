package main;

import entities.character.Bomber;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxSreenRow = 13;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxSreenRow * tileSize;

    public final int maxWorldCol = 31;
    public final int maxWorldRow = 13;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileManager = new TileManager(this);

    Thread gameThread;

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    KeyHandler keyHandler = new KeyHandler();

    public Bomber bomber = new Bomber(this, this.keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of this class
        //this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//if set to true, all the drawing from this component will be done is an offscreen painting buffer
        this.addKeyListener(keyHandler);
        this.setFocusable(true);// với hàm này, gamepanel có thể tập trung để nhân được key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60; //khoảng thời gian giữa 2 lần vẽ
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            //1 UPDATE: update information such as characters position
            update();

            //2 DRAW: draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();//còn bao nhiêu thời gian cho tới lần vẽ tiếp
                remainingTime /= 1000000;//do sleep chỉ chấp nhận tham số đơn vị milisecond
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        bomber.update();
    }

    public void paintComponent(Graphics g) { //graphics như kiểu 1 cái bút
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);//nhớ render tile lên trươc khi render player, vì nếu render player lên trước thì khi render tile thì tile sẽ đè lên player làm cho mình không nhìn thấy player
        bomber.draw(g2);
        g2.dispose();//xóa bối cảnh đồ họa này và giải phóng bất kì tài nguyên bộ nhớ nào mà nó đang sử dụng
    }
}