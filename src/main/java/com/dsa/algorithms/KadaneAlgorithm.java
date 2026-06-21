/**
 * Kadane's algorithm is about finding the sub-array with the maximum sum in a given array
 * For example, consider the array [-1,2,-3,4,5,-5]. The sub-array [4,5] has maximum sum of 9
 * In the case of an array with only positive integers [1,2,3,4,5], the entire array is the sub-array with max sum
 */
package com.dsa.algorithms;

public class KadaneAlgorithm {
    public static void main(String[] args) {
        int[] numArr = {-9, 6, -3, 9, -5, -2};
        long startTime = System.nanoTime();
        System.out.println("Find the maximum subarray sum with brute force approach");
        System.out.println("Maximum subarray: " + maxSum(numArr));
        System.out.println("Time taken: " + ((System.nanoTime() - startTime) / Math.pow(10, 6)) + " milliseconds");

        startTime = System.nanoTime();
        System.out.println("Find the maximum subarray sum with kadane's algorithm");
        System.out.println("Maximum subarray: " + maxSumKadaneAlgorithm(numArr));
        System.out.printf("Time taken: %.2f milliseconds", ((System.nanoTime() - startTime) / Math.pow(10, 6)));

        startTime = System.nanoTime();
        System.out.println();
        System.out.println("Find the maximum subarray with kadane's algorithm");
        int[] arr = findingTheSubArrayKadaneAlgorithm(numArr);
        System.out.print("The sub array: [ ");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("]\n");
        System.out.println("The sum: " + arr[arr.length - 1]);
        System.out.printf("Time taken: %.2f milliseconds", ((System.nanoTime() - startTime) / Math.pow(10, 6)));
    }

    // the brute force approach
    public static int maxSum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    // kadane's algorithm
    public static int maxSumKadaneAlgorithm(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        for (int num : arr) {
            if ((currentSum + num) > num) {
                currentSum += num;
            } else {
                currentSum = num;
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static int[] findingTheSubArrayKadaneAlgorithm(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0, start = 0, end = 0, maxStart = 0, maxEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((currentSum + arr[i]) > arr[i]) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
                start = i;
            }
            end = i;
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStart = start;
                maxEnd = end;
            }
        }
        int[] returnValues = new int[maxEnd - maxStart + 2];
        int j = 0;
        for (int i = maxStart; i <= maxEnd; i++, j++) {
            returnValues[j] = arr[i];
        }
        returnValues[j] = maxSum;

        return returnValues;
    }
}
