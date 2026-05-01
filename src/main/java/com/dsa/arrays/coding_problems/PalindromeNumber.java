/**
 * Problem: Check if a given number is palindrome
 *
 * Method 1: By conversion to string
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Method 2: Without conversion to string
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package com.dsa.arrays.coding_problems;

public class PalindromeNumber {
    public static void main(String[] args) {
        int num = 121;

        System.out.println("Is number " + num + " palindrome ? " + solutionByConversionToString(num));

        System.out.println("Is number " + num + " palindrome ? " + solutionWithoutConversionToString(num));

    }

    public static boolean solutionByConversionToString(int num) {
        // Convert the number to a string
        String numStr = String.valueOf(num);
        // use 2 pointers. One moves from the front and the other from the back comparing the characters/digits
        // at each complementary position
        for (int i=0,j=numStr.length()-1; i<j ; i++, j--) {
            if (numStr.charAt(i)!=numStr.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean solutionWithoutConversionToString(int num) {
        if (num < 0) {
            return false;
        }

        int temp = num;
        int reverse = 0;
        // the loop reverses the number
        while (temp > 0) {
            // reverse*10 moves the digits in the existing number one step to the left.
            // Eg: if 3 is present in reverse, it becomes 30. 3 moves left to the ten's place
            // Remainder gets the last digit. Eg: in 123, 3 is the last digit
            reverse =  reverse*10 + temp%10;
            // Quotient gets the digits other than the last digit
            temp = temp/10;
        }

        return reverse==num;
    }

}
