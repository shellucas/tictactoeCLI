package com.shelby.tictactoe.logic;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    private final static String WELCOME_TEXT = "=== Welcome to Tic Tac Toe ===";
    private static String[][] GRID;
    private Status status;
    private Player winner;

    private enum Status {
        WIN, DRAW, CONTINUE
    }

    private enum Player {
        X, Y
    }

    public TicTacToe() {
        GRID = new String[3][3];
        this.status = Status.CONTINUE;
        print(WELCOME_TEXT);
    }

    public void start(boolean playVsCPU) {
        do {
            turn(Player.X);
            if (status == Status.CONTINUE)
                turn(Player.Y);
        } while (status == Status.CONTINUE);

        print(boardToString());
        print("\nWE HAVE A WINNER !!");
        print("Player " + winner + " won the game.");

    }

    private void turn(Player player) {
        final Scanner in = new Scanner(System.in);
        int row, column;
        while (true) {
            print(boardToString());
            String rowString = "Enter a row (0, 1 or 2) for player ";
            rowString = (player == Player.X) ? rowString + "X: " : rowString + "O: ";
            System.out.print(rowString);
            row = Integer.parseInt(in.nextLine());

            String colString = rowString.replace("row", "column");
            System.out.print(colString);
            column = Integer.parseInt(in.nextLine());

            if (isValidMove(row, column))  break;
            else print("Invalid move. Place has already been taken.\n");

        }
        GRID[row][column] = (player == Player.X) ? "X" : "O";
        updateStatus();
    }

    /**
     * Updates the status depending on the outcome of the game.
     */
    private void updateStatus() {
        if (checkRowsForWin() ||  checkColsForWin() || checkDiaForWin())
            status = Status.WIN;
        else if (boardIsFull())
            status = Status.DRAW;
        else status = Status.CONTINUE;
    }

    /**
     * Checks if there is a row with three equal values.
     * @return
     */
    private boolean checkRowsForWin() {
        for (int i = 0, j = 0; i < 3; i++, j = 0) {
            if (checkEqual(GRID[i][j++], GRID[i][j++], GRID[i][j])) return true;
        } return false;
    }

    /**
     * Checks if there is a column with three equal values.
     * @return
     */
    private boolean checkColsForWin() {
        for (int i = 0, j = 0; i < 3; i++, j = 0) {
            if (checkEqual(GRID[j++][i], GRID[j++][i], GRID[j][i])) return true;
        } return false;
    }

    /**
     * Checks if the values are equal diagonally for both left-to-right and vice-versa.
     * @return
     */
    private boolean checkDiaForWin() {
        return checkEqual(GRID[0][0], GRID[1][1], GRID[2][2]) || checkEqual(GRID[0][2], GRID[1][1], GRID[2][0]);
    }

    /**
     * Checks if the three values are equal.
     * @param a
     * @param b
     * @param c
     * @return True if all three are equal.
     */
    private boolean checkEqual(String a, String b, String c) {
        if ( a != null && a.equals(b) && b.equals(c)) {
            winner = (a.equals("X")) ? Player.X : Player.Y;
            return true;
        } return false;
    }

    /**
     * Checks if the slot at GRID[x][y] is already taken.
     * @param x
     * @param y
     * @return true if the slot is free.
     */
    private boolean isValidMove(int x, int y) {
        return GRID[x][y] == null && x < 3 && x >= 0 && y < 3 && y >= 0;
    }

    /**
     * Checks if the board is full.
     * @return
     */
    private boolean boardIsFull() {
        for (String[] strings : GRID) {
            for (String string : strings) {
                if (string == null) return false;
            }
        } return true;
    }

    /**
     * Returns the current status of the board as a string.
     * @return
     */
    private String boardToString() {
        String line = "\n——————-——————\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        for (String[] rows : GRID) {
            for (String token : rows) {
                sb.append("| ")
                    .append(Objects.requireNonNullElse(token, " "))
                    .append(" ");
            }
            sb.append("|" + line);
        }
        return sb.toString();
    }

    /**
     * Helper method to shorten the call to System.out.println().
     * @param message Message to print out.
     */
    private void print(String message) {
        System.out.println(message);
    }

}
