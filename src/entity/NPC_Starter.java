package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Starter extends Entity{

    String name;
    public NPC_Starter(GamePanel gp){
        super(gp);

        name="starter";
        isMoving=true;
        direction="down";
        speed=1;
        getNpcImage();
        setDialogue();
    }

    public void getNpcImage(){

        up1 = setup("npc/up1-npc");
        up2 = setup("npc/up2-npc");
        down1 = setup("npc/down1-npc");
        down2 = setup("npc/down2-npc");
        left1 = setup("npc/left1-npc");
        left2 = setup("npc/left2-npc");
        right1 = setup("npc/right1-npc");
        right2 = setup("npc/right2-npc");
        standing = setup("npc/standing-npc");

    }
    public void setDialogue(){

        totalDialogues[0] = "Finally, a hero! I am trapped here, \nyou must let me out.";
        totalDialogues[1] = "Sorry, I have not introduced myself, \nI am Philip the Great!";
        totalDialogues[2] = "We need to get out of here, \nyou have to find the MEGAKEY!";
        totalDialogues[3] = "Quickly, start by grabbing \nthat key over there and opening the door.";
    }
    public void speak(){

        super.speak();
    }
    public void setAction(){

        Random random = new Random();
        int i = random.nextInt(100);
        timeForMovement++;

        if(timeForMovement==120){
            if(i<=25)
                direction="up";
            if(i>25&&i<=50)
                direction="down";
            if(i>50&&i<=75)
                direction="left";
            if(i>75&&i<100)
                direction="right";
            timeForMovement=0;
        }
    }
}
