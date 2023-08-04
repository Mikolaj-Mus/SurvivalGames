package com.example.survivalgames;


public class ChampBoard {
    private static final Champ[][] board = new Champ[(int)Math.sqrt(Mechanics.returnGAME_UNITS())][(int)Math.sqrt(Mechanics.returnGAME_UNITS())];


    public static Champ[][] getBoard() {
        return board;
    }

    public static void setCell (int i, int j, Champ value) {
        board[i][j] = value;
    }

    public static void printBoard() {
        for (int i = 0; i < getBoard().length; i++) {
            System.out.println();
            for (int j = 0; j < getBoard()[i].length; j++) {
                if (getBoard()[i][j] == null) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print("X" + " ");
                }
            }
        }
        System.out.println();
    }
}
