/**
 * Link: https://leetcode.com/problems/roman-to-integer/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package com.dsa.hashtable;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        String romanNumeral = "IV";
        int value = romanToInt(romanNumeral);
        System.out.printf("The roman numeral %s equates to %d", romanNumeral, value);
    }

    public static int romanToInt(String s) {
        Map<Character,Integer> romanNumeralsAndValues = new HashMap<>();
        romanNumeralsAndValues.put('I',1);
        romanNumeralsAndValues.put('V',5);
        romanNumeralsAndValues.put('X',10);
        romanNumeralsAndValues.put('L',50);
        romanNumeralsAndValues.put('C',100);
        romanNumeralsAndValues.put('D',500);
        romanNumeralsAndValues.put('M',1000);

        int sum = 0;
        int valueAtIndex;
        int valueAtPreviousIndex = 0;

        for (int i=0; i<s.length(); i++) {
            valueAtIndex = romanNumeralsAndValues.get(s.charAt(i));
            if (valueAtPreviousIndex < valueAtIndex) {
                sum = sum - valueAtPreviousIndex;
                sum = sum + (valueAtIndex - valueAtPreviousIndex);
            } else {
                sum = sum + valueAtIndex;
            }
            valueAtPreviousIndex = valueAtIndex;
        }

        return sum;
    }
}
