package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    /**
     * Entity class, used as base class for the different
     * characters in the game, including Player, Enemy and NPCs.
     */

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
    public int timeForMovement=0;
    public String totalDialogues[]=new String[20];
    int dialogueIndex = 0;
    public int maxLife, life;

    public Entity(GamePanel gp){

        this.gp=gp;
    }

    public void setAction() {}
    public void setDialogue() {}
    public void speak() {
        /**
         * This method controls the Entity interaction when
         * in dialogue with the player. Makes sure that they
         * always show some dialogue and directs them towards
         * the player when speaking.
         */

        //If the dialog reaches the end it repeats it
        if(totalDialogues[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.ui.dialogue=totalDialogues[dialogueIndex];
        dialogueIndex++;

        //Npc faces the player when engaged in dialogue
        switch(gp.player.direction){
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
        }
    }
    public void update(){
        /**
         * Update method used by all entities except Player, which has its own
         * update method.
         * Calls for collisionManager.checkTile(), checkObject() and checkPlayer(),
         * the different methods created to control entity collision.
         */

        setAction();

        collisionOn=false;
        gp.collisionManager.checkTile(this);
        gp.collisionManager.checkObject(this);
        gp.collisionManager.checkPlayer(this);

        if(isMoving){
            if(!collisionOn){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNumber==1)
                    spriteNumber=2;
                else if(spriteNumber==2)
                    spriteNumber=1;
                spriteCounter=0;
            }
        }
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
