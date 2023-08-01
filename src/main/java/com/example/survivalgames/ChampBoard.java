package com.example.survivalgames;

public class ChampBoard {

    private static int[] x;
    private static int[] y;

    public ChampBoard(int size) {
        x = new int[size];
        y = new int[size];
    }

    public static int[] getX() {
        return x;
    }

    public static void setX(int[] x) {
        ChampBoard.x = x;
    }

    public static int[] getY() {
        return y;
    }

    public static void setY(int[] y) {
        ChampBoard.y = y;
    }
}
