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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView text_p1, text_p2;
    ImageView cover1, cover2, cover3, cover4,cover5,cover6,cover7,cover8, cover9,cover10,
    cover11,cover12, cover13, cover14, cover15, cover16, cover17, cover18, cover19, cover20;
    LinkedList<Integer> link_list = new LinkedList<Integer>();


    Integer[] cardArray = {101, 102,103,104, 105,106, 201, 202,203,204, 205,206};

    int image101, image102,image103, image104, image105, image106,
            image201, image202,image203, image204, image205, image206;

    private boolean visible1 = false, visible2 = false, visible3 = false, visible4= false, visible5=false, visible6=false,
            visible7 = false, visible8 = false, visible9 = false, visible10 = false, visible11 =false, visible12 =false;
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints =0, cpuPoints =0;
    int turn =1;
    Animation rotateAnimation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

            text_p1 = (TextView) findViewById(R.id.text_p1);
            cover1 = (ImageView) findViewById(R.id.cover1);
           //z cover1.getLayoutParams().width = 20;
            cover2 = (ImageView) findViewById(R.id.cover2);
            //cover2.getLayoutParams().width =100;
            cover3 = (ImageView) findViewById(R.id.cover3);
            cover4 = (ImageView) findViewById(R.id.cover4);
            cover5 = (ImageView) findViewById(R.id.cover5);
            cover6 = (ImageView) findViewById(R.id.cover6);
            cover7 = (ImageView) findViewById(R.id.cover7);
            cover8 = (ImageView) findViewById(R.id.cover8);
            cover9 = (ImageView) findViewById(R.id.cover9);
            cover10 = (ImageView) findViewById(R.id.cover10);
            cover11 = (ImageView) findViewById(R.id.cover11);
            cover12 = (ImageView) findViewById(R.id.cover12);
            rotateAnimation();
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

        if(savedInstanceState != null){
            boolean met1 = savedInstanceState.getBoolean("state1");
            boolean met2 = savedInstanceState.getBoolean("state2");
            boolean met3 = savedInstanceState.getBoolean("state3");
            boolean met4 = savedInstanceState.getBoolean("state4");
            boolean met5 = savedInstanceState.getBoolean("state5");
            boolean met6 = savedInstanceState.getBoolean("state6");
            boolean met7 = savedInstanceState.getBoolean("state7");
            boolean met8 = savedInstanceState.getBoolean("state8");
            boolean met9 = savedInstanceState.getBoolean("state9");
            boolean met10 = savedInstanceState.getBoolean("state10");
            boolean met11 = savedInstanceState.getBoolean("state11");
            boolean met12 = savedInstanceState.getBoolean("state12");
            if(met1){
                cover1.setVisibility(View.INVISIBLE);
                cover1.setEnabled(false);
                visible1 = true;
            }
            if(met2){
                cover2.setVisibility(View.INVISIBLE);
                cover2.setEnabled(false);
                visible2 = true;
            }
            if(met3){
                cover3.setVisibility(View.INVISIBLE);
                cover3.setEnabled(false);
                visible3 = true;

            }
            if(met4){
                cover4.setVisibility(View.INVISIBLE);
                cover4.setEnabled(false);
                visible4 = true;
            }
            if(met5){
                cover5.setVisibility(View.INVISIBLE);
                cover5.setEnabled(false);
                visible5 = true;

            }
            if(met6){
                cover6.setVisibility(View.INVISIBLE);
                cover6.setEnabled(false);
                visible6 = true;

            }
            if(met7){
                cover7.setVisibility(View.INVISIBLE);
                cover7.setEnabled(false);
                visible7 = true;
            }
            if(met8){
                cover8.setVisibility(View.INVISIBLE);
                cover8.setEnabled(false);
                visible8 =  true;
            }
            if(met9){
                cover9.setVisibility(View.INVISIBLE);
                cover9.setEnabled(false);
                visible9 = true;
            }
            if(met10){
                cover10.setVisibility(View.INVISIBLE);
                cover10.setEnabled(false);
                visible10 = true;
            }
            if(met11){
                cover11.setVisibility(View.INVISIBLE);
                cover11.setEnabled(false);
                visible11 = true;
            }
            if(met12){
                cover12.setVisibility(View.INVISIBLE);
                cover12.setEnabled(false);
                visible12 = true;
            }

        }else{
            // Collections.shuffle(Arrays.asList(cardArray));
            shuffleCards();
        }


        frontOfCardsResources();
            //load cards
            // frontOfCardsResources();
            // Collections.shuffle(Arrays.asList(cardArray));

        //    text_p2.setTextColor(Color.GRAY);

            cover1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover1, theCard);
                }
            });
            cover2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover2, theCard);
                }
            });
            cover3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover3, theCard);
                }
            });
            cover4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover4, theCard);
                }
            });
            cover5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover5, theCard);
                }
            });
            cover6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover6, theCard);
                }
            });
            cover7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover7, theCard);
                }
            });
            cover8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover8, theCard);
                }
            });
            cover9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover9, theCard);
                }
            });
            cover10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover10, theCard);
                }
            });
            cover11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover11, theCard);
                }
            });
            cover12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int theCard = Integer.parseInt((String) view.getTag());
                    doStuff(cover12, theCard);
                }
            });
    }
    private void doStuff(ImageView cover, int num){
        //rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
       // cover.startAnimation(rotateAnimation);
        if(cardArray[num] == 101){
            cover.setImageResource(image101);
        }else if(cardArray[num] == 102){
            cover.setImageResource(image102);
        }else if(cardArray[num] == 103){
            cover.setImageResource(image103);
        }else if(cardArray[num] == 104){
            cover.setImageResource(image104);
        }else if(cardArray[num] == 105){
            cover.setImageResource(image105);
        }else if(cardArray[num] == 106){
            cover.setImageResource(image106);
        }else if(cardArray[num] == 201){
            cover.setImageResource(image201);
        }else if(cardArray[num] == 202){
            cover.setImageResource(image202);
        }else if(cardArray[num] == 203){
            cover.setImageResource(image203);
        }else if(cardArray[num] == 204){
            cover.setImageResource(image204);
        }else if(cardArray[num] == 205){
            cover.setImageResource(image205);
        }else if(cardArray[num] == 206){
            cover.setImageResource(image206);
        }
        if(cardNumber == 1){
            firstCard = cardArray[num];
            if(firstCard >200){
                firstCard =firstCard-100;
            }
            cardNumber =2;
            clickedFirst = num;
            cover.setEnabled(false);
        }else if(cardNumber == 2){
            secondCard =cardArray[num];
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

    private void shuffleCards(){
        int temp, index;
        Random random = new Random();
        for(int i = cardArray.length-1; i> 0; i--){
            index = random.nextInt(i+1);
            temp = cardArray[index];
            cardArray[index] = cardArray[i];
            cardArray[i] = temp;
        }
    }
    private void calculate(){

        if(firstCard == secondCard){
            stateChange(clickedFirst);
            stateChange(clickedSecond);
                    playerPoints++;
                    text_p1.setText("Player 1: " +playerPoints);
                    text_p1.setTextColor(Color.GREEN);

        }else{
            reset();
                if(playerPoints > 0){
                    playerPoints--;
                    text_p1.setText("Player 1: " +playerPoints);
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
    }
}