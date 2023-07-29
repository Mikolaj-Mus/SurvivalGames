package com.example.survivalgames;

public class Board {

    Character[][] board;

    public void createBoard(int row, int column) {
        board = new Character[row][column];
    }
}
