package main;

import entity.NPC_Starter;
import object.Door;
import object.Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setAsset(){
        gp.obj[0] = new Key(gp);
        gp.obj[0].x = 3*gp.tileSize;
        gp.obj[0].y = 1*gp.tileSize;

        gp.obj[1] = new Key(gp);
        gp.obj[1].x = 21*gp.tileSize;
        gp.obj[1].y = 9*gp.tileSize;

        gp.obj[2] = new Key(gp);
        gp.obj[2].x = 14*gp.tileSize;
        gp.obj[2].y = 13*gp.tileSize;

        gp.obj[3] = new Door(gp);
        gp.obj[3].x = 11*gp.tileSize;
        gp.obj[3].y = 9*gp.tileSize;

        gp.obj[4] = new Door(gp);
        gp.obj[4].x = 2*gp.tileSize;
        gp.obj[4].y = 19*gp.tileSize;

        gp.obj[5] = new Door(gp);
        gp.obj[5].x = 2*gp.tileSize;
        gp.obj[5].y = 7*gp.tileSize;
    }

    public void createNPCs(){
        gp.npc[0] = new NPC_Starter(gp);
        gp.npc[0].worldX = gp.tileSize*1;
        gp.npc[0].worldY = gp.tileSize*2;
    }
}
