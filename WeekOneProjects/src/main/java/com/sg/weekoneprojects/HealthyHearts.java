//Michael Elverum
package com.sg.weekoneprojects;
import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        
        Scanner inputScanner = new Scanner(System.in);
       
        System.out.println("What is your age?");
        int age = Integer.parseInt(inputScanner.next());  //Get users age
        
        int maximumHeartRate = 220 - age;   //Math to determine requested HeartRate information
        double targetLow = maximumHeartRate * .50;
        double targetHigh = maximumHeartRate * .85;
       // Output information to the console
        System.out.println("Your maximum heart rate should be " + maximumHeartRate + " beats per minute" ); 
        System.out.println("Your target HR Zone is " + Math.round(targetLow) + " - " + Math.round(targetHigh) + " beats per minute");
    
}
}