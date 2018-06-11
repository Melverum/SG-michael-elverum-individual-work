package com.sg.dvdlibrary.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Melve
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);
    Random random;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        double temp = 0;

        do {
            System.out.println(prompt);
            temp = Float.parseFloat(sc.nextLine());

        } while (Double.isNaN(temp) == true);

        return temp;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        double temp = 0;

        try {
            System.out.println(prompt);
            temp = Double.parseDouble(sc.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
        if (temp <= max && temp >= min) {

        } else {
            temp = 0;
        }

        return temp;
    }

    @Override
    public float readFloat(String prompt) {
        float temp = 0;

        do {
            System.out.println(prompt);
            temp = Float.parseFloat(sc.nextLine());

        } while (Float.isNaN(temp) == true);

        return temp;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean validNumber = false;
        boolean inRange = false;
        float temp = 0;

        do {
            try {
                System.out.println(prompt);
                temp = Float.parseFloat(sc.nextLine());
                validNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
            if (temp <= max || temp >= min) {
                inRange = true;
            } else {
                inRange = false;
            }

        } while (inRange == false || validNumber == false);

        return temp;
    }

    @Override
    public int readInt(String prompt) {
        int temp = 0;
        boolean validNumber = false;
        do {
            try {
                System.out.println(prompt);
                temp = Integer.parseInt(sc.nextLine());
                validNumber = true;

            } catch (NumberFormatException e) {
                System.out.println("That is an invalid number!");
            }

        } while (validNumber == false);

        return temp;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        int temp = 0;

        try {
            System.out.println(prompt);
            temp = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
        if (temp <= max || temp >= min) {

        } else {
            temp = 0;
        }

        return temp;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean validNumber = false;
        boolean inRange = false;
        long temp = 0;

        do {
            try {
                System.out.println(prompt);
                temp = Long.parseLong(sc.nextLine());
                validNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
            if (temp <= max || temp >= min) {
                inRange = true;
            } else {
                inRange = false;
            }

        } while (inRange == false || validNumber == false);

        return temp;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    @Override
    public int readRandom(String prompt, int rangeMax, int rangeMin) {
        print(prompt);
        return random.nextInt(rangeMax) + rangeMin;
    }
    @Override
    public LocalDate readDate(String prompt){
        print(prompt);
        return  LocalDate.parse(sc.nextLine(), formatter);
        
    }

}
