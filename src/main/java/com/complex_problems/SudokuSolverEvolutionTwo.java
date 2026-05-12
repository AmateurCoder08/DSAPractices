package com.complex_problems;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SudokuSolverEvolutionTwo {

    public static void main(String[] args) {
        int[][] sudokuGrid = new int[][]{
                {0, 1, 0, 4, 0, 0, 0, 6, 5},
                {0, 0, 8, 0, 0, 0, 2, 0, 9},
                {0, 0, 0, 0, 9, 7, 0, 0, 0},
                {0, 2, 4, 0, 0, 0, 6, 0, 0},
                {0, 3, 0, 7, 0, 9, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 3, 5, 0},
                {0, 0, 0, 5, 4, 0, 0, 0, 0},
                {1, 0, 5, 0, 0, 0, 8, 0, 0},
                {4, 6, 0, 0, 0, 3, 0, 2, 0}
        };


        long startTime = System.nanoTime();

        if (solveSudoku(sudokuGrid)) {
            printSudoku(sudokuGrid);
        } else {
            System.out.println("There is no solution!");
        }

        long endTime = System.nanoTime();
        System.out.println("Executed in " + (endTime - startTime) + " nanoseconds");


    }

    // Method to print the solved sudoku puzzle in proper format
    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveSudoku(int[][] sudoku) {
        // using bitmasking to make the code more efficient
        int[] rowUsed = new int[9];
        int[] colUsed = new int[9];
        int[] boxUsed = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sudoku[i][j];
                if (num != 0) {
                    // if the number is present in the row, we set the corresponding bit to 1. For example, if the number is 3, we set the 3rd bit to 1.
                    rowUsed[i] = rowUsed[i] | (1 << num);
                    // if the number is present in the column, we set the corresponding bit to 1.
                    colUsed[j] = colUsed[j] | (1 << num);
                    // There are a total of nine 3*3 boxes in the entire sudoku grid. The boxes are numbered from 0 to 8.
                    // The below formula calculates the number of the 3*3 box based on the row and col of a cell
                    int gridNum = ((i / 3) * 3) + (j / 3);
                    // if the number is present in the grid/box, we set the corresponding bit to 1.
                    boxUsed[gridNum] = boxUsed[gridNum] | (1 << num);
                }
            }
        }

        return backtrack(sudoku, 0, 0, rowUsed, colUsed, boxUsed);

    }

    public static boolean backtrack(int[][] sudokuGrid, int row, int col,
                                    int[] numInRow, int[] numInColumn, int[] numInGrid) {
        // The base case. Rows are numbered from 0 to 8. So a row 9 means the puzzle has been solved
        if (row == 9) return true;

        // if column is 8, it's time to move to next row
        int nextR = (col == 8) ? row + 1 : row;
        // if column is 8, it's time to reset to 0 else move to the next column
        int nextC = (col == 8) ? 0 : col + 1;

        // if the cell has a non-zero value meaning it has already been filled, move to the next cell
        if (sudokuGrid[row][col] != 0) {
            return backtrack(sudokuGrid, nextR, nextC, numInRow, numInColumn, numInGrid);
        }

        // Again the formula for calculating the box number
        int box = (row / 3 * 3) + (col / 3);

        // Trying the possibilities from 1 to 9.
        for (int num = 1; num <= 9; num++) {
            // check if the number is not present in the row, column and the 3*3 box and then try it
            // we check if at the index corresponding to num, the bit is 0 or not. If it's 0, it means the number is not present in that row/column/box. If it's 1, it means the number is already present.
            if ((numInRow[row] & (1 << num)) == 0 && (numInColumn[col] & (1 << num)) == 0 && (numInGrid[box] & (1 << num)) == 0) {
                sudokuGrid[row][col] = num;
                numInRow[row] = numInRow[row] | (1 << num);
                numInColumn[col] = numInColumn[col] | (1 << num);
                numInGrid[box] = numInGrid[box] | (1 << num);

                // if a successful try proceed further
                if (backtrack(sudokuGrid, nextR, nextC, numInRow, numInColumn, numInGrid)) {
                    return true;
                }

                // if an unsuccessful try, undo the changes so that the next possibility can be tried
                sudokuGrid[row][col] = 0;
                // we undo the changes by setting the corresponding bit to 0.
                numInRow[row] = numInRow[row] & ~(1 << num);
                numInColumn[col] = numInColumn[col] & ~(1 << num);
                numInGrid[box] = numInGrid[box] & ~(1 << num);
            }
        }
        // if none of the possibilities from 1 to 9, then there is no solution to this sudoku puzzle
        return false;
    }
}
