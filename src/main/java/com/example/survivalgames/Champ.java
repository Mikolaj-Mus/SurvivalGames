package com.example.survivalgames;

import java.util.ArrayList;
import java.util.List;
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
        g.fillOval(xCor * Mechanics.getUNIT_SIZE(), yCor * Mechanics.getUNIT_SIZE(), Mechanics.getUNIT_SIZE(), Mechanics.getUNIT_SIZE());
        g.drawString(String.valueOf(id), xCor * Mechanics.getUNIT_SIZE(), yCor * Mechanics.getUNIT_SIZE() + 8);
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

            switch (direction) {
                case 0 -> newY -= 1;
                case 1 -> newY += 1;
                case 2 -> newX -= 1;
                case 3 -> newX += 1;
            }
        } while (Mechanics.getOccupiedPositions().contains(Mechanics.getPositionKey(newX, newY)));

        updatePosition(newX, newY);
    }

    public void updatePosition(int newX, int newY) {
        Mechanics.getOccupiedPositions().add(Mechanics.getPositionKey(newX, newY));
        Mechanics.getOccupiedPositions().remove(Mechanics.getPositionKey(xCor, yCor));
        xCor = newX;
        yCor = newY;
        excludedDirection.clear();
    }

    public int getRandomDirection() {
        do {
            direction = ThreadLocalRandom.current().nextInt(0, 4);
        } while (excludedDirection.contains(direction));
        return direction;
    }

    public void checkBorder() {
        if (yCor == 0) {
            excludedDirection.add(0);
        }
        if (yCor == (int) Math.sqrt(Mechanics.getGAME_UNITS()) - 1) {
            excludedDirection.add(1);
        }
        if (xCor == 0) {
            excludedDirection.add(2);
        }
        if (xCor == (int) Math.sqrt(Mechanics.getGAME_UNITS()) - 1) {
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
