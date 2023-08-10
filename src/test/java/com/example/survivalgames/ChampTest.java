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
        assertEquals(1, champ.getStrength());
        assertFalse(champ.isDefeated());
        assertNull(champ.getColor());
    }

    @Test
    public void testMoveOnAnotherChamp() {
        // Arrange
        Champ champ = new Champ(1, 2, 2);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(2, 2), champ);
        champMap.put(Mechanics.getPositionKey(2, 1), new Champ(2, 2, 1));
        champMap.put(Mechanics.getPositionKey(3, 2), new Champ(3, 2, 1));
        champMap.put(Mechanics.getPositionKey(1, 2), new Champ(1, 2, 1));

        // Act
        champ.move(champMap);

        // Assert
        assertEquals(2, champ.getxCor());
        assertEquals(3, champ.getyCor());
    }

    @Test
    public void testUpdateMove() {

        // Arrange
        Champ champ = new Champ(1, 5, 5);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(5, 5), champ);

        // Act
        champ.move(champMap);

        // Assert
        if (champ.getxCor() == 5) {
            assertTrue(champ.getyCor() == 4 || champ.getyCor() == 6);
        }
        if (champ.getyCor() == 5) {
            assertTrue(champ.getxCor() == 4 || champ.getyCor() == 6);
        }
    }

    @Test
    public void testChampHasNoMove() {
        // Arrange
        Champ champ = new Champ(1, 3, 3);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(3, 3), champ);
        champMap.put(Mechanics.getPositionKey(2, 3), new Champ(2, 2, 3));
        champMap.put(Mechanics.getPositionKey(3, 2), new Champ(3, 3, 2));
        champMap.put(Mechanics.getPositionKey(4, 3), new Champ(4, 4, 3));
        champMap.put(Mechanics.getPositionKey(3, 4), new Champ(5, 3, 4));

        // Act
        champ.move(champMap);

        // Assert
        assertEquals(3, champ.getxCor());
        assertEquals(3, champ.getyCor());
    }

    @Test
    public void testChampMoveNextToBorder() {

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

    @Test
    public void testStrengthAfterFight() {
        // Arrange
        Champ champ1 = new Champ(1, 2, 2);
        Champ champ2 = new Champ(2, 3, 2);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(2, 2), champ1);
        champMap.put(Mechanics.getPositionKey(3, 2), champ2);

        // Act
        champ1.fight(champMap);

        // Assert
        if (champ1.isDefeated()) {
            assertEquals(2, champ2.getStrength());
        } else {
            assertEquals(2, champ1.getStrength());
        }
    }

    @Test
    public void testFight() {
        // Arrange
        Champ champ1 = new Champ(1, 3, 4);
        Champ champ2 = new Champ(2, 4, 4);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(3, 4), champ1);
        champMap.put(Mechanics.getPositionKey(4, 4), champ2);

        // Act
        champ1.fight(champMap);

        // Assert
        assertTrue(champ1.isDefeated() || champ2.isDefeated());
    }
}
