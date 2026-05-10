/**
 * An implementation of binary search algorithm.
 * The position/index of the number being searched is returned. If the number is not found -1 is returned.
 * The binary search algorithm expects a SORTED ARRAY to be its input
 *
 * Time complexity: O(log n)
 * Space Complexity: O(1)
 */
package com.dsa.algorithms;

import java.util.Arrays;

public class BinarySearchImpl {
    public static void main(String[] args) {
        int[] sortedArr = {1,2,3,4,5,6,7,8,9,10};
        int target = 11;

        int index = binarySearch(sortedArr, target);
        if (index==-1) {
            System.out.println("The number " + target + " is not present in the array " + Arrays.toString(sortedArr));
        } else {
            System.out.println("The number " + target + " is present in the array " + Arrays.toString(sortedArr) + " at index " + index);
        }
    }

    public static int binarySearch(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        int middle;
        while (startIndex <= endIndex) {
            middle = (startIndex + endIndex) / 2;
            if (nums[middle] == target)
                return middle;
            else if (nums[middle] < target)
                startIndex = middle + 1;
            else
                endIndex = middle - 1;
        }
        return -1;
    }
}
