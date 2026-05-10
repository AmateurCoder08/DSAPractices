package com.complex_problems;

public class SudokuSolver {

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

        if (solveSudoku(sudokuGrid)) {
            printSudoku(sudokuGrid);
        } else {
            System.out.println("There is no solution!");
        }


    }

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveSudoku(int[][] sudoku) {
        boolean[][] numInRow = new boolean[9][10];
        boolean[][] numInColumn = new boolean[9][10];
        boolean[][] numInGrid = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sudoku[i][j];
                if (num!=0) {
                    numInRow[i][num] = true;
                    numInColumn[j][num] = true;
                    int gridNum = ((i / 3) * 3) + (j/3);
                    numInGrid[gridNum][num] = true;
                }
            }
        }

        return backtrack(sudoku, 0, 0, numInRow, numInColumn, numInGrid);

    }

    public static boolean backtrack(int[][] sudokuGrid, int row, int col,
                                      boolean[][] numInRow, boolean[][] numInColumn,
                                      boolean[][] numInGrid) {
        if (row == 9) return true;

        int nextR = (col==8) ? row + 1: row;
        int nextC = (col==8) ? 0: col+1;

        if (sudokuGrid[row][col]!=0) {
            return backtrack(sudokuGrid, nextR, nextC, numInRow, numInColumn, numInGrid);
        }

        int box = (row/3 * 3) + (col/3);

        for (int num=1; num<9; num++) {
            if (!numInRow[row][num] && !numInColumn[row][num] && !numInGrid[box][num]) {
                sudokuGrid[row][col] = num;
                numInRow[row][num] = true;
                numInRow[col][num] = true;
                numInRow[box][num] = true;

                if (backtrack(sudokuGrid, nextR, nextC, numInRow, numInColumn, numInGrid)) {
                    return true;
                }

                sudokuGrid[row][col] = 0;
                numInRow[row][num] = false;
                numInRow[col][num] = false;
                numInRow[box][num] = false;
            }
        }
        return false;
    }
}
