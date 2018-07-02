package com.tommy.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class twoPlayers extends AppCompatActivity {
    private static TicTacToe newGame = new TicTacToe();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);
    }

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        char winner = 'X';
        boolean gameOn = true;
        // this place the position for player;
        // x is the first player in the game
        while(gameOn){
            int position = Integer.parseInt(counter.getTag().toString());
            PointConverter newPosition = new PointConverter(position);
            if(gameOn && newGame.placeMark(newPosition.getX(),newPosition.getY())){
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.x);
                newGame.placeMark(newPosition.getX(),newPosition.getY());
                newGame.printBoard();
                if(newGame.changePlayer() == 'x'){
                    counter.setImageResource(R.drawable.o);
                }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                if(newGame.checkForWin() || newGame.isBoardFull()){
                    if (newGame.checkForWin()) {
                        System.out.println("We have a winner! Congrats!");
                        winner = newGame.whoWin();
                        Toast.makeText(twoPlayers.this,"We have a winner! Congrats!",Toast.LENGTH_LONG).show();
                        gameOn = false;
                    }
                    else if (newGame.isBoardFull()) {
                        Toast.makeText(twoPlayers.this,"Appears we have a draw!",Toast.LENGTH_LONG).show();
                        gameOn = false;
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    if (newGame.whoWin() == 'T'){
                        winnerMessage.setText("Tie");
                        layout.setVisibility(View.VISIBLE);
                        int color = Color.parseColor("#00ff2a");
                        layout.setBackgroundColor(color);
                    }
                    else{
                        winnerMessage.setText(winner + " has won!");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
                newGame.printBoard();
                gameOn = false;
            }
            else{
                System.out.println("Position has already been placed");
                break;
            }
        }
    }

    public void playAgain(View view) {


        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
        newGame = new TicTacToe();
    }
}
