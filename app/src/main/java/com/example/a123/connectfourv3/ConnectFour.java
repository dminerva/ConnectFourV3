package com.example.a123.connectfourv3;

/**
 * Created by 123 on 10/16/2017.
 */

public class ConnectFour {
    public static final int rSIDE = 6;
    public static final int cSIDE = 7;
    private int turn;
    private int [][] game;

    public ConnectFour() {
        game = new int[rSIDE][cSIDE];
        resetGame();
    }

    public int play( int row, int col ) {
        int currentTurn = turn;
        if( row >= 0 && col >= 0 && row < rSIDE && col < cSIDE
                && game[row][col] == 0 ) {
            game[row][col] = turn;
            if( turn == 1 )
                turn = 2;
            else
                turn = 1;
            return currentTurn;
        }
        else
            return 0;
    }

    public int checkEmptySpace(int thisCol) {
        for (int row = rSIDE -1; row >= 0; row--) {
            if (game[row][thisCol] == 0) {
                return row;
            }
        }
        return 99;
    }

    /*public int whoWon() {

    }*/

    //check rows

    //check cols

    //check diags

    public int whoWon() {
        final int HEIGHT = game.length;
        final int WIDTH = game[0].length;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = game[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == game[r][c+1] && // look right
                        player == game[r][c+2] &&
                        player == game[r][c+3])
                    return player;
                if (r + 3 < HEIGHT) {
                    if (player == game[r+1][c] && // look up
                            player == game[r+2][c] &&
                            player == game[r+3][c])
                        return player;
                    if (c + 3 < WIDTH &&
                            player == game[r+1][c+1] && // look up & right
                            player == game[r+2][c+2] &&
                            player == game[r+3][c+3])
                        return player;
                    if (c - 3 >= 0 &&
                            player == game[r+1][c-1] && // look up & left
                            player == game[r+2][c-2] &&
                            player == game[r+3][c-3])
                        return player;
                }
            }
        }
        return EMPTY_SLOT; // no winner found
    }

    public boolean canNotPlay( ) {
        boolean result = true;
        for (int row = 0; row < rSIDE; row++)
            for( int col = 0; col < cSIDE; col++ )
                if ( game[row][col] == 0 )
                    result = false;
        return result;
    }

    public boolean isGameOver() {
        return canNotPlay( ) || ( whoWon() > 0 );
    }

    public void resetGame( ) {
        for (int row = 0; row < rSIDE; row++)
            for( int col = 0; col < cSIDE; col++ )
                game[row][col] = 0;
        turn = 1;
    }
}
