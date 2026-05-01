/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 *
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 *
 * Constraints:
 *
 * -2^31 <= x <= 2^31 - 1
 *
 * Follow up: Could you solve it without converting the integer to a string?
 */

package com.dsa.arrays.coding_problems;

public class PalindromeNumber {
    public static void main(String[] args) {
        int num = 121;

        System.out.println("Is number " + num + " palindrome ? " + solutionByConversionToString(num));

        System.out.println("Is number " + num + " palindrome ? " + solutionWithoutConversionToString(num));

    }

    public static boolean solutionByConversionToString(int num) {
        String numStr = String.valueOf(num);

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
        while (temp > 0) {
            reverse =  reverse*10 + temp%10;
            temp = temp/10;
        }

        return reverse==num;
    }

}
