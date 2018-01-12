package com.example.a123.connectfourv3;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by 123 on 10/16/2017.
 */

public class myGUI extends GridLayout {
    private int rSide = 6;
    private int cSide = 7;
    private Button[][] buttons;

    public myGUI(Context context, int width, View.OnClickListener listener) {
        super(context);

        //set # of rows and cols
        setRowCount(rSide);
        setColumnCount(cSide);

        //create buttons and add them to grid layout
        buttons = new Button[rSide][cSide];
        for (int row = 0; row < rSide; row++) {
            for (int col = 0; col < cSide; col++) {
                buttons[row][col] = new Button( context );
                buttons[row][col].setTextSize( ( int ) ( width * .2 ) );
                buttons[row][col].setBackgroundResource(R.drawable.empty_9);
                buttons[row][col].setOnClickListener( listener );
                addView( buttons[row][col], width, width );
            }
        }
    }

    public void setButtonText(int row, int column, String s) {
        buttons[row][column].setText(s);
    }

    public void setButtonPic( int row, int column, int pic ) {
        buttons[row][column].setBackgroundResource(pic);
    }

    public boolean isButton( Button b, int row, int column ) {
        return ( b == buttons[row][column] );
    }

    public void resetButtons( ) {
        for( int row = 0; row < rSide; row++ )
            for( int col = 0; col < cSide; col++ )
                buttons[row][col].setBackgroundResource(R.drawable.empty_9);
    }

    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < rSide; row++ )
            for( int col = 0; col < cSide; col++ )
                buttons[row][col].setEnabled( enabled );
    }
}
