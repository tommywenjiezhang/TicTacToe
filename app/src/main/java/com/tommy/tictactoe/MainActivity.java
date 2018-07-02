package com.tommy.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    private static TicTacToe newGame = new TicTacToe();
    Button twoPlayer;
    Button aiPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPlayer = (Button) findViewById(R.id.twoPlayer);
        final Intent intent = new Intent(getApplicationContext(),twoPlayers.class);
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        aiPlayer = (Button) findViewById(R.id.aiScreen);
            final  Intent aiIntent = new Intent(getApplicationContext(), AiMode.class);
            aiPlayer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    startActivity(aiIntent);
                }
            });
    }




}
