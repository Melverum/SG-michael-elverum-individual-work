//Michael Elverum
package com.sg.weekoneprojects;

public class SummativeSums {

    public static void main(String[] args) {

        int[][] numberArray = {
            {1, 90, -33, -55, 67, -16, 28, -55, 15},
            {999, -60, -77, 14, 160, 301},
            {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99}
        };

        for (int i = 0; i < numberArray.length; i++) { //Loop as long as i is less than number of Arrays in the Array
            //Display and Call Method getSum
            System.out.println("#" + (i + 1) + " Array Sum: " + getSum(numberArray, i));
        }
    }

    public static int getSum(int[][] numberArray, int i) { //Pass in numberArray and int i as parameters
        int sum = 0;
        for (int j = 0; j < numberArray[i].length; j++) {
            sum += numberArray[i][j];
        }
        return sum;  //Return sum
    }
}
