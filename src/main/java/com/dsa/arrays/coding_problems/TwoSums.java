/**
 * Problem: Two Sum. Return the indices of numbers that add up to the target
 * Link: https://leetcode.com/problems/two-sum/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
package com.dsa.arrays.coding_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSums {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] indices = twoSums(nums, target);
        System.out.println(Arrays.toString(indices));
    }

    private static int[] twoSums(int[] nums, int target) {
        // hash map to track the current element and its position
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            // find the difference between current element and target
            int diff = target - curr;
            // if the difference is present in the map return the current index and the index of the difference
            if (map.containsKey(diff)) {
                return new int[]{i, map.get(diff)};
            }
            map.put(curr, i);
        }
        return null;
    }
}
