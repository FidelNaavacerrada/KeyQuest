package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends SuperObject{

    GamePanel gp;

    public Heart(GamePanel gp){
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/hearts/full-heart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/hearts/1hit-heart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/hearts/2hit-heart.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/res/objects/hearts/dead-heart.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image4, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
