package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;

    public int worldY;

    public int speed;

    public BufferedImage playerUp, playerUp1, playerUp2;
    public BufferedImage playerDown, playerDown1, playerDown2;
    public BufferedImage playerRight, playerRight1, playerRight2;
    public BufferedImage playerLeft, playerLeft1, playerLeft2;

    public BufferedImage bomb, bomb1, bomb2;
    public BufferedImage bombExploded, bombExploded1, bombExploded2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public Rectangle solidArea; //Để lấy ra phần sẽ check va chạm
    public boolean collisionOn = false;
}