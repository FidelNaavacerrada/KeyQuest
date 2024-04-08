package main;

import entity.Enemy;
import entity.NPC_Starter;
import entity.NPC_Wizard;
import object.*;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setAsset(){
        gp.obj[0] = new Key(gp);
        gp.obj[0].x = 2*gp.tileSize;
        gp.obj[0].y = 5*gp.tileSize;

        gp.obj[5] = new Door(gp);
        gp.obj[5].x = 2*gp.tileSize;
        gp.obj[5].y = 7*gp.tileSize;

        gp.obj[1] = new Key(gp);
        gp.obj[1].x = 30*gp.tileSize;
        gp.obj[1].y = 1*gp.tileSize;

        gp.obj[3] = new Door(gp);
        gp.obj[3].x = 21*gp.tileSize;
        gp.obj[3].y = 7*gp.tileSize;

        gp.obj[2] = new Key(gp);
        gp.obj[2].x = 19*gp.tileSize;
        gp.obj[2].y = 22*gp.tileSize;

        gp.obj[4] = new Key(gp);
        gp.obj[4].x = 33*gp.tileSize;
        gp.obj[4].y = 22*gp.tileSize;

        gp.obj[6] = new HealingPotion(gp);
        gp.obj[6].x = 10*gp.tileSize;
        gp.obj[6].y = 21*gp.tileSize;

        gp.obj[7] = new Door(gp);
        gp.obj[7].x = 2*gp.tileSize;
        gp.obj[7].y = 19*gp.tileSize;

        gp.obj[8] = new Door(gp);
        gp.obj[8].x = 27*gp.tileSize;
        gp.obj[8].y = 18*gp.tileSize;

        gp.obj[9] = new Door(gp);
        gp.obj[9].x = 43*gp.tileSize;
        gp.obj[9].y = 18*gp.tileSize;

        gp.obj[10] = new Key(gp);
        gp.obj[10].x = 14*gp.tileSize;
        gp.obj[10].y = 22*gp.tileSize;

        gp.obj[11] = new Spike(gp);
        gp.obj[11].x = 2*gp.tileSize;
        gp.obj[11].y = 13*gp.tileSize;

        gp.obj[12] = new Spike(gp);
        gp.obj[12].x = 15*gp.tileSize;
        gp.obj[12].y = 2*gp.tileSize;

        gp.obj[13] = new Spike(gp);
        gp.obj[13].x = 9*gp.tileSize;
        gp.obj[13].y = 17*gp.tileSize;

        gp.obj[14] = new Spike(gp);
        gp.obj[14].x = 11*gp.tileSize;
        gp.obj[14].y = 18*gp.tileSize;

        gp.obj[15] = new Spike(gp);
        gp.obj[15].x = 24*gp.tileSize;
        gp.obj[15].y = 5*gp.tileSize;

        gp.obj[16] = new Spike(gp);
        gp.obj[16].x = 25*gp.tileSize;
        gp.obj[16].y = 5*gp.tileSize;

        gp.obj[17] = new EndKey(gp);
        gp.obj[17].x = 46*gp.tileSize;
        gp.obj[17].y = 18*gp.tileSize;

        gp.obj[18] = new Spike(gp);
        gp.obj[18].x = 18*gp.tileSize;
        gp.obj[18].y = 19*gp.tileSize;

        gp.obj[19] = new Spike(gp);
        gp.obj[19].x = 34*gp.tileSize;
        gp.obj[19].y = 19*gp.tileSize;

        gp.obj[20] = new HealingPotion(gp);
        gp.obj[20].x = 35*gp.tileSize;
        gp.obj[20].y = 22*gp.tileSize;

        gp.obj[21] = new Spike(gp);
        gp.obj[21].x = 5*gp.tileSize;
        gp.obj[21].y = 10*gp.tileSize;
    }

    public void createNPCs(){
        gp.npc[0] = new NPC_Starter(gp);
        gp.npc[0].worldX = gp.tileSize*2;
        gp.npc[0].worldY = gp.tileSize*8;

        gp.npc[1] = new NPC_Wizard(gp);
        gp.npc[1].worldX = gp.tileSize*1;
        gp.npc[1].worldY = gp.tileSize*1;

        gp.npc[2] = new Enemy(gp);
        gp.npc[2].worldX = gp.tileSize*7;
        gp.npc[2].worldY = gp.tileSize*11;

        gp.npc[3] = new Enemy(gp);
        gp.npc[3].worldX = gp.tileSize*8;
        gp.npc[3].worldY = gp.tileSize*10;

        gp.npc[4] = new Enemy(gp);
        gp.npc[4].worldX = gp.tileSize*6;
        gp.npc[4].worldY = gp.tileSize*9;

        gp.npc[5] = new Enemy(gp);
        gp.npc[5].worldX = gp.tileSize*8;
        gp.npc[5].worldY = gp.tileSize*11;

        gp.npc[6] = new Enemy(gp);
        gp.npc[6].worldX = gp.tileSize*10;
        gp.npc[6].worldY = gp.tileSize*2;

        gp.npc[7] = new Enemy(gp);
        gp.npc[7].worldX = gp.tileSize*12;
        gp.npc[7].worldY = gp.tileSize*4;

        gp.npc[8] = new Enemy(gp);
        gp.npc[8].worldX = gp.tileSize*21;
        gp.npc[8].worldY = gp.tileSize*2;

        gp.npc[9] = new Enemy(gp);
        gp.npc[9].worldX = gp.tileSize*25;
        gp.npc[9].worldY = gp.tileSize*2;

        gp.npc[10] = new Enemy(gp);
        gp.npc[10].worldX = gp.tileSize*29;
        gp.npc[10].worldY = gp.tileSize*2;

        gp.npc[11] = new Enemy(gp);
        gp.npc[11].worldX = gp.tileSize*29;
        gp.npc[11].worldY = gp.tileSize*5;

        gp.npc[12] = new Enemy(gp);
        gp.npc[12].worldX = gp.tileSize*22;
        gp.npc[12].worldY = gp.tileSize*4;

        gp.npc[13] = new Enemy(gp);
        gp.npc[13].worldX = gp.tileSize*22;
        gp.npc[13].worldY = gp.tileSize*8;

        gp.npc[14] = new Enemy(gp);
        gp.npc[14].worldX = gp.tileSize*22;
        gp.npc[14].worldY = gp.tileSize*15;

        gp.npc[15] = new Enemy(gp);
        gp.npc[15].worldX = gp.tileSize*21;
        gp.npc[15].worldY = gp.tileSize*15;

        gp.npc[16] = new Enemy(gp);
        gp.npc[16].worldX = gp.tileSize*29;
        gp.npc[16].worldY = gp.tileSize*18;

        gp.npc[17] = new Enemy(gp);
        gp.npc[17].worldX = gp.tileSize*35;
        gp.npc[17].worldY = gp.tileSize*18;

        gp.npc[18] = new Enemy(gp);
        gp.npc[18].worldX = gp.tileSize*36;
        gp.npc[18].worldY = gp.tileSize*18;

        gp.npc[19] = new Enemy(gp);
        gp.npc[19].worldX = gp.tileSize*37;
        gp.npc[19].worldY = gp.tileSize*18;

        gp.npc[20] = new Enemy(gp);
        gp.npc[20].worldX = gp.tileSize*36;
        gp.npc[20].worldY = gp.tileSize*18;

        gp.npc[21] = new Enemy(gp);
        gp.npc[21].worldX = gp.tileSize*45;
        gp.npc[21].worldY = gp.tileSize*18;
    }
}
