package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    static final int GAME_WIDTH = GamePanel.SCREEN_WIDTH;
    static final int GAME_HEIGHT = GamePanel.SCREEN_HEIGHT;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    GameFrame frame;
    JButton startGame = new JButton("START GAME");
    MPanel menuPanel= new MPanel();
    int menuState, start=0, charSelection=1;

    Menu(){

        setUpMenu();
    }

    public void setUpMenu(){

        menuState=start;
        this.add(menuPanel);
        initMenuPanel(menuPanel);

        this.setTitle("KeyQuest");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==startGame){
            this.dispose();
            frame = new GameFrame();
        }
    }

    public void initMenuPanel(MPanel menuPanel){
        startGame.setBounds(100,GAME_HEIGHT/2 + 100,200,40);
        startGame.setFocusable(false);
        startGame.addActionListener(this);

        menuPanel.add(startGame);
        menuPanel.setFocusable(true);
        menuPanel.setPreferredSize(SCREEN_SIZE);
        menuPanel.setBackground(new Color(205,92,92));
    }

    class MPanel extends JPanel{

        int menuState, start=0, charSelection=1;
        Font quinqueFive;

        MPanel(){
            super();

            InputStream is = getClass().getResourceAsStream("/res/fonts/QuinqueFive.otf");
            try{
                quinqueFive = Font.createFont(Font.TRUETYPE_FONT, is);
            }catch (IOException | FontFormatException e){
                e.printStackTrace();
            }
        }
        public void paint(Graphics g){

            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;

            if(menuState==0){
                screenTitle(g2);
            }

        }
        public void screenTitle(Graphics2D g2){

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
            String text = "KEY QUEST";
            int x=UtilityTool.centeredTextX(text,g2 ,menuPanel, GAME_WIDTH), y=GAME_HEIGHT/2;
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }
    }
}