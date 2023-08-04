package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    private static final int UNIT_SIZE = 8;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;
    private static Champ[] champTab;
    boolean running = false;
    Timer timer;
    Random random = new Random();
    private static Set<String> occupiedPositions = new HashSet<>();


    Mechanics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        createChamps(2000);
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        drawLines(g);
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

        for (int i = 0; i < champTab.length; i++) {
            int x, y;
            do {
                x = random.nextInt((int) Math.sqrt(GAME_UNITS));
                y = random.nextInt((int) Math.sqrt(GAME_UNITS));
            } while (occupiedPositions.contains(getPositionKey(x, y)));

            champTab[i] = new Champ(i+1, x, y);
            champTab[i].setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            occupiedPositions.add(getPositionKey(x, y));
        }
    }

    public static void setChampTab(Champ[] champTab) {
        Mechanics.champTab = champTab;
    }

    public static Champ[] getChampTab() {
        return champTab;
    }

    public static String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    public static Set<String> getOccupiedPositions() {
        return occupiedPositions;
    }

    public void setOccupiedPositions(Set<String> occupiedPositions) {
        this.occupiedPositions = occupiedPositions;
    }

    public static int getGAME_UNITS() {
        return GAME_UNITS;
    }

    public static int getUNIT_SIZE() {
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
            champ.fight();
        }
        repaint();
    }


}
