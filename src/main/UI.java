package main;

import entity.Player;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_30, arial_80;
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
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2=g2;
        g2.setFont(arial_30);
        g2.setColor(Color.white);

        if(gp.gameState==gp.playState){
            //Draw on screen when playing
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawString("Keys: "+gp.player.hasKey, 25, 50);

            //Display Time spent in the game (sums 1/60 seconds every frame)
            playTime += (double)1/60;
            //g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);

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
        if(gp.gameState==gp.pauseState){
            //Draw on screen when pause
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){

        String text = "PAUSE";
        int x=centeredTextX(text),y=gp.SCREEN_HEIGHT/2;
        g2.drawString(text, x, y);
    }
    public int centeredTextX(String string){
        //Make Strings centered on x-axis
        int x;
        int textLength = (int)g2.getFontMetrics().getStringBounds(string, g2).getWidth();
        x=gp.SCREEN_WIDTH/2-textLength/2; //x=middle of screen-middle of textLength
        return x;
    }
}
