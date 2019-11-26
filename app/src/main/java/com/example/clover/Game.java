package com.example.clover;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;

// grab first 2 parts of random array then add 100 or subtract 100


public class Game extends AppCompatActivity {

    TextView text_p1, text_p2;
    ImageView cover1, cover2, cover3, cover4,cover5,cover6,cover7,cover8, cover9,cover10,
    cover11,cover12, cover13, cover14, cover15, cover16, cover17, cover18, cover19, cover20;
    ImageView[] img;
    int[] card2 =  {101, 102,103,104, 105,106};
    int[] cardArray = {101, 102,103,104, 105,106, 201, 202,203,204, 205,206};
    int[] copy;

    int image101, image102,image103, image104, image105, image106,
            image201, image202,image203, image204, image205, image206;

    private boolean visible1 = false, visible2 = false, visible3 = false, visible4= false, visible5=false, visible6=false,
            visible7 = false, visible8 = false, visible9 = false, visible10 = false, visible11 =false, visible12 =false;
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints =0, cpuPoints =0;
    String playerText;
    int size;

    Animation rotateAnimation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

            text_p1 = (TextView) findViewById(R.id.text_p1);
            cover1 =  findViewById(R.id.cover1);
            cover2 =  findViewById(R.id.cover2);
            cover3 =  findViewById(R.id.cover3);
            cover4 =  findViewById(R.id.cover4);
            cover5 =  findViewById(R.id.cover5);
            cover6 =  findViewById(R.id.cover6);
            cover7 =  findViewById(R.id.cover7);
            cover8 =  findViewById(R.id.cover8);
            cover9 =  findViewById(R.id.cover9);
            cover10 = findViewById(R.id.cover10);
            cover11 = findViewById(R.id.cover11);
            cover12 = findViewById(R.id.cover12);
          //  rotateAnimation();


        if(savedInstanceState != null) {
            playerPoints = savedInstanceState.getInt("points");
            playerText = savedInstanceState.getString("player");
            size = savedInstanceState.getInt("size");
            copy = savedInstanceState.getIntArray("location1");
            text_p1.setText(playerText);

            visible1 = savedInstanceState.getBoolean("state1");
            visible2 = savedInstanceState.getBoolean("state2");
            visible3 = savedInstanceState.getBoolean("state3");
            visible4 = savedInstanceState.getBoolean("state4");
            visible5 = savedInstanceState.getBoolean("state5");
            visible6 = savedInstanceState.getBoolean("state6");
            visible7 = savedInstanceState.getBoolean("state7");
            visible8 = savedInstanceState.getBoolean("state8");
            visible9 = savedInstanceState.getBoolean("state9");
            visible10 = savedInstanceState.getBoolean("state10");
            visible11 = savedInstanceState.getBoolean("state11");
            visible12 = savedInstanceState.getBoolean("state12");

        }else{
            String data = getIntent().getStringExtra("size_key");
            data = data.replaceAll("\\s", "");
            size = Integer.parseInt(data);
           DoThis();
        }
      Call();
    }
    public void resetVisible(){
        visible1 =false;
        visible2 =false;
        visible3 =false;
        visible4 =false;
        visible5 =false;
        visible6 =false;
        visible7 =false;
        visible8 =false;
        visible9 =false;
        visible10 =false;
        visible11 =false;
        visible12 =false;
    }

    public void DoThis(){
        shuffleCards(size);
        shuffle(size);
    }

    public void Call(){
        Display(size);

        setViewV2(cover1, visible1);
        setViewV2(cover2, visible2);
        setViewV2(cover3, visible3);
        setViewV2(cover4, visible4);
        setViewV2(cover5, visible5);
        setViewV2(cover6, visible6);
        setViewV2(cover7, visible7);
        setViewV2(cover8, visible8);
        setViewV2(cover9, visible9);
        setViewV2(cover10, visible10);
        setViewV2(cover11, visible11);
        setViewV2(cover12, visible12);

        frontOfCardsResources();
        ButtonEvent(cover1);
        ButtonEvent(cover2);
        ButtonEvent(cover3);
        ButtonEvent(cover4);
        ButtonEvent(cover5);
        ButtonEvent(cover6);
        ButtonEvent(cover7);
        ButtonEvent(cover8);
        ButtonEvent(cover9);
        ButtonEvent(cover10);
        ButtonEvent(cover11);
        ButtonEvent(cover12);
    }

    public void Display(int size){
       String num =  Integer.toString(size);
        UpdateTag(num);
        if( size == 4 || size == 6)
        {
           cover2.setTag("0");
           cover3.setTag("1");
           cover6.setTag("2");
           cover7.setTag("3");
           if(size == 6)
               cover10.setTag("4");
                cover11.setTag("5");
        }else{
            cover1.setTag("0");
            cover2.setTag("1");
            cover3.setTag("2");
            cover4.setTag("3");
            cover5.setTag("4");
            cover6.setTag("5");
            cover7.setTag("6");
            cover8.setTag("7");
            cover9.setTag("8");
            cover10.setTag("9");
            cover11.setTag("10");
            cover12.setTag("11");
        }
    }
    private void UpdateTag(String size){
        cover1.setTag(size);
        cover2.setTag(size);
        cover3.setTag(size);
        cover4.setTag(size);
        cover5.setTag(size);
        cover6.setTag(size);
        cover7.setTag(size);
        cover8.setTag(size);
        cover9.setTag(size);
        cover10.setTag(size);
        cover11.setTag(size);
        cover12.setTag(size);
    }

    private void setViewV2(ImageView cover, boolean visible){
        int theCard = Integer.parseInt((String) cover.getTag());
        if(visible){
            cover.setVisibility(View.INVISIBLE);
            cover.setEnabled(false);
        } else if(theCard < size){
            cover.setEnabled(true);
            cover.setVisibility(View.VISIBLE);
        }else{
            cover.setEnabled(false);
            cover.setVisibility(View.INVISIBLE);
        }

    }

    private  void ButtonEvent(final ImageView cover){
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(cover, theCard);
            }
        });
    }
    private void doStuff(ImageView cover, int num){
        if(copy[num] == 101){
            cover.setImageResource(image101);
        }else if(copy[num] == 102){
            cover.setImageResource(image102);
        }else if(copy[num] == 103){
            cover.setImageResource(image103);
        }else if(copy[num] == 104){
            cover.setImageResource(image104);
        }else if(copy[num] == 105){
            cover.setImageResource(image105);
        }else if(copy[num] == 106){
            cover.setImageResource(image106);
        }else if(copy[num] == 201){
            cover.setImageResource(image201);
        }else if(copy[num] == 202){
            cover.setImageResource(image202);
        }else if(copy[num] == 203){
            cover.setImageResource(image203);
        }else if(copy[num] == 204){
            cover.setImageResource(image204);
        }else if(copy[num] == 205){
            cover.setImageResource(image205);
        }else if(copy[num] == 206){
            cover.setImageResource(image206);
        }
        if(cardNumber == 1){
            firstCard = copy[num];
            if(firstCard >200){
                firstCard =firstCard-100;
            }
            cardNumber =2;
            clickedFirst = num;
            cover.setEnabled(false);
        }else if(cardNumber == 2){
            secondCard =copy[num];
            if(secondCard >200){
                secondCard =secondCard-100;
            }
            cardNumber =1 ;
            clickedSecond = num;
            Enable(false);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private void shuffle(int size){
        int temp, index;
        Random random = new Random();
        for(int i = copy.length-1; i> 0; i--){
            index = random.nextInt(i+1);
            temp = copy[index];
            copy[index] = copy[i];
            copy[i] = temp;
        }
    }

    private void shuffleCards(int size){
        int temp, index;
        Random random = new Random();
        for(int i = card2.length-1; i> 0; i--){
            index = random.nextInt(i+1);
            temp = card2[index];
            card2[index] = card2[i];
            card2[i] = temp;
        }
        getCard(size);
    }

    private void getCard(int size){
        int count = 0;
        copy = new int[size];
        for(int i = 0; i < size/2; i++){
            copy[i] = card2[count];
                     count++;
        }
        count = 0;
        for(int i = size/2; i < size; i++){
            copy[i] = copy[count] +100;
            count++;
        }
        for(int i=0; i < copy.length; i++){
            copy[i] = copy[i];
            String t = Integer.toString(copy[i]);
            Toast.makeText(Game.this, t, Toast.LENGTH_SHORT).show();
        }
    }

    private void calculate(){
        if(firstCard == secondCard){
            if(size ==4 || size == 6){
                stateChangeV2(clickedFirst);
                stateChangeV2(clickedSecond);
            }else {
                stateChange(clickedFirst);
                stateChange(clickedSecond);
            }
            playerPoints++;
                     playerText = "Player 1: " +playerPoints;
                    text_p1.setText(playerText);
                    text_p1.setTextColor(Color.GREEN);
        }else{
            reset();
                if(playerPoints > 0){
                    playerPoints--;
                    playerText = "Player 1: " +playerPoints;
                    text_p1.setText(playerText);
                    text_p1.setTextColor(Color.RED);
                }
        }
        Enable(true);
        checkEnd();
    }
    private void checkEnd(){
        if(cover1.getVisibility() == View.INVISIBLE &&
                cover2.getVisibility() == View.INVISIBLE &&
                cover3.getVisibility() == View.INVISIBLE &&
                cover4.getVisibility() == View.INVISIBLE &&
                cover5.getVisibility() == View.INVISIBLE &&
                cover6.getVisibility() == View.INVISIBLE &&
                cover7.getVisibility() == View.INVISIBLE &&
                cover8.getVisibility() == View.INVISIBLE &&
                cover9.getVisibility() == View.INVISIBLE &&
                cover10.getVisibility() == View.INVISIBLE &&
                cover11.getVisibility() == View.INVISIBLE &&
                cover12.getVisibility() == View.INVISIBLE ){
            rotateAnimation();
            AlertDialog.Builder alertDialogBuilder =  new AlertDialog.Builder(Game.this);
            alertDialogBuilder.setMessage("Game over")
            .setCancelable(false)
            .setPositiveButton("New", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            })
            .setNegativeButton("Exit", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void frontOfCardsResources(){
        image101 = R.drawable.camel;
        image102 = R.drawable.coala;
        image103 = R.drawable.fox;
        image104 = R.drawable.lion;
        image105 = R.drawable.monkey;
        image106 = R.drawable.wolf;

        image201 = R.drawable.camel1;
        image202 = R.drawable.coala1;
        image203 = R.drawable.fox1;
        image204 = R.drawable.lion2;
        image205 = R.drawable.monkey1;
        image206 = R.drawable.wolf1;
    }

    private void rotateAnimation(){
        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        cover1.startAnimation(rotateAnimation);
        cover2.startAnimation(rotateAnimation);
        cover3.startAnimation(rotateAnimation);
        cover4.startAnimation(rotateAnimation);
        cover5.startAnimation(rotateAnimation);
        cover6.startAnimation(rotateAnimation);
        cover7.startAnimation(rotateAnimation);
        cover8.startAnimation(rotateAnimation);
        cover9.startAnimation(rotateAnimation);
        cover10.startAnimation(rotateAnimation);
        cover11.startAnimation(rotateAnimation);
        cover12.startAnimation(rotateAnimation);
    }

    public void Enable(boolean set){
        cover1.setEnabled(set);
        cover2.setEnabled(set);
        cover3.setEnabled(set);
        cover4.setEnabled(set);
        cover5.setEnabled(set);
        cover6.setEnabled(set);
        cover7.setEnabled(set);
        cover8.setEnabled(set);
        cover9.setEnabled(set);
        cover10.setEnabled(set);
        cover11.setEnabled(set);
        cover12.setEnabled(set);
    }
    public void reset(){
        cover1.setImageResource(R.drawable.code);
        cover2.setImageResource(R.drawable.code);
        cover3.setImageResource(R.drawable.code);
        cover4.setImageResource(R.drawable.code);
        cover5.setImageResource(R.drawable.code);
        cover6.setImageResource(R.drawable.code);
        cover7.setImageResource(R.drawable.code);
        cover8.setImageResource(R.drawable.code);
        cover9.setImageResource(R.drawable.code);
        cover10.setImageResource(R.drawable.code);
        cover11.setImageResource(R.drawable.code);
        cover12.setImageResource(R.drawable.code);
    }

    private void stateChangeV2(int check){
        if(check == 0){
            cover2.setVisibility(View.INVISIBLE);
            visible2 = true;
        } else if(check == 1){
            cover3.setVisibility(View.INVISIBLE);
            visible3 = true;
        } else if(check == 2){
            cover6.setVisibility(View.INVISIBLE);
            visible6 = true;
        } else if(check == 3){
            cover7.setVisibility(View.INVISIBLE);
            visible7 = true;
        }else if(check == 4){
            cover10.setVisibility(View.INVISIBLE);
            visible10 = true;
        } else if(check == 5){
            cover11.setVisibility(View.INVISIBLE);
            visible11 = true;
        }
    }



    public void stateChange(int check){
        if(check == 0){
            cover1.setVisibility(View.INVISIBLE);
            visible1 = true;
        } else if(check == 1){
            cover2.setVisibility(View.INVISIBLE);
            visible2 = true;
        } else if(check == 2){
            cover3.setVisibility(View.INVISIBLE);
            visible3 = true;
        } else if(check == 3){
            cover4.setVisibility(View.INVISIBLE);
            visible4 = true;
        } else if(check == 4){
            cover5.setVisibility(View.INVISIBLE);
            visible5 = true;
        } else if(check == 5){
            cover6.setVisibility(View.INVISIBLE);
            visible6 = true;
        } else if(check == 6){
            cover7.setVisibility(View.INVISIBLE);
            visible7 = true;
        } else if(check == 7){
            cover8.setVisibility(View.INVISIBLE);
            visible8 = true;
        } else if(check == 8){
            cover9.setVisibility(View.INVISIBLE);
            visible9 = true;
        } else if(check == 9){
            cover10.setVisibility(View.INVISIBLE);
            visible10 = true;
        } else if(check == 10){
            cover11.setVisibility(View.INVISIBLE);
            visible11 = true;
        } else if(check == 11){
            cover12.setVisibility(View.INVISIBLE);
            visible12 = true;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("size", size);
        outState.putBoolean("state1", visible1);
        outState.putBoolean("state2", visible2);
        outState.putBoolean("state3", visible3);
        outState.putBoolean("state4", visible4);
        outState.putBoolean("state5", visible5);
        outState.putBoolean("state6", visible6);
        outState.putBoolean("state7", visible7);
        outState.putBoolean("state8", visible8);
        outState.putBoolean("state9", visible9);
        outState.putBoolean("state10", visible10);
        outState.putBoolean("state11", visible11);
        outState.putBoolean("state12", visible12);
        /////////////////////////////////////////////////////////////////////
        outState.putInt("points", playerPoints);
        outState.putIntArray("location1", copy);
        outState.putString("player", playerText);

    }
    public void Reveal(ImageView cover, int num){
        if(copy[num] == 101){
            cover.setImageResource(image101);
        }else if(copy[num] == 102){
            cover.setImageResource(image102);
        }else if(copy[num] == 103){
            cover.setImageResource(image103);
        }else if(copy[num] == 104){
            cover.setImageResource(image104);
        }else if(copy[num] == 105){
            cover.setImageResource(image105);
        }else if(copy[num] == 106){
            cover.setImageResource(image106);
        }else if(copy[num] == 201){
            cover.setImageResource(image201);
        }else if(copy[num] == 202){
            cover.setImageResource(image202);
        }else if(copy[num] == 203){
            cover.setImageResource(image203);
        }else if(copy[num] == 204){
            cover.setImageResource(image204);
        }else if(copy[num] == 205){
            cover.setImageResource(image205);
        }else if(copy[num] == 206){
            cover.setImageResource(image206);
        }
    }

    public void Show(ImageView cover){
        int theCard = Integer.parseInt((String) cover.getTag());
        if(theCard < size) {
           Reveal(cover, theCard);
       }
    }

    public void Answers(View view){
        Show(cover1);
        Show(cover2);
        Show(cover3);
        Show(cover4);
        Show(cover5);
        Show(cover6);
        Show(cover7);
        Show(cover8);
        Show(cover9);
        Show(cover10);
        Show(cover11);
        playerPoints = 0;
    }

    public void Reset(View view) {
        resetVisible();
        reset();
        DoThis();
        Call();
    }
}