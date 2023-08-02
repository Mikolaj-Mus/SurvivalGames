package com.example.survivalgames;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.*;

public class Champ {
    private int id;
    private int xCor;
    private int yCor;
    private int strength = 1;
    private int direction;
    private Color color;

    public Champ(int id, int x, int y) {
        this.id = id;
        this.xCor = x;
        this.yCor = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xCor, yCor, Mechanics.returnUNIT_SIZE(), Mechanics.returnUNIT_SIZE());
    }

    public void move() {
        direction = ThreadLocalRandom.current().nextInt(0, 4);

        switch (direction) {
            case 0 -> yCor -= Mechanics.returnUNIT_SIZE();
            case 1 -> yCor += Mechanics.returnUNIT_SIZE();
            case 2 -> xCor -= Mechanics.returnUNIT_SIZE();
            case 3 -> xCor += Mechanics.returnUNIT_SIZE();
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
