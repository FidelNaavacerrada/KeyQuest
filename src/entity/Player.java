package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{

    GamePanel gp;
    GamePanel.AL keyH;
    public final int screenX,screenY;
    int hasKey = 0;

    /*
    int xVelocity;
    int yVelocity;
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
        //Object interaction when pressing E
        int objIndex = gp.collisionManager.checkObject(this);
        if(keyH.ePressed){
            try{
                pickUpObj(objIndex);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void pickUpObj(int i) throws IOException {
        if(i!=100){

            String objName = gp.obj[i].name;

            switch(objName){
            case "Key":
                hasKey++;
                gp.obj[i]=null;
                System.out.println("Key nº"+hasKey);
                break;
            case "Door":
                if(hasKey>0){
                    gp.obj[i].collision = false;
                    gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_open.png"));
                    hasKey--;
                }
                break;
            }
        }
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1-boy.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2-boy.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1-boy.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2-boy.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1-boy.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2-boy.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1-boy.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2-boy.png"));
            standing = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-boy.png"));

        }catch(IOException e) {
            throw new RuntimeException(e);
        }

    }
    /*
    public void keyPressed(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){

    }

    public void setXDirection(int xDirection){

    }
    public void setYDirection(int yDirection){

    }
    public void move(){

    }

     */
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
