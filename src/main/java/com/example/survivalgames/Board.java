package com.example.survivalgames;

public class Board {

    private Champion[][] board;

    public Board(int rows) {
        board = new Champion[rows][rows];
    }

    public void fulfillBoard(Champion[][] board, Champion[] champions) {
        int numOfChampion = 0;
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[i].length; j += 3) {
                board[i][j] = champions[numOfChampion];
                numOfChampion++;
            }

        }
    }

    public static void printBoard(Champion[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
    }

    public Champion[][] getBoard() {
        return board;
    }

    public void setBoard(Champion[][] board) {
        this.board = board;
    }
}
