package com.example.survivalgames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.*;


public class Champ {
    private int id;
    private int xCor;
    private int yCor;
    private int strength = 1;
    private int direction;
    private Color color;
    private final List<Integer> excludedDirection = new ArrayList<>();

    public Champ(int id, int x, int y) {
        this.id = id;
        this.xCor = x;
        this.yCor = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xCor * Mechanics.UNIT_SIZE, yCor * Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE);
//        g.drawString(String.valueOf(id), xCor * Mechanics.UNIT_SIZE(), yCor * Mechanics.UNIT_SIZE());
    }

    /*
    [0] - UP
    [1] - DOWN
    [2] - LEFT
    [3] - RIGHT
     */
    public void move() {

        int newX;
        int newY;

        checkBorder();

        do {
            newX = xCor;
            newY = yCor;

            direction = getRandomDirection();

            if (direction == 4) {
                break;
            }

            switch (direction) {
                case 0 -> newY -= 1;
                case 1 -> newY += 1;
                case 2 -> newX -= 1;
                case 3 -> newX += 1;
                default -> {}
            }
        } while (Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(newX, newY)));

        if (direction != 4) {
            updatePosition(newX, newY);
        }
    }

    public void fight() {
        if (hasAdjacentChampion(xCor, yCor)) {
            fightAdjacentChampion(xCor, yCor);
        }
    }

    private void fightAdjacentChampion(int x, int y) {
        Champ winner = null;
        int loserId = -1;
        for (Champ champion : Mechanics.getChampTab()) {
            if (champion != this && isAdjacent(x, y, champion)) {
                loserId = champion.getId();
                winner = new Random().nextBoolean() ? this : champion;
                break;
            }
        }
        if (winner == this) {
            Champ[] newChampTab = new Champ[Mechanics.getChampTab().length - 1];
            int newIndex = 0;

            for (Champ champion : Mechanics.getChampTab()) {
                if (champion.getId() != loserId) {
                    newChampTab[newIndex] = champion;
                    newIndex++;
                }
            }

            Mechanics.setChampTab(newChampTab);
        }
    }

    private boolean isAdjacent(int x, int y, Champ champion) {
        int dx = Math.abs(x - champion.getxCor());
        int dy = Math.abs(y - champion.getyCor());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    private boolean hasAdjacentChampion(int x, int y) {
        return Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(x, y - 1)) ||
                Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(x, y + 1)) ||
                Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(x - 1, y)) ||
                Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(x + 1, y));
    }


    public void updatePosition(int newX, int newY) {
        Mechanics.getOccupiedPositions().add(Mechanics.getPositionKey(newX, newY));
        Mechanics.getOccupiedPositions().remove(Mechanics.getPositionKey(xCor, yCor));
        xCor = newX;
        yCor = newY;
        excludedDirection.clear();
    }

    public int getRandomDirection() {
        if (excludedDirection.size() == 4) {
            return 4;
        }
        do {
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        } while (excludedDirection.contains(direction));
        return direction;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxCor() {
        return xCor;
    }

    public void setxCor(int xCor) {
        this.xCor = xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public void setyCor(int yCor) {
        this.yCor = yCor;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
