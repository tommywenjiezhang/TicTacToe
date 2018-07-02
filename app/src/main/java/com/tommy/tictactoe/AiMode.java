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


import com.tommy.tictactoe.Board;
import com.tommy.tictactoe.Point;
import com.tommy.tictactoe.PointConverter;
import com.tommy.tictactoe.R;



import java.util.Random;

public class AiMode extends AppCompatActivity {
    int whoTurn = 0;
    Board b;
    Random random;
    Button ai;
    Button hu;
    int choice;
    GridLayout aiGridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_mode);
        b = new Board();
        random = new Random();
        final LinearLayout whoTurn = findViewById(R.id.whosTurn);
        whoTurn.setVisibility(View.VISIBLE);
        whoTurn.setTranslationY(-1000f);
        whoTurn.setAlpha(0f);
        whoTurn.animate()
                .translationYBy(1000f)
                .alpha(1)
                .setDuration(2000);
        ai = findViewById(R.id.ai);
        ai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               whoTurn.setVisibility(View.INVISIBLE);
               firstPlay();

            }
        });
        hu = findViewById(R.id.hu);
        hu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whoTurn.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void firstPlay(){
        choice =1;
               aiGridLayout = findViewById(R.id.aigridLayout);
                if(choice == 1){
                    Point newPoint = new Point(random.nextInt(3),random.nextInt(3));
                    b.placeAMove(newPoint,1);
                    int tagId = (int) new PointConverter(newPoint).getTag();
                    ImageView imageView = (ImageView) aiGridLayout.getChildAt(tagId);
                    imageView.setImageResource(R.drawable.o);
                    imageAnimation(imageView);
                    b.displayBoard();

    }
    }

   public void humanInput(View view){
       int win = 0; // 0 mean winning 1 meaing losing;
        boolean userClick = true;
            ImageView userInput = (ImageView) view;
            userInput.setImageResource(R.drawable.x);
            imageAnimation(userInput);
            int tagId = Integer.parseInt(userInput.getTag().toString());
             PointConverter converter = new  PointConverter(tagId);
            Point userMove = new Point(converter.getX(),converter.getY());
             b.placeAMove(userMove,2);
            b.displayBoard();

            while(!b.isGameOver() && userClick){
                aiPlay();

                userClick = false;
                LinearLayout linearLayout = findViewById(R.id.aiplayAgainLayout);
                if (b.hasXWon()) {
                    win = 0;
                } else if (b.hasOWon()) {
                    win = 1;
                }
                else{
                   win = 3;
                }

               if(b.hasOWon() || b.hasXWon() || b.isGameOver()){
                   winnerMessageDisplay(win);
               }

            }

   }
    public void aiPlayAgain(View view) {
        LinearLayout layout = (LinearLayout)findViewById(R.id.aiplayAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.aigridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
        b = new Board();
    }


   public void aiPlay(){
       b.callMinimax(0, 1);
       int tagId = (int) new PointConverter(b.returnBestMove()).getTag();
       aiGridLayout = findViewById(R.id.aigridLayout);
       ImageView imageView = (ImageView) aiGridLayout.getChildAt(tagId);
       imageView.setImageResource(R.drawable.o);
       imageAnimation(imageView);
       b.displayBoard();
       b.placeAMove(b.returnBestMove(), 1);
   }


   public void imageAnimation(View view){
        ImageView imageView = (ImageView) view;
       imageView.setTranslationY(-1000f);
       imageView.animate().translationYBy(1000f).setDuration(200);
   }

   public void winnerMessageDisplay(int choice){
        String message = "";
       LinearLayout linearLayout = findViewById(R.id.aiplayAgainLayout);
       linearLayout.setVisibility(View.VISIBLE);
        switch(choice){
            case 0:
                message = "You lose tried again";
                linearLayout.setBackgroundColor(Color.parseColor("#e3dadf"));
                break;
            case 1:
                message = "You win";
                linearLayout.setBackgroundColor(Color.parseColor("#ffff00"));
                break;

            case 3:
                message = "It is a tie";
                linearLayout.setBackgroundColor(Color.parseColor("#00ff00"));
                default:
                    break;
        }
       TextView winnerMessage = findViewById(R.id.aiwinnerMessage);
       winnerMessage.setText(message);
       linearLayout.setBackgroundColor(Color.parseColor("#e3dadf"));
       linearLayout.setAlpha(0f);
       linearLayout.animate().alpha(0.9f).setDuration(200);
   }
}
