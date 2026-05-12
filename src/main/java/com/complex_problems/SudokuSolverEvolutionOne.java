package com.complex_problems;

public class SudokuSolverEvolutionOne {

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
        // the below 2d boolean array tracks if a number exists in a particular row
        boolean[][] numInRow = new boolean[9][10];
        // the below 2d boolean array tracks if a number exists in a particular column
        boolean[][] numInColumn = new boolean[9][10];
        // the below 2d boolean array tracks if a number exists in a particular 3*3 box
        boolean[][] numInGrid = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sudoku[i][j];
                if (num != 0) {
                    numInRow[i][num] = true;
                    numInColumn[j][num] = true;
                    // There are a total of nine 3*3 boxes in the entire sudoku grid. The boxes are numbered from 0 to 8.
                    // The below formula calculates the number of the 3*3 box based on the row and col of a cell
                    int gridNum = ((i / 3) * 3) + (j / 3);
                    numInGrid[gridNum][num] = true;
                }
            }
        }

        return backtrack(sudoku, 0, 0, numInRow, numInColumn, numInGrid);

    }

    public static boolean backtrack(int[][] sudokuGrid, int row, int col,
                                    boolean[][] numInRow, boolean[][] numInColumn,
                                    boolean[][] numInGrid) {
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
            if (!numInRow[row][num] && !numInColumn[col][num] && !numInGrid[box][num]) {
                sudokuGrid[row][col] = num;
                numInRow[row][num] = true;
                numInColumn[col][num] = true;
                numInGrid[box][num] = true;

                // if a successful try proceed further
                if (backtrack(sudokuGrid, nextR, nextC, numInRow, numInColumn, numInGrid)) {
                    return true;
                }

                // if an unsuccessful try, undo the changes so that the next possibility can be tried
                sudokuGrid[row][col] = 0;
                numInRow[row][num] = false;
                numInColumn[col][num] = false;
                numInGrid[box][num] = false;
            }
        }
        // if none of the possibilities from 1 to 9, then there is no solution to this sudoku puzzle
        return false;
    }
}
