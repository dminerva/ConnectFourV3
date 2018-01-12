package com.example.a123.connectfourv3;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ConnectFour game;
    private myGUI mg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new ConnectFour();
        Point size = new Point( );
        getWindowManager().getDefaultDisplay( ).getSize( size );
        int w = size.x / 7;
        ButtonHandler bh = new ButtonHandler( );
        mg = new myGUI(this, w, bh);
        setContentView(mg);
    }

    public void showNewGameDialog( ) {
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "This is fun" );
        alert.setMessage( "Play again?" );
        PlayDialog playAgain = new PlayDialog( );
        alert.setPositiveButton( "YES", playAgain );
        alert.setNegativeButton( "NO", playAgain );
        alert.show( );
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            for (int row = 0; row < ConnectFour.rSIDE; row++) {
                for( int column = 0; column < ConnectFour.cSIDE; column++ ) {
                    if( mg.isButton( (Button) v, row, column ) ) {
                        if (game.checkEmptySpace(column) != 99) {
                            row = game.checkEmptySpace(column);
                            int play = game.play( row, column );
                            if( play == 1 )
                                mg.setButtonPic( row, column, R.drawable.redchip_9 );
                            else if( play == 2 )
                                mg.setButtonPic( row, column, R.drawable.bluechip_9 );
                            if( game.isGameOver( ) ) {
                                mg.enableButtons(false);
                                showNewGameDialog( );
                            }
                        }
                    }
                }
            }
        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick( DialogInterface dialog, int id ) {
            if( id == -1 ) /* YES button */ {
                game.resetGame( );
                mg.enableButtons( true );
                mg.resetButtons( );
            }
            else if( id == -2 ) // NO button
                MainActivity.this.finish( );
        }
    }
}
