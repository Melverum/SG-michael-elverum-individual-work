//Michael Elverum
package com.sg.weekoneprojects;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int randomizedPercent;
        String randomizedBreed;
        String[] chosenDogBreeds = new String[5];
        float[] chosenPercent = new float[5];
        float sum = 0;
        float endSum = 0;

        String[] dogBreeds = {"German Shepard", "Beagle", "Bulldog", "Poodle", "Great Dane",
            "Yorkie", "Shitzu", "Austrailian Shepard", "Sheltie", "Black Lab", "Rottweiler", "Golden Retriever",
            "Siberian Husky", "Boxer", "Chihuahua", "Chow Chow"};

        int dogBreedIndex = (dogBreeds.length) - 1; //Setting the range for the Randomizer;

        System.out.println("What is your dogs name? ");
        String dogName = userInput.nextLine();
        System.out.println("Well then I have this highly reliable report on "
                + dogName + "'s prestigious background right here!");
        System.out.println("\n" + dogName + " is:");
        System.out.println("");

        for (int i = 0; i < 5; i++) {          //Run the for loop 5 times to get and display percent and breed
            int checkCount;
            do {
                checkCount = 0;                  //Set a checker to see if we have duplicate breeds
                randomizedBreed = dogBreeds[randomValue(dogBreedIndex)];    //Call randomValue Method to retrieve a randomized dogBreed
                for (int j = 0; j < i; j++) {
                    if (randomizedBreed.equals(chosenDogBreeds[j])) {        //Step through array of chosen breeds to see if we have a duplicate with the randomBreed that we just got
                        checkCount++;                                       //If we do then redo the randomizer.
                    }
                }
            } while (checkCount != 0);

            chosenDogBreeds[i] = randomizedBreed; //  Put the dog breed into the position i into our array.

//            if (i < 4) {                          //If iteration is less than 4, get a randomized percent.
//                randomizedPercent = randomValue(percentIndex);
//                chosenPercent[i] = randomizedPercent;
//                percentIndex -= randomizedPercent;
//            } else {
//                randomizedPercent = percentIndex;//Else use the remaining percent
//                chosenPercent[i] = randomizedPercent;
//            }
            chosenPercent[i] = randomValue(10);
            sum += chosenPercent[i];
                
            
        }
        
        for (int j = 0; j < 5; j++){
            chosenPercent[j] = calculateRandomValues(sum, j, chosenPercent);
            endSum += chosenPercent[j];
        }
        if (endSum != 100){
            chosenPercent[4] += (100 - endSum);
        }
        
        sortPercentageAndBreed(chosenPercent, chosenDogBreeds);
    }

    public static void sortPercentageAndBreed(float[] chosenPercent, String[] chosenDogBreeds) {
        float tempPercent;
        String tempBreed;

        for (int j = 0; j < chosenPercent.length; j++) {   //Sort the percentages from highest to lowest
            for (int k = 0; k < chosenPercent.length - 1; k++) {
                if (chosenPercent[k] < chosenPercent[k + 1]) {
                    tempPercent = chosenPercent[k];
                    chosenPercent[k] = chosenPercent[k + 1];
                    chosenPercent[k + 1] = tempPercent;
                    tempBreed = chosenDogBreeds[k];     //Keeping the breed consistent with the percent.
                    chosenDogBreeds[k] = chosenDogBreeds[k + 1];
                    chosenDogBreeds[k + 1] = tempBreed;
                }
            }
           
        }
        
        for(int l = 0; l < 5; l++){
            System.out.println(chosenPercent[l] + "% " + chosenDogBreeds[l]);
        }
    }

    public static int randomValue(int index) {  //Get a random integer between 0 and index
        Random num = new Random();
        return num.nextInt(index) + 1;
    }
    
    public static float calculateRandomValues(float sum, int j, float[] chosenPercent){
        return Math.round((chosenPercent[j] * 100) / sum);
        
    }
}
