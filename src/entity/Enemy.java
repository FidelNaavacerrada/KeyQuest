package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Enemy extends Entity{

    int health;

    String name;
    public Enemy(GamePanel gp){
        super(gp);

        name="enemy";
        isMoving=true;
        direction="down";
        speed=1;
        getEnemyImage();
        setDialogue();
    }

    public void getEnemyImage(){

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
