package main;

import object.Door;
import object.Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setAsset(){
        gp.obj[0] = new Key();
        gp.obj[0].x = 3*gp.tileSize;
        gp.obj[0].y = 1*gp.tileSize;

        gp.obj[1] = new Door();
        gp.obj[1].x = 12*gp.tileSize;
        gp.obj[1].y = 5*gp.tileSize;
    }
}
