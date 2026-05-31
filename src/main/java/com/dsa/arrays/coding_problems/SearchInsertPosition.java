/**
 * Problem:
 * Link: https://leetcode.com/problems/search-insert-position/
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 */
package com.dsa.arrays.coding_problems;

public class SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6, 10};
        int target = 4;
        int position = searchInsertLogarithmicTime(nums, target);
        System.out.println("The position where the number is present or can be inserted is " + position);
    }

    // Time Complexity: O(n)
    // linearly searches for the target and returns the index of the target if found or the index where it can be inserted if not found
    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] >= target) {
                break;
            }
        }
        return i;
    }

    // Time Complexity: O(log n)
    // uses binary search to find the target and returns the index of the target if found or the index where it can be inserted if not found
    public static int searchInsertLogarithmicTime(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle]==target)
                return middle;
            else if (nums[middle] < target)
                start = middle + 1;
            else
                end = middle - 1;
        }
        return start;
    }
}
