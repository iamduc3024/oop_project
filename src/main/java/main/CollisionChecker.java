package main;

import entities.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {//Entity mà không phải Player vì sau này Enemy cũng cần check collision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];//check vị trí ngoài cùng bên trái và trên cùng của entity
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];//check vị trí ngoài cùng bên phải và trên cùng của entity
                if (gamePanel.tileManager.tile[tileNum1].collision == true
                        || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];//check vị trí ngoài cùng bên trái và trên cùng của entity
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];//check vị trí ngoài cùng bên phải và trên cùng của entity
                if (gamePanel.tileManager.tile[tileNum1].collision == true
                        || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];//check vị trí ngoài cùng bên trái và trên cùng của entity
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];//check vị trí ngoài cùng bên phải và trên cùng của entity
                if (gamePanel.tileManager.tile[tileNum1].collision == true
                        || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];//check vị trí ngoài cùng bên trái và trên cùng của entity
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];//check vị trí ngoài cùng bên phải và trên cùng của entity
                if (gamePanel.tileManager.tile[tileNum1].collision == true
                        || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
