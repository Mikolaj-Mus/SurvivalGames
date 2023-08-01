package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 60;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 1000;
    private Champ[] champTab;
    boolean running = false;
    Timer timer;
    Random random;


    Mechanics(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
//        this.addKeyListener(new MyKeyAdapter());
        createChamps(12);
        startGame();
    }

    public void startGame() {

        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if(running) {
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            for (int i = 0; i < champTab.length; i++) {
                g.setColor(Color.ORANGE);
                g.drawOval(champTab[i].getxCor(), champTab[i].getyCor(),UNIT_SIZE,UNIT_SIZE);
            }

        }
    }

    public void createChamps(int number) {
        int index = 0;
        champTab = new Champ[number];
        champTab[0] = new Champ(0, 0, 0);
//        for (int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i+=3) {
//            for (int j = 0; j < SCREEN_HEIGHT/UNIT_SIZE; j+=3) {
//                champTab[index] = new Champ(index, i, j);
//                index++;
//            }
//
//        }
    }

    public static int returnGAME_UNITS() {
        return GAME_UNITS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
