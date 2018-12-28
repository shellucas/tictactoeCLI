package com.shelby.tictactoe.logic;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    private final static String WELCOME_TEXT = "=== Welcome to Tic Tac Toe ===";
    private static String[][] GRID;
    private Status status;

    private enum Status {
        WIN, DRAW, CONTINUE
    }

    public TicTacToe() {
        GRID = new String[3][3];
        this.status = Status.CONTINUE;
        print(WELCOME_TEXT);
    }

    public void start(boolean playVsCPU) {
        while (true) {
            print(boardToString());
            turn(true);
            print(boardToString());
            turn(false);
        }
    }

    private void turn(boolean isPlayerX) {
        final Scanner in = new Scanner(System.in);
        int row, column;
        while (true) {

            String rowString = "Enter a row (0, 1 or 2) for player ";
            rowString = (isPlayerX) ? rowString + "X: " : rowString + "O: ";
            System.out.print(rowString);
            row = Integer.parseInt(in.nextLine());

            String colString = rowString.replace("row", "column");
            System.out.print(colString);
            column = Integer.parseInt(in.nextLine());

            if (isValidMove(row, column))  break;
            else print("Invalid move. Place has already been taken.\n");

        }
        GRID[row][column] = (isPlayerX) ? "X" : "O";
    }

    private boolean isValidMove(int x, int y) {
        return GRID[x][y] == null;
    }


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

    private Status getStatus() {
        return Status.CONTINUE;
    }

    private void print(String message) {
        System.out.println(message);
    }

}
