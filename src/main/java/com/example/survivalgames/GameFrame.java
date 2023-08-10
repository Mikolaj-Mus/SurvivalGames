package com.example.survivalgames;


import javax.swing.*;

public class GameFrame extends JFrame{

    GameFrame() {
        this.add(new Mechanics());
        this.setTitle("Survival Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
