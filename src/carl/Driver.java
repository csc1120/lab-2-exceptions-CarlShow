/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Carl Show
 * Last Updated: September 12, 2024
 */
package carl;

import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        try {
            instantiateDice();
        } catch (IllegalArgumentException e) {
            instantiateDice();
        }
    }
    public static void instantiateDice() {
        int[] temp = getInput();
        Die dice = createDice(temp[1]);
        rollDice(dice, temp[0], temp[2]);
    }
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
    public static Die createDice(int sides) throws IllegalArgumentException {
        return new Die(sides);
    }
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
            String workingString = "";
            workingString += i + "\t: " + outputCount[i] + "\t";
            for(int j = 0; j < outputCount[i]/perUnit; j++) {
                workingString += "*";
            }
            System.out.println(workingString);
        }
    }
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