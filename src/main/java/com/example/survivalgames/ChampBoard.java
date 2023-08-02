package com.example.survivalgames;


public class ChampBoard {
    private static Champ[][] board;

    public ChampBoard() {
        board = new Champ[Mechanics.returnGAME_UNITS()][Mechanics.returnGAME_UNITS()];
    }

    public static Champ[][] getBoard() {
        return board;
    }

    public static void setBoard(Champ[][] board) {
        ChampBoard.board = board;
    }
}
