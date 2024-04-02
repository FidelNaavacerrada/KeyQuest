package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int x, y;
    public Rectangle realArea = new Rectangle(0,0,48,48);
    public int realAreaDefX = 0, realAreaDefY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = x - gp.player.worldX + gp.player.screenX;
        int screenY = y - gp.player.worldY + gp.player.screenY;

        if(x + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                x - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                y + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                y - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
