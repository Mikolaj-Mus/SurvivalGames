package com.example.survivalgames;

import static com.example.survivalgames.Board.printBoard;

public class GameMechanics {

    public static void main(String[] args) {

        Board board = new Board(7);
        board.fulfillBoard(board.getBoard(), Champion.createCharacters(8));
        printBoard(board.getBoard());

    }

}
