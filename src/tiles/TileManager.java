package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    /**
     * Creates map, its different tiles around the player. The map is loaded
     * as a text document and converted into a matrix (mapTileMatrix).
     * The draw() method draws the program around the player as it moves,
     * making the impression that the player is moving when in reality it
     * is always at the center of the screen.
     */

    GamePanel gp;
    public Tile[] tile;
    //Tile types array
    public int mapTileMatrix[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        mapTileMatrix = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/48x48map.txt");
    }

    public void loadMap(String mapPath){
        /**
         * Loads the .txt map into mapTileMatrix, separating by white spaces.
         */
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

        setup(0, "floor_plain", false);
        setup(1, "wall_center", true);
    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/environment/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        //Draw method previously mentioned

        int worldCol=0, worldRow=0;

        while(worldCol<gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileMatrix[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
