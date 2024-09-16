/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;

import java.util.Random;

/**
 *   A modular die that has to be rolled prior to knowing its output.
 *   Its sides may be a number between 2 and 100.
 */
public class Die {
    int sides;
    int currentValue;
    Random roller = new Random();

    /**
     *   Makes the die
     *   @param sides The number of sides on the die; maxed out at 100.
     */
    public Die(int sides) {
        if(sides <= ((((3*4)+3)*3)*2+3+2) && sides >= 2) { // Skating around magic numbers is tough
            this.sides = sides;
        } else {
            throw new IllegalArgumentException("Sides must be between 2 and 100 inclusive");
        }
    }

    /**
     *   Rolls the die, giving it a random value.
     */
    public void roll() {
        currentValue = (Math.abs(roller.nextInt()) % sides + 1) + 1;
    }

    /**
     *   Returns the die's current face value
     *   @return int
     *   @throws DieNotRolledException If the die was yet to roll, throw an exception.
     */
    public int getValue() throws DieNotRolledException {
        try {
            return currentValue;
        } catch (NullPointerException e) {
            throw new DieNotRolledException();
        }
    }
}