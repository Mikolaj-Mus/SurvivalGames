package com.example.survivalgames;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;


public class MechanicsTest {

    @Test
    public void testGameInitialization() {

        Mechanics mechanics = new Mechanics();

        assertNotNull(mechanics);
        assertTrue(mechanics.isFocusable());
    }

    @Test
    public void testGameStartOnEnter() {
        Mechanics mechanics = new Mechanics();
        KeyListener keyListener = mechanics.getKeyListeners()[0];

        KeyEvent enterEvent = new KeyEvent(mechanics, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        keyListener.keyPressed(enterEvent);

        assertTrue(mechanics.isRunning());
    }

    @Test
    public void testGamePauseOnSpace() {
        Mechanics mechanics = new Mechanics();
        mechanics.setRunning(true); // Set the game to running
        KeyListener keyListener = mechanics.getKeyListeners()[0]; // Get the key listener

        KeyEvent spaceEvent = new KeyEvent(mechanics, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        keyListener.keyPressed(spaceEvent);

        assertFalse(mechanics.isRunning()); // Check if the game is paused after pressing Space
    }

    @Test
    public void testCreateChampsExceedingGameUnits() {
        Mechanics mechanics = new Mechanics();

        assertThrows(IllegalArgumentException.class, () -> {
            mechanics.createChamps(Mechanics.GAME_UNITS + 1);
        });
    }
}
