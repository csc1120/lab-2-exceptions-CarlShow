/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;

/**
 *   If you forgot to roll the die before randomizing its number, throw this.
 */
public class DieNotRolledException extends Exception {
    /**
     *   Establishes the exception
     */
    public DieNotRolledException() {
        super("Die is unrolled: Null value");
    }
}
