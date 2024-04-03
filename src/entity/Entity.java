package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    //Abstract entity class, used for Player, NPC, Enemies

    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, standing;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle realArea = new Rectangle(0,0,48,48);
    public int realAreaDefX, realAreaDefY;
    public boolean collisionOn = false;
    public GamePanel gp;
    public boolean isMoving;

    public Entity(GamePanel gp){
        this.gp=gp;
    }

    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res/"+imageName+".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }
    public void draw(Graphics2D g2){
        //Entity draw class, will be used by NPC and Enemy, mixes Player and SuperObject draw methods

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            BufferedImage image = null;
            switch(direction){
                case "up":
                    if(spriteNumber==1)
                        image = up1;
                    if(spriteNumber==2)
                        image = up2;
                    break;
                case "down":
                    if(spriteNumber==1)
                        image = down1;
                    if(spriteNumber==2)
                        image = down2;
                    if(!isMoving){
                        image=standing;
                    }
                    break;
                case "left":
                    if(spriteNumber==1)
                        image = left1;
                    if(spriteNumber==2)
                        image = left2;
                    break;
                case "right":
                    if(spriteNumber==1)
                        image = right1;
                    if(spriteNumber==2)
                        image = right2;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
