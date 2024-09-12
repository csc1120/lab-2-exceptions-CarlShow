/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;

import java.util.Random;

public class Die {
    int sides;
    int currentValue;
    Random roller = new Random();
    public Die(int sides) {
        if(sides <= 100 && sides >= 2) {
            this.sides = sides;
        } else {
            throw new IllegalArgumentException("Sides must be between 2 and 100 inclusive");
        }
    }
    public void roll() {
        currentValue = (Math.abs(roller.nextInt()) % sides + 1) + 1;
    }
    public int getValue() throws DieNotRolledException {
        try {
            return currentValue;
        } catch (NullPointerException e) {
            throw new DieNotRolledException();
        }
    }
}