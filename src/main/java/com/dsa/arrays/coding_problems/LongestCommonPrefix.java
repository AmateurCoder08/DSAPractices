/**
 * Problem: Longest common prefix.
 * Link: https://leetcode.com/problems/longest-common-prefix/
 *
 * Time Complexity: O(n · m)
 * Space Complexity: O(1)
 */
package com.dsa.arrays.coding_problems;

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strArr = new String[]{"flower", "flow", "flight"};
        String[] strArr2 = new String[]{"dog", "car", "racecar"};
        System.out.println("Longest common prefix among " + Arrays.toString(strArr) + " : " + findLongestCommonPrefix(strArr));
        System.out.println("Longest common prefix among " + Arrays.toString(strArr2) + " : " + findLongestCommonPrefix(strArr2));
    }

    public static String findLongestCommonPrefix(String[] arrStr) {
        // Assume the length of the first string to be the prefix
        int prefixLength = arrStr[0].length();

        // Then compare with the other strings and modify the prefix length to suit the common prefix
        // i loops through the other strings
        for (int i = 1; i < arrStr.length; i++) {
            // j loops through the individual characters up until the condition
            for (int j = 0; j < prefixLength && j < arrStr[i].length(); j++) {
                if (arrStr[i].charAt(j) != arrStr[0].charAt(j)) {
                    // j is the position where the characters don't match
                    prefixLength = j;
                    break;
                }
            }
        }
        // So, substring is from 0 to where the characters didn't match in the end
        return arrStr[0].substring(0, prefixLength);
    }
}
