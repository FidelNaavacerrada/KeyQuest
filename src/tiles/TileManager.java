package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileMatrix[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        mapTileMatrix = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/48x48map.txt");
    }

    public void loadMap(String mapPath){
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0, row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow){

                String line = br.readLine();
                while(col<gp.maxWorldCol){

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileMatrix[col][row] = num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/environment/floor_plain.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/environment/wall_center.png"));
            tile[1].collision = true;

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2){

        int worldCol=0, worldRow=0;

        while(worldCol<gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileMatrix[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.playerX + gp.player.screenX;
            int screenY = worldY - gp.player.playerY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.playerX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.playerX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.playerY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.playerY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
