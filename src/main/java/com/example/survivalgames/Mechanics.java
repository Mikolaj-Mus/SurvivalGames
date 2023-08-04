package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 60;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 1000;
    private static Champ[] champTab;
    boolean running = false;
    Timer timer;
    Random random = new Random();


    Mechanics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        createChamps(10);
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
        for (Champ champ : champTab) {
            champ.draw(g);
        }
    }

    public void drawLines(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
        }
    }

    public void createChamps(int number) {
        champTab = new Champ[number];
        Set<String> occupiedPositions = new HashSet<>();

        for (int i = 0; i < champTab.length; i++) {
            int x, y;
            do {
                x = random.nextInt((int) Math.sqrt(GAME_UNITS));
                y = random.nextInt((int) Math.sqrt(GAME_UNITS));
            } while (occupiedPositions.contains(getPositionKey(x, y)));

            champTab[i] = new Champ(0, x, y);
            champTab[i].setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            occupiedPositions.add(getPositionKey(x, y));
        }
    }

    public static Champ[] getChampTab() {
        return champTab;
    }

    public void setChampTab(Champ[] champTab) {
        this.champTab = champTab;
    }

    private String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    public static int returnGAME_UNITS() {
        return GAME_UNITS;
    }

    public static int returnUNIT_SIZE() {
        return UNIT_SIZE;
    }

    public static int returnSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public static int returnSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Champ champ : champTab) {
            champ.move();
        }

        repaint();
    }


}
