package main;

import entity.Player;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen dimensions and resolution
    static final int originalTileSize = 16;
    static final int scale = 3;
    public static final int tileSize = originalTileSize*scale;
    static final int GAME_WIDTH = 16;
    static final int GAME_HEIGHT = 12;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH*tileSize,GAME_HEIGHT*tileSize);//768x576

    //FPS
    static final int FPS = 60;

    //Key Handler
    AL keyH = new AL();

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Player player;
    int numberOfRooms;
    int enemiesPerRoom;


    GamePanel(){

        player = new Player(this, keyH);
        this.newPlayer();
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
        player = new Player(this, keyH);
    }

    @Override
    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

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
            //update
            update();

            //draw
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

        player.update();
    }

    public static class AL extends KeyAdapter{

        public boolean upPressed, downPressed, leftPressed, rightPressed;

        @Override
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
        @Override
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