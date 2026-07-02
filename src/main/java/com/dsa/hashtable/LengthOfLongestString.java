/**
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * The solution is a combination of sliding window and hash map
 * Time Complexity: O(n)
 * Space Complexity: O(min(n,m))
 * m - length of the character set
 */
package com.dsa.hashtable;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestString {

    public static void main(String[] args) {
        String s = "bbbbbbbb";
        int length = lengthOfLongestSubstring(s);
        System.out.printf("\nLength of the longest substring %d", length);
    }

    public static int lengthOfLongestSubstring(String s) {
        // a map that keeps track of the character and its last index encountered in the string
        Map<Character,Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int startIndexMax = 0, endIndexMax = 0; // These variables are needed only to print the substring
        int lastSeenIndex;
        for (int left = 0, right = 0; right <s.length(); right++) {
            // using get or default to avoid multiple lookups in the map. Once to check if character is present
            // and the other to get the index
            // an alternative is to use .containsKey() method directly in the if condition
            lastSeenIndex = lastSeen.getOrDefault(s.charAt(right), -1);
            if (lastSeenIndex != -1) {
                // if the last seen index is less than the current substring,
                // it means that there is no duplicate in the current substring
                // so the value of left remains the same otherwise it is updated
                left = Math.max(left, lastSeenIndex + 1);
            }
            lastSeen.put(s.charAt(right), right);
            // comparing the current length with max length at each iteration
            if ((right - left + 1) > maxLength) {
                maxLength = right - left + 1;
                startIndexMax = left;
                endIndexMax = right;
            }
        }

        // Additional answer where the longest substring without duplicates is printed
        System.out.print("Longest substring without duplicates, ");
        for (int i=startIndexMax; i<=endIndexMax; i++) {
            System.out.print(s.charAt(i));
        }

        return maxLength;
    }
}
