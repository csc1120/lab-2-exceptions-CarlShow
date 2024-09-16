/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;
import java.util.Scanner;

/**
 *   The driver class, instances the instantiate dice method
 *   and catches any errors it may throw.
 */
public class Driver {
    public static void main(String[] args) {
        boolean didComplete = false;
        do {
            try {
                didComplete = instantiateDice();
            } catch (IllegalArgumentException e) {
                System.out.println("Dice failed to be made: " + e.getMessage());
            } catch (NegativeArraySizeException e) {
                System.out.println("Dice failed to roll: Bad number of dice");
            }
        } while (!didComplete);
    }

    /**
     *   This is the parent method invokes all the other methods required to create the dice
     *   @return Returns true once completed without error
     */
    public static boolean instantiateDice() {
        int[] temp = getInput();
        Die dice = createDice(temp[1]);
        rollDice(dice, temp[0], temp[2]);
        return true;
    }

    /**
     *   Gathers user input and outputs it as an int[]
     *   @return int[], with [0] as the # of dice, [1] as the sides per die, and [2]
     *   as the number of rolls
     */
    public static int[] getInput() {
        Scanner scan = new Scanner(System.in);
        int[] output = new int[3];
        boolean successfull = false;
        while(!successfull) {
            System.out.println("""
                               Please enter the following:
                               (1) The number of dice you'd like to roll
                               (2) The number of sides per die (between 2 and 100)
                               (3) The number of rolls you'd like to test
                               Format your input as: (1) (2) (3)
                               """);
            String userInput = scan.nextLine();
            String[] split = userInput.split(" ");

            // Preliminary checks
            if(split.length > 3) {
                System.out.println("Bad input: more than 3 values");
            } else if (split.length < 3) {
                System.out.println("Bad input: less than 3 values");
            } else {
                try {
                    for (int i = 0; i < 3; i++) {
                        output[i] = Integer.parseInt(split[i]);
                    }
                    successfull = true;
                } catch(NumberFormatException e) {
                    System.out.println("Bad input: all 3 inputs should be integers");
                }
            }
        }
        return output;
    }

    /**
     *   A small method that makes the die
     *   @param sides The number of sides on the die
     *   @return The created die
     *   @throws IllegalArgumentException If an illegal amount of sides is given, it
     *   spits back an exception
     */
    public static Die createDice(int sides) throws IllegalArgumentException {
        return new Die(sides);
    }

    /**
     *   The final major method, this function rolls the dice based on input
     *   @param dice The created die to be rolled
     *   @param numDice The number of dice to be rolled
     *   @param rolls The times this set of dice should be rolled
     */
    public static void rollDice(Die dice, int numDice, int rolls) {
        int calcMax = numDice*dice.sides;
        int[] outputCount = new int[calcMax + 1];
        for(int i = 0; i < rolls; i++) {
            int tempTotal = 0;
            for(int j = 0; j < numDice; j++) {
                dice.roll();
                try {
                    tempTotal += dice.getValue();
                } catch (DieNotRolledException e) {
                    System.out.println("Catastrophic error: Dice not rolled exception");
                }
            }
            outputCount[tempTotal - numDice]++;
        }
        int indexMax = findMax(outputCount);
        double perUnit = ((double) outputCount[indexMax] / 10);
        if(rolls > 10)
        {
            perUnit = Math.floor(perUnit);
        }
        for (int i = numDice; i < outputCount.length; i++) {
            String workingString = i + "\t: " + outputCount[i] + "\t || ";
            for(int j = 0; j < Math.floor(outputCount[i]/perUnit); j++) {
                workingString += "*";
            }
            System.out.println(workingString);
        }
    }

    /**
     *   Finds the maximum within an int[]
     *   @param input The int[] to be searched
     *   @return int, the maximum.
     */
    public static int findMax(int[] input) {
        int indexOfMax = 0;
        int max = 0;
        for(int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                indexOfMax = i;
                max = input[i];
            }
        }
        return indexOfMax;
    }
}