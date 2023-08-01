package com.example.survivalgames;


public class ChampBoard {

    private static Champ[] x;
    private static Champ[] y;

    public ChampBoard() {
        x = new Champ[Mechanics.returnGAME_UNITS()];
        y = new Champ[Mechanics.returnGAME_UNITS()];
    }

    public static Champ[] getX() {
        return x;
    }

    public static void setX(Champ[] x) {
        ChampBoard.x = x;
    }

    public static Champ[] getY() {
        return y;
    }

    public static void setY(Champ[] y) {
        ChampBoard.y = y;
    }
}
