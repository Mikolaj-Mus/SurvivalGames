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
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + board[i][j].getId() + "]");
                }
            }
        }
    }

    public void randMove() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    Champion temp = board[i][j];
                    int maxAttempts = 10; // Maksymalna liczba prób


                    double randomMove = Math.random() * 4;
                    int move = (int) randomMove;

                    switch (move) {
                        case 0:
                            if (i + 1 < board.length && board[i+1][j] == null) {
                                board[i][j] = board[i + 1][j];
                                board[i + 1][j] = temp;
                                break;
                            }

                        case 1:
                            if (i - 1 >= 0 && board[i-1][j] == null) {
                                board[i][j] = board[i - 1][j];
                                board[i - 1][j] = temp;
                                break;
                            }

                        case 2:
                            if (j + 1 < board[i].length && board[i][j+1] == null) {
                                board[i][j] = board[i][j + 1];
                                board[i][j + 1] = temp;
                                break;
                            }

                        case 3:
                            if (j - 1 >= 0 && board[i][j-1] == null) {
                                board[i][j] = board[i][j - 1];
                                board[i][j - 1] = temp;
                                break;
                            }
                        default:
                            break;

                    }
                }
            }
        }
    }


    public Champion[][] getBoard() {
        return board;
    }

    public void setBoard(Champion[][] board) {
        Board.board = board;
    }
}
