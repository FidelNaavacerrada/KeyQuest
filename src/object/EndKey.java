package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EndKey extends SuperObject{

    GamePanel gp;

    public EndKey(GamePanel gp){
        name = "EndKey";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/end_key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
