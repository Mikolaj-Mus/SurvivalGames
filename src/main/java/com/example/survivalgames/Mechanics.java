package com.example.survivalgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

public class Mechanics extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 8;
    private static final int DELAY = 1;
    private static final int CHAMPS_NUM = 1000;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    public static final int CELLS = (int) Math.sqrt(GAME_UNITS);
    private static HashMap<String, Champ> champMap = new HashMap<>();
    private static boolean running = false;
    Timer timer;
    static Random random = new Random();
    public static int i = 0;
    Font font = new Font("Calibre Light", Font.ITALIC, 200);

    Mechanics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        createChamps(CHAMPS_NUM);
        startGame();
    }

    public void startGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (champMap.size() != 1) {
            for (Champ champ : champMap.values()) {
                champ.draw(g);
            }
        } else {
            gameOver(g);
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
        i++;
        System.out.println("Fight num: " + i);
        champMap.remove(getPositionKey(championToRemove.getxCor(), championToRemove.getyCor()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Champ> championsCopy = new ArrayList<>(champMap.values());

        ListIterator<Champ> iterator = championsCopy.listIterator();
        if (running && champMap.size() > 1) {
            while (iterator.hasNext()) {
                Champ champ = iterator.next();
                champ.move(champMap);
                champ.fight(champMap);
                repaint();

                if (champ.isDefeated() || !champMap.containsValue(champ)) {
                    iterator.remove();
                }
            }
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(champMap.values().iterator().next().getColor());
        g.fillOval(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 8, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        g.setFont(new Font("Calibre", Font.BOLD, 100));
        g.drawString("WINNER", SCREEN_WIDTH / 4, SCREEN_HEIGHT - SCREEN_HEIGHT / 4);
        g.drawString("#" + champMap.values().iterator().next().getId(), SCREEN_WIDTH / 4, SCREEN_HEIGHT / 8);
    }

    public static class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER -> running = true;
                case KeyEvent.VK_SPACE -> running = false;
            }
        }
    }

    public static boolean isRunning() {
        return running;
    }
}
