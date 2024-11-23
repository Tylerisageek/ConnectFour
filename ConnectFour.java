// Ty Kemple
// 01/18/2022

import java.util.*;


// This class implements the AbstractStrategyGame interface in order to create the game Connect ConnectFour
// In this game you choose which column to put a piece into, and it is put at the bottom of that column.
// If four pieces of the same color are in a row horizontally, vertically, or diagonally, then that player wins.
public class ConnectFour implements AbstractStrategyGame {

    private String[][] board;
    private int whiteTurn;

    // Game constructor. Creates the board and initializes the turn tracker.
    public ConnectFour() {
        board = new String[7][6];
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = "-";
            }
        }
        whiteTurn = 1;
    }

    // Creates a string containing the board information 
    // Returns said string
    public String toString(){
        String result = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                result += board[j][i] + " ";
            }
            result += "\n";
        }
        return result;
    }

    // Checks to see if either player has won the game.
    // Returns true if a player has won, otherwise returns false
    public boolean isGameOver() {
        boolean gameState = false;
        if (getWinner() > -1) {
            gameState = true;
        }
        return gameState;
    }

    // Checks if a player has four in a row horizontally, diagonally, or vertically
    // Returns an int -1 if no one has won, otherwise returns the number that represents the player
    public int getWinner() {
        int winner = -1;
        for (int i = 0; i < 6; i++) {
            for (int j = 5; j > 2; j--) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i][j - 1]) && board[i][j].equals(board[i][j - 2]) && board[i][j].equals(board[i][j - 3])) {
                    winner = Integer.parseInt(board[i][j]);
                }
            }
            for (int k = 0; k < 4; k++) {
                if (!board[k][i].equals("-") && board[k][i].equals(board[k + 1][i]) && board[k][i].equals(board[k + 2][i]) && board[k][i].equals(board[k + 3][i])) {
                    winner = Integer.parseInt(board[k][i]);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 5; j > 2; j--) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(board[i + 2][j - 2]) && board[i][j].equals(board[i + 3][j - 3])) {
                    winner = Integer.parseInt(board[i][j]);
                }
                if (!board[6 - i][j].equals("-") && board[6 - i][j].equals(board[5 - i][j - 1]) && board[6 - i][j].equals(board[4 - i][j - 2]) && board[6 - i][j].equals(board[3 - i][j - 3])) {
                    winner = Integer.parseInt(board[6 - i][j]);
                }
            }
        }

        return winner;
    }

    // Changes active player
    // Returns an int representing the active player
    public int getNextPlayer(){
        if (whiteTurn == 1) {
            whiteTurn = 0;
        } else {
            whiteTurn = 1;
        }
        return whiteTurn;
    }

    // Looks for player input and runs the actual makeMove method
    public void makeMove(Scanner input){

        System.out.print("Column? ");
        int col = input.nextInt();

        makeMove(col);
    }

    // Takes an int value for selected column to play into
    // Adds piece to the board based on column selection
    // Throws error if column is full or column is not contained in board
    public void makeMove(int col) {
        if (!board[col][0].equals("-")) {
            throw new IllegalArgumentException("Column is full!");
        }
        
        if (board[col][5].equals("-")){
            board[col][5] = Integer.toString(whiteTurn);
        } else {
            for (int i = 0; i < board[col].length; i++) {
                if (!board[col][i].equals("-")) {
                    board[col][i - 1] = Integer.toString(whiteTurn);
                    break;
                }
            }
        } 
    }
}
