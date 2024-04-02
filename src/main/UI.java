package main;

import entity.Player;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_30, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int timeCounter=0;
    public boolean gameEnd=false;

    double playTime=0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){

        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        Key key = new Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        if(gameEnd==true){ //Messages displayed when game ends, also ends gamethread

            String text;
            int textLength;
            int x, y;

            //Message number 1
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            text = "You ended the game!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Length of text
            x=gp.SCREEN_WIDTH/2-textLength/2;
            y=gp.SCREEN_HEIGHT/2-(gp.tileSize*3);
            g2.drawString(text, x, y);

            //Total time message display
            text = "Total Time: "+dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Length of text
            x=gp.SCREEN_WIDTH/2-textLength/2;
            y=gp.SCREEN_HEIGHT/2-(gp.tileSize*4);
            g2.drawString(text, x, y);

            //Message number 2
            g2.setFont(arial_80);
            g2.setColor(Color.white);
            text = "CONGRATULATIONS";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //Length of text
            x=gp.SCREEN_WIDTH/2-textLength/2;
            y=gp.SCREEN_HEIGHT/2+(gp.tileSize*2);
            g2.drawString(text, x, y);

            //Ends gameThread
            gp.gameThread = null;

        }
        else{
            //Displays Key number at the top left
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize,gp.tileSize, null);
            g2.drawString("Keys: "+gp.player.hasKey, 25, 50);

            //Display Time spent in the game (sums 1/60 seconds every frame)
            playTime += (double)1/60;
            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);

            //Message displayer for events i.e. opening door with a 2 sec counter
            if(messageOn==true){
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                timeCounter++;
                if(timeCounter>120){
                    timeCounter=0;
                    messageOn=false;
                }
            }
        }
    }
}
