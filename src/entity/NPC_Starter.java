package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Starter extends Entity{

    String name;
    String dir = "npc/moving_npc";
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

        up1 = setup(dir+"/up1-npc");
        up2 = setup(dir+"/up2-npc");
        down1 = setup(dir+"/down1-npc");
        down2 = setup(dir+"/down2-npc");
        left1 = setup(dir+"/left1-npc");
        left2 = setup(dir+"/left2-npc");
        right1 = setup(dir+"/right1-npc");
        right2 = setup(dir+"/right2-npc");
        standing = setup(dir+"/standing-npc");

    }
    public void setDialogue(){

        totalDialogues[0] = "AAAAH! Beware of those creatures, \nI am trapped here with them";
        totalDialogues[1] = "Sorry, I have not introduced myself, \nI am Philip the Great!";
        totalDialogues[2] = "Even though I am very brave, \nmy knee hurts from an epic fight I just had," +
                "\nThat's why I don't go through myself.";
        totalDialogues[3] = "Quickly, you have to find the MEGAKEY!\nIt is bigger than the normal keys." +
                "\nAnd be careful of the spikes too!\nIf you get hurt, drink from the red potions";
    }
    public void speak(){

        super.speak();
    }
    public void setAction(){
        /**
         * Creates a very basic AI for the NPC movement, which makes it move
         * and change direction (or nor) randomly every 2 seconds
         */

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
