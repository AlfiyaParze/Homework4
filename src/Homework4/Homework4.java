package Homework4;

import java.util.Random;
import java.util.Scanner;

public class Homework4 {

    public static int size = 5;
    public static int dotsToWin = 4;
    public static final char emptyCell = '-';
    public static char[][] field;

    public static void main(String[] args) {
        start();
    }

    static void start() {
        createField();
        drawField();

        do {
            doPlayerMove();
            drawField();
            if (isWin('X')) {
                System.out.println("Congrats!!! You are winner :)");
                break;
            }
            if (isDraw()) {
                System.out.println("This is draw.");
                break;
            }

            doAIMove();
            drawField();
            if (isWin('O')) {
                System.out.println("Sorry!!! You are looser :(");
                break;
            }
            if (isDraw()) {
                System.out.println("This is draw.");
                break;
            }
        } while (true);
    }

    static boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isFreeCell(field, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isWin(char symbol) {
        int i, j;
        int countHor, countVert;

        // horizontal
        for (i = 0; i < size; i++) {
            countHor = 0;
            for (j = 0; j < size; j++)
                if (field[i][j] == symbol) {
                    countHor++;
                }
            if (countHor == dotsToWin) {
                return true;
            }
        }

        // vertical
        for (i = 0; i < size; i++) {
            countVert = 0;
            for (j = 0; j < size; j++)
                if (field[j][i] == symbol) {
                    countVert++;
                }
            if (countVert == dotsToWin) {
                return true;
            }
        }

        // diagonals
        int count1 = 0, count2 = 0;
        for (i = 0; i < size; i++) {
            if (field[i][i] == symbol) {
                count1++;
                if (count1 == dotsToWin) {
                    return true;
                }
            }
            if (field[i][size - 1 - i] == symbol) {
                count2++;
                if (count2 == dotsToWin) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void createField() {
        field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = emptyCell;
            }
        }
    }

    static void doPlayerMove() {
        int h, v;
        do {
            h = getCoordinate(size - 1, 'h');
            v = getCoordinate(size - 1, 'v');
        } while (isNotFreeCell(field, h, v));

        field[h][v] = 'X';
    }

    static void doAIMove() {
        int h, v;
        Random random = new Random();
        System.out.println("AI moving: ");
        do {
            h = random.nextInt(size);
            v = random.nextInt(size);
        } while (isNotFreeCell(field, h, v));

        field[h][v] = 'O';
    }

    static boolean isFreeCell(char[][] field, int h, int v) {
        return field[h][v] == '-';
    }

    static boolean isNotFreeCell(char[][] field, int h, int v) {
        return !isFreeCell(field, h, v);
    }

    static int getCoordinate(int lastIndex, char coordName) {
        Scanner scanner = new Scanner(System.in);
        int coord;
        do {
            System.out.printf("Please enter %s-coordinate ... [1-" + size + "]%n", coordName, 1);
            coord = scanner.nextInt() - 1;
        } while (coord < 0 || coord > lastIndex);
        return coord;
    }

    static void drawField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

}