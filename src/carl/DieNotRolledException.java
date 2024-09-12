/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;

public class DieNotRolledException extends Exception  {
    public DieNotRolledException() {
        super("Die is unrolled: Null value");
    }
}
