package com.example.survivalgames;
public class Game {

    Champion champion;
    static Board board;

    public static void main(String[] args) {

        board = new Board(10);
        board.fulfillBoard();
        board.printBoard();

        while(board.checkWinner()) {


        }


    }


}
