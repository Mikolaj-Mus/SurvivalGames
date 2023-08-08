package com.example.survivalgames;

import java.util.*;
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
    private final Set<Integer> excludedDirection = new HashSet<>();
    private boolean defeated = false;
    Font font = new Font("Arial", Font.BOLD, Mechanics.UNIT_SIZE / 2);

    public Champ(int id, int x, int y) {
        this.id = id;
        this.xCor = x;
        this.yCor = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xCor * Mechanics.UNIT_SIZE, yCor * Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE, Mechanics.UNIT_SIZE);



//        g.setColor(Color.BLACK);
//        g.setFont(font);
//        g.drawString(String.valueOf(id), xCor * Mechanics.UNIT_SIZE + Mechanics.UNIT_SIZE / 3, yCor * Mechanics.UNIT_SIZE + 2 * (Mechanics.UNIT_SIZE / 3));
    }

    /*
    [0] - UP
    [1] - DOWN
    [2] - LEFT
    [3] - RIGHT
     */
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

    public void fight(HashMap<String, Champ> champMap) {
        if (defeated) {
//            Mechanics.removeChampion(this);
            return;
        }

        Champ opponent = getAdjacentChampion(xCor, yCor, champMap);
        if (opponent != null) {
            System.out.println("--------------");
            Champ winner = Mechanics.random.nextBoolean() ? this : opponent;
            Champ loser = winner == this ? opponent : this;
            System.out.println(winner.id);
            System.out.println(loser.id);
            loser.setDefeated(true);
            Mechanics.removeChampion(loser);
        }
    }

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


    public void updatePosition(int newX, int newY, HashMap<String, Champ> champMap) {
        champMap.remove(Mechanics.getPositionKey(xCor, yCor));
        xCor = newX;
        yCor = newY;
        champMap.put(Mechanics.getPositionKey(xCor, yCor), this);
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
}
