package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 80;
    private static final int DELAY = 10;
    private static final int CHAMPS_NUM = 100;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    public static final int CELLS = (int) Math.sqrt(GAME_UNITS);
    private static HashMap<String, Champ> champMap = new HashMap<>();
    boolean running = false;
    Timer timer;
    static Random random = new Random();
    public static int i = 0;


    Mechanics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        createChamps(CHAMPS_NUM);
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


//    public void createChamps(int number) {
//
//        Champ champ1 = new Champ(1, 1, 1);
//        champ1.setColor(Color.BLUE);
//        Champ champ2 = new Champ(2, 2, 1);
//        champ2.setColor(Color.YELLOW);
//        Champ champ3 = new Champ(3, 2, 2);
//        champ3.setColor(Color.RED);
////        Champ champ4 = new Champ(4, 4, 1);
////        champ4.setColor(Color.GREEN);
////        Champ champ5 = new Champ(5, 5, 1);
////        champ5.setColor(Color.ORANGE);
//
//        champMap.put(getPositionKey(1, 1), champ1);
//        champMap.put(getPositionKey(2, 1), champ2);
//        champMap.put(getPositionKey(2, 2), champ3);
////        champMap.put(getPositionKey(4, 1), champ4);
////        champMap.put(getPositionKey(5, 1), champ5);
//
//    }

    public static String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    public static void removeChampion(Champ championToRemove) {
        i++;
        System.out.println("ID: " + championToRemove.getId() + " " + i);
        champMap.remove(getPositionKey(championToRemove.getxCor(), championToRemove.getyCor()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Champ> championsCopy = new ArrayList<>(champMap.values());

        ListIterator<Champ> iterator = championsCopy.listIterator();
        while (iterator.hasNext()) {
            Champ champ = iterator.next();
            champ.move(champMap);
            champ.fight(champMap);

            if(champ.isDefeated() || !champMap.containsValue(champ)) {
                iterator.remove();
            }
        }

        repaint();
    }


}
