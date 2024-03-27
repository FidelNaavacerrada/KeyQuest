package src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;

public class GamePanel extends JPanel implements Runnable{

    //Screen dimensions and resolution
    static final int originalTileSize = 16;
    static final int scale = 3;
    static final int tileSize = originalTileSize*scale;
    static final int GAME_WIDTH = 16;
    static final int GAME_HEIGHT = 12;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH*tileSize,GAME_HEIGHT*tileSize);//768x576

    //FPS
    int FPS = 60;

    //Key Handler
    AL keyH = new AL();

    //Player base position and speed
    int playerX=100, playerY=100, playerSpeed=5;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Player player;
    int numberOfRooms;
    int enemiesPerRoom;


    GamePanel(){
        newPlayer();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(keyH);
        this.setBackground(Color.black);

        startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newPlayer(){

    }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

    }

    public void draw(Graphics g){

    }

    public void move(){

    }

    public void checkCollision(){

    }

    public void run(){

        double drawInterval = 1000000000/FPS; //1 sec/60 -> draw interval
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //update info
            update();

            //draw screen
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            }   catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    public static class AL extends KeyAdapter{

        public boolean upPressed, downPressed, leftPressed, rightPressed;
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();

            switch(code){
                case KeyEvent.VK_W:
                    upPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
            }
        }
        public void keyReleased(KeyEvent e){

            int code = e.getKeyCode();

            switch(code) {
                case KeyEvent.VK_W:
                    upPressed = false;
                    break;
                case KeyEvent.VK_S:
                    downPressed = false;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = false;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = false;
                    break;
            }
        }
    }
}