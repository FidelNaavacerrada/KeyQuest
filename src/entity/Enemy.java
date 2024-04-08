package entity;

import main.GamePanel;

import java.util.*;

public class Enemy extends Entity{
    /**
     * Class that represents the Enemy entities.
     */

    String name;
    public Enemy(GamePanel gp){
        /**
         * Class constructor. Calls super constructor
         * and adds name, movement, direction and speed,
         * It also calls both class methods getEnemyImage()
         * and setDialogue().
         */

        super(gp);

        name="enemy";
        isMoving=true;
        direction="down";
        speed=1;
        getEnemyImage();
        setDialogue();
    }

    public void getEnemyImage(){
        /**
         * Loads sprites used by Enemy entity.
         */

        String dir = "/enemy/enemy-";

        up1 = setup(dir+"up1");
        up2 = setup(dir+"up2");
        down1 = setup(dir+"down1");
        down2 = setup(dir+"down2");
        left1 = setup(dir+"left1");
        left2 = setup(dir+"left2");
        right1 = setup(dir+"right1");
        right2 = setup(dir+"right2");
        standing = setup(dir+"down1");

    }
    public void setAction(){
        /**
         * Creates movement for Enemy entity, selecting a
         * direction every second.
         */

        Random random = new Random();
        int i = random.nextInt(100);
        timeForMovement++;

        if(timeForMovement==60){
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
