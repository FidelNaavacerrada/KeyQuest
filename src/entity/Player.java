package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{

    GamePanel gp;
    GamePanel.AL keyH;
    public final int screenX,screenY;
    public int hasKey = 0;

    /*
    int health;
    int lives;
     */

    public Player(GamePanel gp, GamePanel.AL keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.SCREEN_WIDTH/2 - (gp.tileSize/2);
        screenY = gp.SCREEN_HEIGHT/2 - (gp.tileSize/2);

        realArea = new Rectangle(8,16,32,32);
        realAreaDefX = realArea.x;
        realAreaDefY = realArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 100;
        worldY = 100;
        speed = 5;

        direction = "down";
    }
    public void update(){
        if(keyH.downPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            if(keyH.downPressed == true){
                direction = "down";
            }
            if(keyH.leftPressed == true){
                direction = "left";
            }
            if(keyH.rightPressed == true){
                direction = "right";
            }
            //check tile collision
            collisionOn = false;
            gp.collisionManager.checkTile(this);

            int objIndex = gp.collisionManager.checkObject(this);
            try{
                pickUpObj(objIndex);
            }catch (Exception e){
                e.printStackTrace();
            }

            //if !collisionOn, player moves
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
    public void pickUpObj(int i) throws IOException{
        if(i!=100){

            String objName = gp.obj[i].name;
            switch(objName){
            case "Key":
                hasKey++;
                gp.obj[i]=null;
                gp.ui.showMessage("+1 Key");
                break;
            case "Door":
                if(hasKey>0&& keyH.ePressed){
                    gp.obj[i].collision = false;
                    gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_open.png"));
                    gp.ui.showMessage("Door Opened");
                    hasKey--;
                }
                else{
                    if(gp.obj[i].collision == true)
                        gp.ui.showMessage("You need a Key!");
                }
                break;
            }
        }
    }
    public void getPlayerImage(){

        up1 = setup("up1-boy");
        up2 = setup("up2-boy");
        down1 = setup("down1-boy");
        down2 = setup("down2-boy");
        left1 = setup("left1-boy");
        left2 = setup("left2-boy");
        right1 = setup("right1-boy");
        right2 = setup("right2-boy");
        standing = setup("standing-boy");

    }
    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res/player/"+imageName+".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics g2){
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
