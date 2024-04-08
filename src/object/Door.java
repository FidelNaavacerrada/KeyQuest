package object;

import main.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends SuperObject{

    public Door(GamePanel gp){
        name = "Door";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_closed.png")); //closed door
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
