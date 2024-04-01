package main;

import entity.Entity;
import entity.Player;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(Entity entity){
        int entityLeftX = entity.worldX +entity.realArea.x;
        int entityRightX = entity.worldX +entity.realArea.x+entity.realArea.width;
        int entityTopY = entity.worldY+entity.realArea.y;
        int entityBotY = entity.worldY + entity.realArea.y + entity.realArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBotRow = entityBotY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileMatrix[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileMatrix[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotY+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileMatrix[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileM.mapTileMatrix[entityRightCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileMatrix[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileMatrix[entityLeftCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileMatrix[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileMatrix[entityRightCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity){

        int index = 100;

        for(int i=0;i<gp.obj.length;i++){

            if(gp.obj[i]!=null){
                entity.realArea.x = entity.worldX+entity.realArea.x;
                entity.realArea.y = entity.worldY+entity.realArea.y;

                gp.obj[i].realArea.x = gp.obj[i].x+gp.obj[i].realArea.x;
                gp.obj[i].realArea.y = gp.obj[i].y+gp.obj[i].realArea.y;

                switch(entity.direction){
                    case "up":
                        entity.realArea.y-=entity.speed;
                        if(entity.realArea.intersects(gp.obj[i].realArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(entity instanceof Player){
                                index=i;
                            }
                        }
                        break;
                    case "down":
                        entity.realArea.y+=entity.speed;
                        if(entity.realArea.intersects(gp.obj[i].realArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(entity instanceof Player){
                                index=i;
                            }
                        }
                        break;
                    case "left":
                        entity.realArea.x-=entity.speed;
                        if(entity.realArea.intersects(gp.obj[i].realArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(entity instanceof Player){
                                index=i;
                            }
                        }
                        break;
                    case "right":
                        entity.realArea.x+=entity.speed;
                        if(entity.realArea.intersects(gp.obj[i].realArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(entity instanceof Player){
                                index=i;
                            }
                        }
                        break;
                }
                entity.realArea.x = entity.realAreaDefX;
                entity.realArea.y = entity.realAreaDefY;
                gp.obj[i].realArea.x = gp.obj[i].realAreaDefX;
                gp.obj[i].realArea.y = gp.obj[i].realAreaDefY;
            }
        }
        return index;
    }
}
