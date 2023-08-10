package com.example.survivalgames;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ChampTest {

    @Test
    public void testCreateChamp() {
        // Arrange
        int id = 1;
        int x = 2;
        int y = 3;

        // Act
        Champ champ = new Champ(id, x, y);

        // Assert
        assertEquals(id, champ.getId());
        assertEquals(x, champ.getxCor());
        assertEquals(y, champ.getyCor());
        assertEquals(1, champ.getStrength()); // Verify default strength
        assertFalse(champ.isDefeated()); // Verify default defeated status
        assertNull(champ.getColor()); // Verify default color is null
    }

    @Test
    public void testMove() {
        // Arrange
        Champ champ = new Champ(1, 2, 2); // Starting position (2, 2)
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(2, 2), champ);
        champMap.put(Mechanics.getPositionKey(2, 1), new Champ(2, 2, 1)); // Adjacent champion at (2, 1)
        champMap.put(Mechanics.getPositionKey(3, 2), new Champ(2, 2, 1)); // Adjacent champion at (2, 1)
        champMap.put(Mechanics.getPositionKey(1, 2), new Champ(2, 2, 1)); // Adjacent champion at (2, 1)

        // Act
        champ.move(champMap);

        // Assert
        assertEquals(2, champ.getxCor());
        assertEquals(3, champ.getyCor());
    }

    @Test
    public void testBorder() {

        // Arrange
        Champ champ = new Champ(1, 9, 9);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(9, 9), champ);

        // Act
        champ.move(champMap);

        // Assert
        assertNotEquals(Mechanics.CELLS, champ.getxCor());
        assertNotEquals(Mechanics.CELLS, champ.getyCor());

    }



}
