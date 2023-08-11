package com.example.survivalgames;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;


public class MechanicsTest {

    // Tests the initialization of the game mechanics.
    @Test
    public void testGameInitialization() {

        Mechanics mechanics = new Mechanics();

        assertNotNull(mechanics);
        assertTrue(mechanics.isFocusable());
    }

    // Tests starting the game when the Enter key is pressed.
    @Test
    public void testGameStartOnEnter() {
        Mechanics mechanics = new Mechanics();
        KeyListener keyListener = mechanics.getKeyListeners()[0];

        KeyEvent enterEvent = new KeyEvent(mechanics, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        keyListener.keyPressed(enterEvent);

        assertTrue(mechanics.isRunning());
    }

    // Tests pausing the game when the Space key is pressed.
    @Test
    public void testGamePauseOnSpace() {
        Mechanics mechanics = new Mechanics();
        mechanics.setRunning(true);
        KeyListener keyListener = mechanics.getKeyListeners()[0];

        KeyEvent spaceEvent = new KeyEvent(mechanics, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        keyListener.keyPressed(spaceEvent);

        assertFalse(mechanics.isRunning());
    }

    // Tests attempting to create more Champs than allowed by the game units.
    @Test
    public void testCreateChampsExceedingGameUnits() {
        Mechanics mechanics = new Mechanics();

        assertThrows(IllegalArgumentException.class, () -> mechanics.createChamps(Mechanics.GAME_UNITS + 1));
    }
}
