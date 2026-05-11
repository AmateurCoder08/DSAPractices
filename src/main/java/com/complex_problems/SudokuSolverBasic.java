package com.complex_problems;

public class SudokuSolverBasic {

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
            printBoard(sudokuGrid);
        } else {
            System.out.println("No solution exists");
        }
    }

    // Method to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if it's safe to place a number in a cell
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check the row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }
        // Check the column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }
        // Check the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Recursive method to solve the Sudoku using backtracking
    public static boolean solveSudoku(int[][] board) {
        // Find an empty cell (represented by 0)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    // Try numbers from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        // Check if it's safe to place the number
                        if (isSafe(board, row, col, num)) {
                            // Place the number
                            board[row][col] = num;
                            // Recursively try to solve the rest of the board
                            if (solveSudoku(board)) {
                                return true;
                            }
                            // If not successful, backtrack
                            board[row][col] = 0;
                        }
                    }
                    // If no number works, return false
                    return false;
                }
            }
        }
        // If no empty cell is found, the board is solved
        return true;
    }
}
