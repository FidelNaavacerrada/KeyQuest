package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{

    GamePanel.AL keyH;
    public final int screenX,screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, GamePanel.AL keyH){
        super(gp);
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

        maxLife=9;//Each life is represented by 1/3 of a heart
        life=maxLife;

        direction = "down";
    }
    public void update(){
        isMoving=false;
        if(keyH.downPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.rightPressed == true){
            isMoving=true;
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

            //check  entity collision
            int npcIndex = gp.collisionManager.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check object collision
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
            /*The following piece of code handles animation of character when moving, switching bewteen
            * the 2 different sprites used for each direction (every 10 frames = 1/6 seconds), and also
            * calling the movement sound effect at the same rate.
            */
            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNumber==1)
                    spriteNumber=2;
                else if(spriteNumber==2)
                    spriteNumber=1;
                gp.soundEffect(1);
                spriteCounter=0;
            }
        }

    }
    public void pickUpObj(int i) throws IOException{
        /**
         * Method that handles player-object interaction
         */
        if(i!=100){

            String objName = gp.obj[i].name;
            switch(objName){
            case "Key":
                hasKey++;
                gp.soundEffect(3);
                gp.obj[i]=null;
                gp.ui.showMessage("+1 Key");
                break;
            case "Door":
                if(hasKey>0&& keyH.ePressed){
                    gp.obj[i].collision = false;
                    gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_open.png"));
                    gp.soundEffect(2);
                    gp.ui.showMessage("Door Opened");
                    hasKey--;
                }
                else{
                    if(gp.obj[i].collision == true)
                        gp.ui.showMessage("You need a Key!");
                }
                break;
            case "healpot":
                gp.player.life+=3;
                gp.soundEffect(4);
                if(gp.player.life>9)
                    gp.player.life=9;
                gp.obj[i]=null;
                gp.ui.showMessage("+1 Heart");
                break;
            case "Spike":
                gp.player.life-=2;
                if(gp.player.life<=0){
                    gp.gameState=gp.gameOver;
                    gp.stopMusic();
                }
                gp.obj[i]=null;
                gp.ui.showMessage("-2 HP");
                break;
            case "EndKey":
                gp.gameState= gp.winState;
                break;
            }
        }
    }
    public void interactNPC(int i){
        /**
         * Method than handles player-npc/enemy interaction
         */
        if(i!=100){

            if(gp.npc[i] instanceof NPC_Starter || gp.npc[i] instanceof NPC_Wizard){

                if(keyH.ePressed){
                    gp.gameState=gp.dialogueState;
                    gp.npc[i].speak();
                }
            }
            if(gp.npc[i] instanceof Enemy){
                gp.player.life-=1;
                if(gp.player.life<=0){
                    gp.gameState=gp.gameOver;
                }
                /**
                 * Because this statement is called every frame,
                 * the enemy will take 1hp every frame
                 * they are in contact, which makes the enemies
                 * so dangerous to the player.
                 */
            }
        }
    }
    public void getPlayerImage(){

        up1 = setup("player/up1-boy");
        up2 = setup("player/up2-boy");
        down1 = setup("player/down1-boy");
        down2 = setup("player/down2-boy");
        left1 = setup("player/left1-boy");
        left2 = setup("player/left2-boy");
        right1 = setup("player/right1-boy");
        right2 = setup("player/right2-boy");
        standing = setup("player/standing-boy");

    }

    public void draw(Graphics g2){
        /**
         * Handles player sprite drawing, working with the statement created
         * in the update method by changing spriteNumber's value
         */
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
