package entities.Bomb;

import entities.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InvalidObjectException;

public class Bomb extends Entity {
    GamePanel gamePanel;
    
    public void getBombImage() {
        try {
            bomb = ImageIO.read(getClass().getResourceAsStream("/Bomb/bomb.png"));
            bomb1 = ImageIO.read(getClass().getResourceAsStream("/Bomb/bomb_1.png"));
            bomb2 = ImageIO.read(getClass().getResourceAsStream("Bomb/bomb_2.png"));
            bombExploded = ImageIO.read(getClass().getResourceAsStream("/Bomb/bomb_exploded.png"));
            bombExploded1 = ImageIO.read(getClass().getResourceAsStream("/Bomb/bomb_exploded1.png"));
            bombExploded2 = ImageIO.read(getClass().getResourceAsStream("/Bomb/bomb_exploded2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
