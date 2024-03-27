package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Entity{

    GamePanel gp;
    GamePanel.AL keyH;
    int xVelocity;
    int yVelocity;
    int health;
    int lives;

    public Player(GamePanel gp, GamePanel.AL keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 5;

        direction = "down";
    }

    public void update(){
        if(keyH.downPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNumber==1)
                    spriteNumber=2;
                else if(spriteNumber==2)
                    spriteNumber=1;
                spriteCounter=0;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
