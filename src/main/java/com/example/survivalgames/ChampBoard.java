package com.example.survivalgames;


public class ChampBoard {
    private static final Champ[][] board = new Champ[(int)Math.sqrt(Mechanics.returnGAME_UNITS())][(int)Math.sqrt(Mechanics.returnGAME_UNITS())];


    public static Champ[][] getBoard() {
        return board;
    }

    public static void setCell (int i, int j, Champ value) {
        board[i][j] = value;
    }
}
