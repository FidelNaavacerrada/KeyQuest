package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    static final int GAME_WIDTH = 1280;
    static final int GAME_HEIGHT = 720;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    Graphics graphics;
    GameFrame frame;
    JButton startGame = new JButton("START");

    Menu(){

        startGame.setBounds(100,160,200,40);
        startGame.setFocusable(false);
        startGame.addActionListener(this);

        this.add(startGame);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        this.setTitle("KeyQuest");
        this.setResizable(false);
        this.setBackground(Color.black);
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
}
