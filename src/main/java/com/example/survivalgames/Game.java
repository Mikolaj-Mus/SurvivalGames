package com.example.survivalgames;

import java.util.Scanner;

public class Game {

    Champion champion;
    static Board board;

    public static void main(String[] args) {

        board = new Board(10);
        board.fulfillBoard();
        board.printBoard();
        Scanner scanner = new Scanner(System.in);

        while(!board.checkWinner()) {

            board.randMove();
            System.out.println();
            board.printBoard();
            scanner.nextLine();

        }
    }
}
