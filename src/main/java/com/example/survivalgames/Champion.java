package com.example.survivalgames;

public class Champion {
    private int id;
    private int strength;

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


}
