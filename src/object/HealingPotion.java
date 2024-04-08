package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HealingPotion extends SuperObject{

    public HealingPotion(GamePanel gp){
        name = "healpot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/healing-pot.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
