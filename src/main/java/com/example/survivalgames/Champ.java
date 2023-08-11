package com.example.survivalgames;

import java.util.*;
import java.awt.*;

public class Champ {
    private final int id;
    private int xCor;
    private int yCor;
    private int strength = 1;
    private int direction;
    private Color color;
    private final Set<Integer> excludedDirection = new HashSet<>();
    private boolean defeated = false;

    public Champ(int id, int x, int y) {
        this.id = id;
        this.xCor = x;
        this.yCor = y;
    }

    // Draws the champion on the screen.
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xCor * Mechanics.UNIT_SIZE, yCor * Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE);

    }

    /*
    [0] - UP
    [1] - DOWN
    [2] - LEFT
    [3] - RIGHT
     */
    // Moves the champion's position while considering borders and enemies.
    public void move(HashMap<String, Champ> champMap) {

        int newX;
        int newY;

        checkBorder();
        checkEnemies(champMap);

        if (excludedDirection.size() < 4 && !defeated) {
            do {
                newX = xCor;
                newY = yCor;

                direction = getRandomDirection();

                switch (direction) {
                    case 0 -> newY -= 1;
                    case 1 -> newY += 1;
                    case 2 -> newX -= 1;
                    case 3 -> newX += 1;
                    default -> {
                    }
                }
            } while (champMap.containsKey(Mechanics.getPositionKey(newX, newY)));
            updatePosition(newX, newY, champMap);
        }
        excludedDirection.clear();
    }

    // Initiates a fight between champions, determining a winner and updating stats.
    public void fight(HashMap<String, Champ> champMap) {
        if (defeated) {
            return;
        }

        Champ opponent = getAdjacentChampion(xCor, yCor, champMap);
        if (opponent != null) {
            Champ winner = determineFightWinner(this, opponent);
            Champ loser = winner == this ? opponent : this;
            winner.increaseStrength();
            loser.setDefeated(true);
            Mechanics.removeChampion(loser);
        }
    }

    // Increases the strength of a champion after a victory.
    private void increaseStrength() {
        strength++;
    }

    // Determines the winner of a fight based on champion strengths.
    public Champ determineFightWinner(Champ champ1, Champ champ2) {
        int strengthDifference = champ1.getStrength() - champ2.getStrength();
        double champ1WinProbability = 1.0 / (1 + Math.exp(-strengthDifference));
        return Math.random() < champ1WinProbability ? champ1 : champ2;
    }

    // Returns an adjacent champion for combat.
    private Champ getAdjacentChampion(int x, int y, HashMap<String, Champ> champMap) {
        if (champMap.containsKey(Mechanics.getPositionKey(x, y - 1))) {
            return champMap.get(Mechanics.getPositionKey(x, y - 1));
        }
        if (champMap.containsKey(Mechanics.getPositionKey(x, y + 1))) {
            return champMap.get(Mechanics.getPositionKey(x, y + 1));
        }
        if (champMap.containsKey(Mechanics.getPositionKey(x - 1, y))) {
            return champMap.get(Mechanics.getPositionKey(x - 1, y));
        }
        if (champMap.containsKey(Mechanics.getPositionKey(x + 1, y))) {
            return champMap.get(Mechanics.getPositionKey(x + 1, y));
        }
        return null;
    }

    // Updates the champion's position in the map.
    public void updatePosition(int newX, int newY, HashMap<String, Champ> champMap) {
        champMap.remove(Mechanics.getPositionKey(xCor, yCor));
        xCor = newX;
        yCor = newY;
        champMap.put(Mechanics.getPositionKey(xCor, yCor), this);
    }

    // Returns a random movement direction for the champion.
    public int getRandomDirection() {
        do {
            direction = Mechanics.getRandom().nextInt(0, 4);
        } while (excludedDirection.contains(direction));

        return direction;
    }

    // Checks if the champion is at the border and updates excluded directions.
    public void checkBorder() {
        if (yCor == 0) {
            excludedDirection.add(0);
        }
        if (yCor == Mechanics.CELLS - 1) {
            excludedDirection.add(1);
        }
        if (xCor == 0) {
            excludedDirection.add(2);
        }
        if (xCor == Mechanics.CELLS - 1) {
            excludedDirection.add(3);
        }
    }

    // Checks for adjacent enemies and updates excluded directions.
    public void checkEnemies(HashMap<String, Champ> champMap) {
        if (champMap.containsKey(Mechanics.getPositionKey(xCor, yCor - 1))) {
            excludedDirection.add(4);
        }
        if (champMap.containsKey(Mechanics.getPositionKey(xCor, yCor + 1))) {
            excludedDirection.add(5);
        }
        if (champMap.containsKey(Mechanics.getPositionKey(xCor - 1, yCor))) {
            excludedDirection.add(6);
        }
        if (champMap.containsKey(Mechanics.getPositionKey(xCor + 1, yCor))) {
            excludedDirection.add(7);
        }
    }

    // Various getters and setters for champion attributes.
    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public int getxCor() {
        return xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public int getStrength() {
        return strength;
    }

}
