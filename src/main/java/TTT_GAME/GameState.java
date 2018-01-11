package TTT_GAME;
import javax.swing.*;



public class GameState {

    private int[][] movesArray = new int[3][3];


    public GameState() {


    }
    //reset the play area
    public void resetBoard(){

        movesArray = new int[3][3];

    }

    // Adds moves to the slot in the array

    public void moveSet(int rownum, int colnum, int playnum) {
        //x = 1
        //o = -1
        movesArray[rownum][colnum] = playnum;

    }

    public void checkWinners(int playnum) {

        //checks to see if a player has won
        if (checkForWin()) {


            int result = JOptionPane.showOptionDialog(null, (playnum == 1 ? "X" : "0") + " has WON.\nWant to play again?", "Winner!!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (result == JOptionPane.OK_OPTION) {
                movesArray = new int[3][3];
            }
            if (result == JOptionPane.NO_OPTION) {
                System.exit(0);

            }
            //checks for a draw
        } else if (checkForDraw()) {

            int result = JOptionPane.showOptionDialog(null, "DRAW!!!\nWant to play again?", "DRAW!!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (result == JOptionPane.OK_OPTION) {
                movesArray = new int[3][3];

            }
            if (result == JOptionPane.NO_OPTION) {
                System.exit(0);

            }

        }
    }


        //mutator to get the current board state array
        public int[][] getBoard () {

            return movesArray;

        }
        // Returns true if there is a win
        // This calls the other win functions

    public boolean checkForWin(int[][] gameBoard) {

        return (checkRowsForWin(gameBoard) || checkColumnsForWin(gameBoard) || checkDiagonalsForWin(gameBoard));
    }

    private boolean checkForWin() {

        return (checkRowsForWin(movesArray) || checkColumnsForWin(movesArray) || checkDiagonalsForWin(movesArray));
    }

    //check to see if there is a draw
    private boolean checkForDraw() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (movesArray[i][j] == 0) {
                    return false;
                }

            }
        }

        return true;
    }

    //check to see if there is a draw
    public boolean checkForDraw(int[][] gameBoard) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (gameBoard[i][j] == 0) {
                    return false;
                }

            }
        }

        return true;
    }

    // Loop through rows and see if any are winners.

    private boolean checkRowsForWin(int[][] gameBoard) {

        for (int i = 0; i < 3; i++) {

            if (checkRowCol(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2])) {

                return true;

            }

        }

        return false;

    }


    // Loop through columns and see if any are winners.

    private boolean checkColumnsForWin(int[][] gameBoard) {

        for (int i = 0; i < 3; i++) {

            if (checkRowCol(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i])) {

                return true;

            }

        }

        return false;

    }

    // Check the two diagonals to see if either is a win. Return true if either wins.

    private boolean checkDiagonalsForWin(int[][] gameBoard) {

        return ((checkRowCol(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2])) || (checkRowCol(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0])));

    }

    // Check to see if all three values are the same (and not empty) indicating a win.

    private boolean checkRowCol(int num1, int num2, int num3) {

        return ((num1 != 0) && (num1 == num2) && (num2 == num3));

    }

}




