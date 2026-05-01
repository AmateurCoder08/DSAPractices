/**
 * Problem: Remove Duplicates from Sorted Array
 * Source: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package com.dsa.arrays.coding_problems;

public class RemoveDuplicatesSortedArray {
    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] expectedArr = {0, 1, 2, 3, 4};
        int noOfUniqueElements = removeDuplicates(arr);
        if (expectedArr.length == noOfUniqueElements) {
            for (int i = 0; i < expectedArr.length; i++) {
                if (arr[i] != expectedArr[i]) {
                    System.out.println("The actual array does not match the expected array. The array is sorted incorrectly");
                    System.exit(0);
                }
            }
            System.out.println("No. of unique elements in the array: " + noOfUniqueElements);
        } else {
            System.out.println("Incorrect number of unique elements. Expected: " + expectedArr.length + " Actual: " + noOfUniqueElements);
        }
    }

    public static int removeDuplicates(int[] array) {
        // A pointer to track the index where the next unique element has to be written
        int writeIndex = 1;
        // A pointer to move through the elements
        // the write index stops at a duplicate element and moves only when there is a swap
        for (int i = 1; i < array.length; i++) {
            // the element in the index before the write index is the last non-duplicate element known
            if (array[writeIndex - 1] != array[i]) {
                array[writeIndex] = array[i];
                writeIndex++;
            }
        }

        return writeIndex;

    }
}
