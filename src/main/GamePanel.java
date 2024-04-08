package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tiles.TileManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen dimensions and resolution
    static final int originalTileSize = 16;
    static final int scale = 3;
    public static final int tileSize = originalTileSize*scale;
    public static final int GAME_WIDTH = 16;
    public static final int GAME_HEIGHT = 12;
    public static final int SCREEN_WIDTH = GAME_WIDTH*tileSize;
    public static final int SCREEN_HEIGHT = GAME_HEIGHT*tileSize;
    public static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);//768x576

    //BIG MAP SETTINGS
    public final int maxWorldCol = 48;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    static final int FPS = 60;

    //Managers and setters
    TileManager tileM = new TileManager(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    Sound sound = new Sound();

    //Key Handler
    AL keyH = new AL(this);

    //UI
    public UI ui = new UI(this);

    //GameThread
    Thread gameThread;

    //Entities and objects
    public Player player;
    public SuperObject obj[] = new SuperObject[25];
    public Entity npc[] = new Entity[25];

    //Game State
    public int gameState;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int gameOver=4;
    public final int winState=5;

    GamePanel(){

        this.newPlayer();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(keyH);
        this.setBackground(Color.darkGray);

        this.setupGame();
        startGameThread();
    }

    public void setupGame(){

        gameState = playState;
        assetSetter.setAsset();
        assetSetter.createNPCs();

        playMusic(0);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newPlayer(){
        player = new Player(this, keyH);
    }

    public void update(){

        if(gameState==playState){
            //player
            player.update();
            //npc
            for(int i=0;i<npc.length;i++) {
                if (npc[i] != null)
                    npc[i].update();
            }
        }
        if(gameState==pauseState){
            //No updates
        }
        if(gameState==dialogueState){
            //No updates
        }
        if(gameState==gameOver){
            //No updates
        }
        if(gameState==winState){
            //No updates
        }
    }

    @Override
    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        //Obj
        for(int i=0;i<obj.length;i++){
            if(obj[i] != null)
                obj[i].draw(g2, this);
        }
        //NPC
        for(int i=0;i<npc.length;i++){
            if(npc[i] != null)
                npc[i].draw(g2);
        }
        //Player
        player.draw(g2);
        //UI
        ui.draw(g2);


        g2.dispose();

    }
    public void playMusic(int i){

        sound.setFile(i);
        sound.setVolume(0.1F);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){ sound.stop(); }
    public void soundEffect(int i){
        sound.setFile(i);
        sound.play();
    }

    public void run(){

        //Game loop
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
    public static class AL extends KeyAdapter{

        public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, spacePressed;
        GamePanel gp;

        public AL(GamePanel gp){
            this.gp=gp;
        }

        @Override
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();

            if(gp.gameState==gp.playState) {

                switch (code) {

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
                    case KeyEvent.VK_E:
                        //Action Key E
                        ePressed = true;
                        break;
                }
            }
            if(gp.gameState==gp.dialogueState){
                if(code==KeyEvent.VK_SPACE) {
                    gp.gameState=gp.playState;
                }
            }
            if(code==KeyEvent.VK_P){
                //Pause or unpause game
                if(gp.gameState==gp.playState){
                    gp.gameState=gp.pauseState;
                }
                else if(gp.gameState==gp.pauseState){
                    gp.gameState=gp.playState;
                }
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
                case KeyEvent.VK_E:
                    ePressed = false;
                    break;

            }
        }
    }
}