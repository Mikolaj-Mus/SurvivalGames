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

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 80;
    public static final int DELAY = 100;
    public static final int CHAMPS_NUM = 100;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    public static final int CELLS = (int) Math.sqrt(GAME_UNITS);
    private static HashMap<String, Champ> champMap = new HashMap<>();
    private boolean running = false;
    Timer timer;
    static Random random = new Random();

    // Initializes the game mechanics, creates champions, and starts the game loop.
    Mechanics() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        createChamps(CHAMPS_NUM);
        startGame();
    }

    // Starts the game loop timer.
    public void startGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Draws champions on the screen and handles game over state.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (champMap.size() != 1) {
            for (Champ champ : champMap.values()) {
                champ.draw(g);
                if (!running) {
                    g.drawString(String.valueOf(champ.getId()), champ.getxCor() * Mechanics.UNIT_SIZE, champ.getyCor() * Mechanics.UNIT_SIZE + Mechanics.UNIT_SIZE / 8);
                }
            }
        } else {
            gameOver(g);
        }
    }

    // Creates a specified number of champions with random positions and colors.
    public void createChamps(int number) {
        if (number > GAME_UNITS) {
            throw new IllegalArgumentException("Number of champions to create exceeds available game units.");
        }
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

    // Generates a unique key based on position coordinates.
    public static String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    // Removes a defeated champion from the map.
    public static void removeChampion(Champ championToRemove) {
        champMap.remove(getPositionKey(championToRemove.getxCor(), championToRemove.getyCor()));
    }

    // Displays the winning champion's information and graphics.
    public void gameOver(Graphics g) {
        Champ winner = champMap.values().iterator().next();
        g.setColor(winner.getColor());
        g.fillOval(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 5, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        g.setFont(new Font("Calibre", Font.BOLD, 100));
        g.drawString("WINNER", SCREEN_WIDTH / 4, SCREEN_HEIGHT - SCREEN_HEIGHT / 6);
        g.drawString("#" + winner.getId(), SCREEN_WIDTH / 4, SCREEN_HEIGHT / 6);
    }

    public static Random getRandom() {
        return random;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    // Handles game actions, champion movement, fights, and updates.
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

    // Handles key events for starting, pausing, and quitting the game.
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER -> {
                    running = true;
                    Mechanics.this.repaint();
                }
                case KeyEvent.VK_SPACE -> {
                    running = false;
                    Mechanics.this.repaint();
                }
                case KeyEvent.VK_ESCAPE -> System.exit(0);
            }
        }
    }
}
