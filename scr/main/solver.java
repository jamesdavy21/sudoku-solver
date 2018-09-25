package main;

import java.util.Scanner;

public class solver {

    private int[][] board;

    private int[][] testboard = {
            {8,0,0,0,0,0,0,0,0},
            {0,0,3,6,0,0,0,0,0},
            {0,7,0,0,9,0,2,0,0},
            {0,5,0,0,0,7,0,0,0},
            {0,0,0,0,4,5,7,0,0},
            {0,0,0,1,0,0,0,3,0},
            {0,0,1,0,0,0,0,6,8},
            {0,0,8,5,0,0,0,1,0},
            {0,9,0,0,0,0,4,0,0}
    };

    private static final int empty = 0;

    private static final int max = 9;

    private static final int min = 1;


    public int[][] getBoard() {
        return board;
    }

    public int[][] getTestboard() {
        return testboard;
    }

    public boolean solve(int[][] gameBoard){
        for(int row = 0; row < max; row++){
            for (int column = 0; column < max; column++){
                if (gameBoard[row][column] == empty ){
                    for (int value = min; value <= max; value++) {
                        gameBoard[row][column] = value;
                        if(validation(gameBoard, row, column) && solve(gameBoard)){
                            return true;
                        }
                        gameBoard[row][column] = empty;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkRow(int[][] gameBoard, int row){
        boolean correct = false;
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++) {
                if (x == y)
                    correct = true;
                else if (gameBoard[row][x] == 0 && gameBoard[row][y] == 0)
                    correct = true;
                else if (gameBoard[row][x] == gameBoard[row][y] )
                    return false;
            }
        }
        return correct;
    }

    public static boolean checkColumn(int[][] gameBoard, int column){
        boolean correct = false;
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++) {
                if (x == y)
                    correct = true;
                else if (gameBoard[x][column] == 0 && gameBoard[y][column] ==0)
                    correct = true;
                else if(gameBoard[x][column] == gameBoard[y][column])
                    return false;
            }
        }
        return correct;
    }

    public static boolean checkBox(int[][] gameBoard, int row, int column) {
        int startRow = row / 3 * 3;
        int startColumn = column / 3 * 3;
        int z = startRow;
        boolean correct = false;

        for(int x = startRow; x < startRow + 3; x++) {
            for(int y = startColumn; y < startColumn + 3; y++) {
                for (int x2 = startRow; x2 < startRow + 3; x2++) {
                    for (int y2 = startColumn; y2 < startColumn + 3; y2++) {
                        if ((x == x2 && y == y2))
                            correct = true;
                        else if (gameBoard[x][y2] == 0 && gameBoard[x2][y] == 0)
                            correct = true;
                        else if (gameBoard[x][y2] == gameBoard[x2][y])
                            return false;
                    }
                }
            }

        }
        return correct;
    }

    public static  boolean validation(int[][] gameBoard, int row, int column){
        return (checkRow(gameBoard,row) && checkColumn(gameBoard, column) && checkBox(gameBoard, row, column));
    }

    public static void printBoard(int[][] gameBoard){
        for(int x = 0; x < gameBoard.length; x++){
            for (int y = 0; y < gameBoard[0].length; y++){
                System.out.print(gameBoard[x][y] + " ");
            }
            System.out.println();
        }
    }



    public static void main(String arg[]){
        solver game = new solver();
        Scanner scanner = new Scanner(System.in);
        int [][] board = new int[9][9];
        for(int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.println("enter value of row: " + (x+1) + " column: " + (y+1));
                board[x][y]= scanner.nextInt();
            }
        }
        System.out.println("\n The solution is: \n");
        game.solve(board);
        game.printBoard(board);
    }
}
