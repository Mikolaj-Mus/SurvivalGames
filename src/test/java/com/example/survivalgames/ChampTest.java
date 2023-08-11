package com.example.survivalgames;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ChampTest {

    // Tests the creation of a Champ instance and its initial properties.
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

    // Tests moving a Champ instance to a new position where another Champ is present.
    @Test
    public void testMoveOnAnotherChamp() {

        Champ champ = new Champ(1, 2, 2);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(2, 2), champ);
        champMap.put(Mechanics.getPositionKey(2, 1), new Champ(2, 2, 1));
        champMap.put(Mechanics.getPositionKey(3, 2), new Champ(3, 2, 1));
        champMap.put(Mechanics.getPositionKey(1, 2), new Champ(1, 2, 1));

        champ.move(champMap);

        assertEquals(2, champ.getxCor());
        assertEquals(3, champ.getyCor());
    }

    // Tests updating the position of a Champ instance after moving.
    @Test
    public void testUpdateMove() {

        Champ champ = new Champ(1, 5, 5);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(5, 5), champ);

        champ.move(champMap);

        if (champ.getxCor() == 5) {
            assertTrue(champ.getyCor() == 4 || champ.getyCor() == 6);
        }
        if (champ.getyCor() == 5) {
            assertTrue(champ.getxCor() == 4 || champ.getyCor() == 6);
        }
    }

    // Tests that a Champ instance does not move when surrounded by other Champs.
    @Test
    public void testChampHasNoMove() {

        Champ champ = new Champ(1, 3, 3);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(3, 3), champ);
        champMap.put(Mechanics.getPositionKey(2, 3), new Champ(2, 2, 3));
        champMap.put(Mechanics.getPositionKey(3, 2), new Champ(3, 3, 2));
        champMap.put(Mechanics.getPositionKey(4, 3), new Champ(4, 4, 3));
        champMap.put(Mechanics.getPositionKey(3, 4), new Champ(5, 3, 4));

        champ.move(champMap);

        assertEquals(3, champ.getxCor());
        assertEquals(3, champ.getyCor());
    }

    // Tests that a Champ instance cannot move beyond the game borders.
    @Test
    public void testChampMoveNextToBorder() {

        Champ champ = new Champ(1, 9, 9);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(9, 9), champ);

        champ.move(champMap);

        assertNotEquals(Mechanics.CELLS, champ.getxCor());
        assertNotEquals(Mechanics.CELLS, champ.getyCor());

    }

    // Tests the strength of Champs after a fight.
    @Test
    public void testStrengthAfterFight() {

        Champ champ1 = new Champ(1, 2, 2);
        Champ champ2 = new Champ(2, 3, 2);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(2, 2), champ1);
        champMap.put(Mechanics.getPositionKey(3, 2), champ2);

        champ1.fight(champMap);

        if (champ1.isDefeated()) {
            assertEquals(2, champ2.getStrength());
        } else {
            assertEquals(2, champ1.getStrength());
        }
    }

    // Tests the outcome of a fight between two Champs.
    @Test
    public void testFight() {
        Champ champ1 = new Champ(1, 3, 4);
        Champ champ2 = new Champ(2, 4, 4);
        HashMap<String, Champ> champMap = new HashMap<>();
        champMap.put(Mechanics.getPositionKey(3, 4), champ1);
        champMap.put(Mechanics.getPositionKey(4, 4), champ2);

        champ1.fight(champMap);

        assertTrue(champ1.isDefeated() || champ2.isDefeated());
    }
}
