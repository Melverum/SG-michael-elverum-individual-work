//Michael Elverum
package com.sg.weekoneprojects;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        System.out.println("*** Welcome to Rock, Paper, Scissors ***");

        do {    //Run the Game as long as the user wants to keep playing. 
            int userWin = 0, tie = 0, cpuWin = 0, plays = 0;
            int userDecision = 0, cpuDecision;
            System.out.println("");
            plays = getPlays(plays); //Call the getPlays method
            System.out.println("");
            for (int i = 0; i < plays; i++) {  //Run the loop as long as i is less than the number of plays
                userDecision = getUserDecision(userDecision);  //Get the usersDecision
                cpuDecision = getRandomDecision();             //Get the CPU's randomized decision

                //Figure out who won the round. Then add it to that persons total wins
                if (userDecision == cpuDecision) {
                    System.out.println("Round (" + (i + 1) + ") is a tie!");
                    tie++;
                } else if (userDecision == 1) {
                    if (cpuDecision == 2) {
                        System.out.println("Round (" + (i + 1) + ") is a CPU win ");
                        cpuWin++;
                    } else {
                        System.out.println("Round (" + (i + 1) + ") is a User win ");
                        userWin++;

                    }
                } else if (userDecision == 2) {
                    if (cpuDecision == 1) {
                        userWin++;
                        System.out.println("Round (" + (i + 1) + ") is a User win ");
                    } else {
                        cpuWin++;
                        System.out.println("Round (" + (i + 1) + ") is a CPU win ");
                    }
                } else if (userDecision == 3) {
                    if (cpuDecision == 1) {
                        cpuWin++;
                        System.out.println("Round (" + (i + 1) + ") is a CPU win ");
                    } else {
                        userWin++;
                        System.out.println("Round (" + (i + 1) + ") is a User win ");
                    }
                }
                System.out.println("");
            }
            // Out put the results.
            System.out.println("Number of Ties:       " + tie);
            System.out.println("Number of CPU Wins:   " + cpuWin);
            System.out.println("Number of User Wins:  " + userWin);
            //Determine the Winner
            if (userWin > cpuWin) {
                System.out.println("Congratulations! You are the Winner.");
            } else if (userWin < cpuWin) {
                System.out.println("Shoot! You lost this round.");
            } else {
                System.out.println("Its a tie! You should probably play again.");
            }
            System.out.println("");
        } while (playAgain() == true);   //Call the playAgain method to see if the user wants to play again.

        System.out.println("Thanks for Playing!");
    }

    public static int getPlays(int plays) { //Get the number of plays
        Scanner userPlays = new Scanner(System.in);
        do {
            System.out.println("How many times would you like to play? (1-10) ");
            plays = Integer.parseInt(userPlays.nextLine());
        } while (plays > 10 || plays < 1);

        return plays;
    }

    public static int getUserDecision(int userDecision) {//Get the users decision
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("Plese enter your choice: (1)Rock  (2) Paper  (3) Scissors");
            userDecision = Integer.parseInt(userInput.nextLine());
           
        } while (userDecision < 1 || userDecision > 3);

        return userDecision;
    }

    public static int getRandomDecision() {  //Get a randomized decision by the CPU
        Random cpuRandom = new Random();
        return cpuRandom.nextInt(3) + 1;
    }

    public static boolean playAgain() {  //

        String answer;
        int ansToInt = 0;
        boolean playAgain = false;
        //Ask the user if they want to play agian
        do {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Would you like to play again? (Yes/No)");
            answer = userInput.nextLine();
            if ("yes".equalsIgnoreCase(answer)) {  //Making sure that the user enters either yes or no
                ansToInt = 1;
                playAgain = true;
            } else if ("no".equalsIgnoreCase(answer)) {
                ansToInt = 1;
                playAgain = false;
            }

        } while (ansToInt != 1);  //If the answer doesn't equal yes or no then ask them again. 

        return playAgain;
    }
}

