package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_Starter extends Entity{

    public NPC_Starter(GamePanel gp){
        super(gp);

        direction="down";
        speed=1;
        getNpcImage();
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
}
