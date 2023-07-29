package com.example.survivalgames;

public class Champion {
    private int id;
    private int strength;
    private static Champion[] champions;

    public Champion(int id) {
        this.id = id;
        this.strength = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public static Champion[] createCharacters(int amount) {
        champions = new Champion[amount];
        for (int i = 0; i < amount; i++) {
            champions[i] = new Champion(i);
        }
        return champions;
    }
}
