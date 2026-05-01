/**
 * Problem: Remove all occurrences of a given value from a given array
 * Link: https://leetcode.com/problems/remove-element/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package com.dsa.arrays.coding_problems;

public class RemoveElements {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        int noOfElements = removeElements(nums, val);
        System.out.println("No of elements that are not " + val + " is " + noOfElements);

    }

    public static int removeElements(int[] nums, int val) {
        // a pointer to write the elements that are not val into the right position
        int writeIndex = 0;
        // looping through the elements in the array using another pointer
        for (int i = 0; i < nums.length; i++) {
            // when the current element is not equal to val, we move the current element to the front
            if (nums[i] != val) {
                // additional check to avoid unnecessary swapping
                if (writeIndex != i) {
                    nums[writeIndex] = nums[i];
                }
                // writeIndex moves only when there is a POSSIBILITY of a swap
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
