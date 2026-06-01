/**
 * Link: https://leetcode.com/problems/plus-one/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1). Because a new array gets assigned only in a particular case which is a rare case
 */
package com.dsa.arrays.coding_problems;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        int[] nums = {8,9,9};
        System.out.println("Given array: " + Arrays.toString(nums));
        int[] afterAddingOne = plusOne(nums);
        System.out.println("After adding one the array becomes, " + Arrays.toString(afterAddingOne));
    }

    public static int[] plusOne(int[] nums) {
        int carryover = 1; // Initialising to 1 since anyway we have to add 1
        int i = nums.length - 1; // Starting from the last element in the array. That is where we have to add 1

        do {
            // if sum is 10, assign 0 to that position. carryover remains one.
            // else assign the sum to that position and assign 0 to carryover
            if (nums[i] + carryover == 10) {
                nums[i] = 0;
            } else {
                nums[i] = nums[i] + carryover;
                carryover = 0;
            }
            i--;
        } while (carryover != 0 && i >= 0); // carryover should not be zero and index should be within limits

        // if for example 999+1=1000, carryover remains 1 even after loop ends, in which case we create a new array
        // by default all positions of an array have value 0. so it's enough to assign 1 at the beginning
        if (carryover != 0) {
            int[] newNums = new int[nums.length + 1];
            newNums[0] = 1;
            return newNums;
        }

        return nums;
    }

}
