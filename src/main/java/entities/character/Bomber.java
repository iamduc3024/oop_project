package entities.character;

import entities.Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomber extends Entity {
    GamePanel gamePanel;

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Bomber(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        //Set v trí default của bomberman
        screenX = /*gamePanel.screenWidth / 2 - gamePanel.tileSize / 2*/ gamePanel.tileSize;
        screenY = /*gamePanel.screenHeight / 2 - gamePanel.tileSize / 2*/ gamePanel.tileSize;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 33;
        solidArea.height = 47;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize;
        speed = 1;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            playerUp = ImageIO.read(getClass().getResourceAsStream("/player/player_up.png"));
            playerUp1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            playerUp2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            playerDown = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
            playerDown1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            playerDown2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            playerRight = ImageIO.read(getClass().getResourceAsStream("/player/player_right.png"));
            playerRight1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            playerRight2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            playerLeft = ImageIO.read(getClass().getResourceAsStream("/player/player_left.png"));
            playerLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            playerLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed == true || keyHandler.downPressed == true
                || keyHandler.rightPressed == true || keyHandler.leftPressed == true
                || keyHandler.spacePressed == true) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            } else if (keyHandler.spacePressed) {
                direction = "space";
            }

            //check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //if collision is false, player can move

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }

            //Đoạn này để khi mình không bấm di chuyển thì bomberman sẽ đứng hình tại ảnh gần nhất, không có đoạn này thì khi không di chuyển nhưng bomberman vẫn hoạt động
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 0 || spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 1 || spriteNumber == 2) {
                    spriteNumber = 0;
                } else if (spriteNumber == 2 || spriteNumber == 0) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 0) {
                    image = playerUp1;
                }
                if (spriteNumber == 1) {
                    image = playerUp1;
                }
                if (spriteNumber == 2) {
                    image = playerUp2;
                }
                break;
            case "down":
                if (spriteNumber == 0) {
                    image = playerDown;
                }
                if (spriteNumber == 1) {
                    image = playerDown1;
                }
                if (spriteNumber == 2) {
                    image = playerDown2;
                }
                break;
            case "right":
                if (spriteNumber == 0) {
                    image = playerRight;
                }
                if (spriteNumber == 1) {
                    image = playerRight1;
                }
                if (spriteNumber == 2) {
                    image = playerRight2;
                }
                break;
            case "left":
                if (spriteNumber == 0) {
                    image = playerLeft;
                }
                if (spriteNumber == 1) {
                    image = playerLeft1;
                }
                if (spriteNumber == 2) {
                    image = playerLeft2;
                }
                break;
            case "space":
                if (spriteNumber == 0) {
                    image = bomb;
                }
                if (spriteNumber == 1) {
                    image = bomb1;
                }
                if (spriteNumber == 2) {
                    image = bomb2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);//draw image tại vị trí (x, y) với kích cỡ (tileSize, tileSize)
    }
}