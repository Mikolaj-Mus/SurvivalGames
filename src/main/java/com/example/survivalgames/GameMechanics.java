package com.example.survivalgames;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GameMechanics extends Application {

    public static final int BOARD_SIZE = 10;
    private static final int CELL_SIZE = 50;
    Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        board = new Board(BOARD_SIZE);


        GridPane gridPane = new GridPane(); //pole na ktore dodaje sie siatke
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: black;");

        int gridPadding = 5;
        gridPane.setPadding(new javafx.geometry.Insets(gridPadding));

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                AnchorPane anchorPane = createAnchorPane();
                gridPane.add(anchorPane, col, row);
            }
        }

        Scene scene = new Scene(gridPane, CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);

        stage.setTitle("Survival Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(551);
        stage.setHeight(555);
        stage.show();
    }

    private AnchorPane createAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(CELL_SIZE, CELL_SIZE);
        anchorPane.setStyle("-fx-background-color: white;");
        return anchorPane;
    }



}
