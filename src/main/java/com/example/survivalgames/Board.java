package com.example.survivalgames;

public class Board {

    private static Champion[][] board;

    public Board(int rows) {
        board = new Champion[rows][rows];
    }

    public boolean checkWinner() {
        int counter = 0;
        for (Champion[] champions : board) {
            for (Champion champion : champions) {
                if (champion != null) {
                    counter++;
                }
            }
        }
        return counter == 1;
    }

    public void fulfillBoard() {
        int num = 0;
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[i].length; j += 3) {
                board[i][j] = new Champion(num);
                num++;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    System.out.print("---");
                } else {
                    System.out.print("-" + board[i][j].getId() + "-");
                }
            }
        }
    }

    public void randMove() {


    }

    public Champion[][] getBoard() {
        return board;
    }

    public void setBoard(Champion[][] board) {
        Board.board = board;
    }
}
