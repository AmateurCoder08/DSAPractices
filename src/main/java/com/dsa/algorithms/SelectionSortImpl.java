package com.dsa.algorithms;

import java.util.Arrays;

public class SelectionSortImpl {
    public static void main(String[] args) {
        int[] intArr = new int[]{7, 2, 9, 6, 5, 4, 3, 1};
        selectionSort(intArr);
        System.out.println(Arrays.toString(intArr));

    }

    public static void selectionSort(int[] intArr) {
        for (int i = 0; i < intArr.length - 1; i++) {
            int minimumValueIndex = i;
            int minimumValue = intArr[i];
            for (int j = i + 1; j < intArr.length; j++) {
                if (intArr[j] < minimumValue) {
                    minimumValue = intArr[j];
                    minimumValueIndex = j;
                }
            }
            intArr[minimumValueIndex] = intArr[i];
            intArr[i] = minimumValue;
        }
    }
}
