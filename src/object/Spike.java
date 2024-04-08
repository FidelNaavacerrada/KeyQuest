package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Spike extends SuperObject{

    public Spike(GamePanel gp){
        name = "Spike";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/spike.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}