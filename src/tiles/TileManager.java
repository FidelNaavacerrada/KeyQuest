package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/environment/floor_plain.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/environment/wall_center.png"));

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2){

        int col=0, row=0, x=0, y=0;

        while(col<gp.GAME_WIDTH && row < gp.GAME_HEIGHT){

            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+=gp.tileSize;

            if(col==gp.GAME_WIDTH){
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}
