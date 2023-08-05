package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 80;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;
    public static final int CELLS = (int) Math.sqrt(GAME_UNITS);
    private static HashMap<String, Champ> champMap = new HashMap<>();
    boolean running = false;
    Timer timer;
    static Random random = new Random();


    Mechanics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        createChamps(42);
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
        for (Champ champ : champMap.values()) {
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
        for (int i = 0; i < number; i++) {
            int x, y;
            do {
                x = random.nextInt(CELLS);
                y = random.nextInt(CELLS);
            } while (champMap.containsKey(getPositionKey(x, y)));

            Champ champ = new Champ(i + 1, x, y);
            champ.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            champMap.put(getPositionKey(x, y), champ);
        }
    }


    public static String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    public static void removeChampion(Champ championToRemove) {
        champMap.remove(getPositionKey(championToRemove.getxCor(), championToRemove.getyCor()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Champ> championsCopy = new ArrayList<>(champMap.values());

        for (Champ champ : championsCopy) {
            champ.move(champMap);
            champ.fight(champMap);
        }

        repaint();
    }


}
