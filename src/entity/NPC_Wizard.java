package entity;

import main.GamePanel;

public class NPC_Wizard extends Entity{

    String name;
    String dir = "npc/wizard";
    public NPC_Wizard(GamePanel gp){
        super(gp);

        name="wizard";
        isMoving=false;
        direction="down";
        speed=0;
        getNpcImage();
        setDialogue();
    }

    public void getNpcImage(){

        standing = setup(dir+"/wizard1");
        up1 = setup(dir+"/wizard1");
        up2 = setup(dir+"/wizard1");
        down1 = setup(dir+"/wizard1");
        down2 = setup(dir+"/wizard1");
        left1 = setup(dir+"/wizard1");
        left2 = setup(dir+"/wizard1");
        right1 = setup(dir+"/wizard1");
        right2 = setup(dir+"/wizard1");
        standing = setup(dir+"/wizard1");
    }
    public void setDialogue(){

        totalDialogues[0] = "Hello new Hero, I am Bastian the wise. \nPleased to meet you";
        totalDialogues[1] = "Please grab this key and move to the next \nroom to speak to Philip, he seems to be\n" +
                "out of control and as I am\nold I can't walk to reach him.";
        totalDialogues[2] = "Here, grab that key to open the \ndoor and try to talk some sense into him.";
    }
    public void speak(){

        super.speak();
    }
}
