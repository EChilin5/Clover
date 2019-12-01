package com.example.clover;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

// grab first 2 parts of random array then add 100 or subtract 100


public class Game extends AppCompatActivity {

    TextView text_p1, text_p2;
    ImageButton submitbutton;
    ImageView cover1, cover2, cover3, cover4, cover5, cover6, cover7, cover8, cover9, cover10,
            cover11, cover12, cover13, cover14, cover15, cover16, cover17, cover18, cover19, cover20;
    int[] card2 = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110};
    int[] copy;

    int image101, image102, image103, image104, image105, image106, image107, image108, image109, image110,
            image201, image202, image203, image204, image205, image206, image207, image208, image209, image210;

    private boolean visible1 = false, visible2 = false, visible3 = false, visible4 = false, visible5 = false, visible6 = false,
            visible7 = false, visible8 = false, visible9 = false, visible10 = false, visible11 = false, visible12 = false,
            visible13 = false, visible14 = false, visible15 = false, visible16 = false, visible17 = false, visible18 = false,
            visible19 = false, visible20 = false;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 4000, cpuPoints = 0;
    String playerText;
    int size;

    int missedPoint = 1;
    int corectPoint = 1;

    MediaPlayer mySong;
    HomeListener mHomeWatcher;
    int stop = 0;

    Animation rotateAnimation;
    MusicService music;
    private MusicService mServ;
    private boolean mIsBound = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        text_p1 = findViewById(R.id.text_p1);
        cover1 = findViewById(R.id.cover1);
        cover2 = findViewById(R.id.cover2);
        cover3 = findViewById(R.id.cover3);
        cover4 = findViewById(R.id.cover4);
        cover5 = findViewById(R.id.cover5);
        cover6 = findViewById(R.id.cover6);
        cover7 = findViewById(R.id.cover7);
        cover8 = findViewById(R.id.cover8);
        cover9 = findViewById(R.id.cover9);
        cover10 = findViewById(R.id.cover10);
        cover11 = findViewById(R.id.cover11);
        cover12 = findViewById(R.id.cover12);
        cover13 = findViewById(R.id.cover13);
        cover14 = findViewById(R.id.cover14);
        cover15 = findViewById(R.id.cover15);
        cover16 = findViewById(R.id.cover16);
        cover17 = findViewById(R.id.cover17);
        cover18 = findViewById(R.id.cover18);
        cover19 = findViewById(R.id.cover19);
        cover20 = findViewById(R.id.cover20);
        submitbutton=findViewById(R.id.submitbutton);
        submitbutton.setVisibility(View.VISIBLE);

        reset();

        if (savedInstanceState != null) {
            missedPoint = savedInstanceState.getInt("missed");
            corectPoint = savedInstanceState.getInt("correct");
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
            visible13 = savedInstanceState.getBoolean("state13");
            visible14 = savedInstanceState.getBoolean("state14");
            visible15 = savedInstanceState.getBoolean("state15");
            visible16 = savedInstanceState.getBoolean("state16");
            visible17 = savedInstanceState.getBoolean("state17");
            visible18 = savedInstanceState.getBoolean("state18");
            visible19 = savedInstanceState.getBoolean("state19");
            visible20 = savedInstanceState.getBoolean("state20");

        } else {
            String data = getIntent().getStringExtra("size_key");
            data = data.replaceAll("\\s", "");
            size = Integer.parseInt(data);
            playerText = "LP\n " + playerPoints;
            text_p1.setText(playerText);
            DoThis();
        }
        Call();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Play:
                if (stop == 0) {
                    doBindService();
                    Intent music = new Intent();
                    music.setClass(this, MusicService.class);
                    startService(music);
                } else {
                    if (mServ != null) {
                        mServ.startMusic();
                        stop = 0;
                    }
                }
                return true;
            case R.id.Stop:
                int count =0;
                    if (mServ != null) {
                        doBindService();
                        Intent music = new Intent();
                        music.setClass(this, MusicService.class);
                        startService(music);
                        mServ.stopMusic();
                        stop = 1;
                    }
                return true;
            case R.id.Show:
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
                Show(cover12);
                Show(cover13);
                Show(cover14);
                Show(cover15);
                Show(cover16);
                Show(cover17);
                Show(cover18);
                Show(cover19);
                Show(cover20);
                playerPoints = 0;
                playerText = " LP\n " + playerPoints;
                text_p1.setText(playerText);

                return true;
            case R.id.Reset:
                resetVisible();
                reset();
                DoThis();
                Call();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void resetVisible() {
        visible1 = false;
        visible2 = false;
        visible3 = false;
        visible4 = false;
        visible5 = false;
        visible6 = false;
        visible7 = false;
        visible8 = false;
        visible9 = false;
        visible10 = false;
        visible11 = false;
        visible12 = false;
        visible13 = false;
        visible14 = false;
        visible15 = false;
        visible16 = false;
        visible17 = false;
        visible18 = false;
        visible19 = false;
        visible20 = false;
    }

    public void DoThis() {
        shuffleCards(size);
        shuffle(size);
    }

    public void Call() {
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
        setViewV2(cover13, visible13);
        setViewV2(cover14, visible14);
        setViewV2(cover15, visible15);
        setViewV2(cover16, visible16);
        setViewV2(cover17, visible17);
        setViewV2(cover18, visible18);
        setViewV2(cover19, visible19);
        setViewV2(cover20, visible20);

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
        ButtonEvent(cover13);
        ButtonEvent(cover14);
        ButtonEvent(cover15);
        ButtonEvent(cover16);
        ButtonEvent(cover17);
        ButtonEvent(cover18);
        ButtonEvent(cover19);
        ButtonEvent(cover20);
    }

    public void Display(int size) {
        String num = Integer.toString(size);
        UpdateTag(num);
        if (size == 4 || size == 6 || size == 10 || size == 8 || size == 2) {
            cover2.setTag("0");
            cover3.setTag("1");
            if(size >= 2) {
                cover6.setTag("2");
                cover7.setTag("3");
            }
            if (size >= 6) {
                cover10.setTag("4");
                cover11.setTag("5");
            }
            if (size >= 8) {
                cover14.setTag("6");
                cover15.setTag("7");
            }
            if (size >= 10) {
                cover18.setTag("8");
                cover19.setTag("9");
            }

        } else {
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
            cover13.setTag("12");
            cover14.setTag("13");
            cover15.setTag("14");
            cover16.setTag("15");
            cover17.setTag("16");
            cover18.setTag("17");
            cover19.setTag("18");
            cover20.setTag("19");
        }
    }

    private void UpdateTag(String size) {
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
        cover13.setTag(size);
        cover14.setTag(size);
        cover15.setTag(size);
        cover16.setTag(size);
        cover17.setTag(size);
        cover18.setTag(size);
        cover19.setTag(size);
        cover20.setTag(size);
    }

    private void setViewV2(ImageView cover, boolean visible) {
        int theCard = Integer.parseInt((String) cover.getTag());
        if (visible) {
            cover.setVisibility(View.INVISIBLE);
            cover.setEnabled(false);
        } else if (theCard < size) {
            cover.setEnabled(true);
            cover.setVisibility(View.VISIBLE);
        } else {
            cover.setEnabled(false);
            cover.setVisibility(View.INVISIBLE);
        }

    }

    private void ButtonEvent(final ImageView cover) {
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(cover, theCard);
            }
        });
    }

    private void doStuff(ImageView cover, int num) {
        if (copy[num] == 101) {
            cover.setImageResource(image101);
        } else if (copy[num] == 102) {
            cover.setImageResource(image102);
        } else if (copy[num] == 103) {
            cover.setImageResource(image103);
        } else if (copy[num] == 104) {
            cover.setImageResource(image104);
        } else if (copy[num] == 105) {
            cover.setImageResource(image105);
        } else if (copy[num] == 106) {
            cover.setImageResource(image106);
        } else if (copy[num] == 107) {
            cover.setImageResource(image107);
        } else if (copy[num] == 108) {
            cover.setImageResource(image108);
        } else if (copy[num] == 109) {
            cover.setImageResource(image109);
        } else if (copy[num] == 110) {
            cover.setImageResource(image110);
        } else if (copy[num] == 201) {
            cover.setImageResource(image201);
        } else if (copy[num] == 202) {
            cover.setImageResource(image202);
        } else if (copy[num] == 203) {
            cover.setImageResource(image203);
        } else if (copy[num] == 204) {
            cover.setImageResource(image204);
        } else if (copy[num] == 205) {
            cover.setImageResource(image205);
        } else if (copy[num] == 206) {
            cover.setImageResource(image206);
        } else if (copy[num] == 207) {
            cover.setImageResource(image207);
        } else if (copy[num] == 208) {
            cover.setImageResource(image208);
        } else if (copy[num] == 209) {
            cover.setImageResource(image209);
        } else if (copy[num] == 210) {
            cover.setImageResource(image210);
        }
        if (cardNumber == 1) {
            firstCard = copy[num];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = num;
            cover.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = copy[num];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
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

    private void shuffle(int size) {
        int temp, index;
        Random random = new Random();
        for (int i = copy.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = copy[index];
            copy[index] = copy[i];
            copy[i] = temp;
        }
    }

    private void shuffleCards(int size) {
        int temp, index;
        Random random = new Random();
        for (int i = card2.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = card2[index];
            card2[index] = card2[i];
            card2[i] = temp;
        }
        getCard(size);
    }

    private void getCard(int size) {
        int count = 0;
        copy = new int[size];
        for (int i = 0; i < size / 2; i++) {
            copy[i] = card2[count];
            count++;
        }
        count = 0;
        for (int i = size / 2; i < size; i++) {
            copy[i] = copy[count] + 100;
            count++;
        }
        for (int i = 0; i < copy.length; i++) {
            copy[i] = copy[i];
            String t = Integer.toString(copy[i]);
        }
    }

    private void calculate() {
        if (firstCard == secondCard) {
            if (size == 2 || size == 4 || size == 6 || size == 8 || size == 10) {
                stateChangeV2(clickedFirst);
                stateChangeV2(clickedSecond);
            } else {
                stateChange(clickedFirst);
                stateChange(clickedSecond);
            }
            playerPoints += (100 * corectPoint);
            corectPoint++;
            playerText = "LP\n " + playerPoints;
            text_p1.setText(playerText);
            if (missedPoint != 1) {
                missedPoint--;
            }
        } else {
            reset();

            if (playerPoints > 0) {
                playerPoints -= (50 * 2 * missedPoint);
                missedPoint++;
            }
            if (playerPoints < 0) {
                playerPoints = 0;
                if (missedPoint != 1) {
                    missedPoint--;
                }
            }
            playerText = "LP\n " + playerPoints;
            text_p1.setText(playerText);

        }
        Enable(true);
        checkEnd();
    }
    public void scoreSubmit(View view){
        String score= Integer.toString(playerPoints);
        Intent intent = new Intent(getBaseContext(),submissionPage.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }
    private void checkEnd() {
        if (cover1.getVisibility() == View.INVISIBLE &&
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
                cover12.getVisibility() == View.INVISIBLE &&
                cover13.getVisibility() == View.INVISIBLE &&
                cover14.getVisibility() == View.INVISIBLE &&
                cover15.getVisibility() == View.INVISIBLE &&
                cover16.getVisibility() == View.INVISIBLE &&
                cover17.getVisibility() == View.INVISIBLE &&
                cover18.getVisibility() == View.INVISIBLE &&
                cover19.getVisibility() == View.INVISIBLE &&
                cover20.getVisibility() == View.INVISIBLE) {
                rotateAnimation();
                submitbutton.setVisibility(View.VISIBLE);
        }
    }

    private void frontOfCardsResources() {
        image101 = R.drawable.darkmagician;
        image102 = R.drawable.blue_eyes;
        image103 = R.drawable.karibu;
        image104 = R.drawable.rbd;
        image105 = R.drawable.hl;
        image106 = R.drawable.sts;
        image107 = R.drawable.dgl;
        image108 = R.drawable.ss;
        image109 = R.drawable.wwl;
        image110 = R.drawable.yugi;
        image201 = R.drawable.darkmagician;
        image202 = R.drawable.blue_eyes;
        image203 = R.drawable.karibu;
        image204 = R.drawable.rbd;
        image205 = R.drawable.hl;
        image206 = R.drawable.sts;
        image207 = R.drawable.dgl;
        image208 = R.drawable.ss;
        image209 = R.drawable.wwl;
        image210 = R.drawable.yugi;
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
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
        cover13.startAnimation(rotateAnimation);
        cover14.startAnimation(rotateAnimation);
        cover15.startAnimation(rotateAnimation);
        cover16.startAnimation(rotateAnimation);
        cover17.startAnimation(rotateAnimation);
        cover18.startAnimation(rotateAnimation);
        cover19.startAnimation(rotateAnimation);
        cover20.startAnimation(rotateAnimation);
    }

    public void Enable(boolean set) {
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
        cover13.setEnabled(set);
        cover14.setEnabled(set);
        cover15.setEnabled(set);
        cover16.setEnabled(set);
        cover17.setEnabled(set);
        cover18.setEnabled(set);
        cover19.setEnabled(set);
        cover20.setEnabled(set);
    }

    public void reset() {
        cover1.setImageResource(R.drawable.card);
        cover2.setImageResource(R.drawable.card);
        cover3.setImageResource(R.drawable.card);
        cover4.setImageResource(R.drawable.card);
        cover5.setImageResource(R.drawable.card);
        cover6.setImageResource(R.drawable.card);
        cover7.setImageResource(R.drawable.card);
        cover8.setImageResource(R.drawable.card);
        cover9.setImageResource(R.drawable.card);
        cover10.setImageResource(R.drawable.card);
        cover11.setImageResource(R.drawable.card);
        cover12.setImageResource(R.drawable.card);
        cover13.setImageResource(R.drawable.card);
        cover14.setImageResource(R.drawable.card);
        cover15.setImageResource(R.drawable.card);
        cover16.setImageResource(R.drawable.card);
        cover17.setImageResource(R.drawable.card);
        cover18.setImageResource(R.drawable.card);
        cover19.setImageResource(R.drawable.card);
        cover20.setImageResource(R.drawable.card);
    }


    private void stateChangeV2(int check) {
        if (check == 0) {
            cover2.setVisibility(View.INVISIBLE);
            visible2 = true;
        } else if (check == 1) {
            cover3.setVisibility(View.INVISIBLE);
            visible3 = true;
        } else if (check == 2) {
            cover6.setVisibility(View.INVISIBLE);
            visible6 = true;
        } else if (check == 3) {
            cover7.setVisibility(View.INVISIBLE);
            visible7 = true;
        } else if (check == 4) {
            cover10.setVisibility(View.INVISIBLE);
            visible10 = true;
        } else if (check == 5) {
            cover11.setVisibility(View.INVISIBLE);
            visible11 = true;
        } else if (check == 6) {
            cover14.setVisibility(View.INVISIBLE);
            visible14 = true;
        } else if (check == 7) {
            cover15.setVisibility(View.INVISIBLE);
            visible15 = true;
        } else if (check == 8) {
            cover18.setVisibility(View.INVISIBLE);
            visible18 = true;
        } else if (check == 9) {
            cover19.setVisibility(View.INVISIBLE);
            visible19 = true;
        }
    }


    public void stateChange(int check) {
        if (check == 0) {
            cover1.setVisibility(View.INVISIBLE);
            visible1 = true;
        } else if (check == 1) {
            cover2.setVisibility(View.INVISIBLE);
            visible2 = true;
        } else if (check == 2) {
            cover3.setVisibility(View.INVISIBLE);
            visible3 = true;
        } else if (check == 3) {
            cover4.setVisibility(View.INVISIBLE);
            visible4 = true;
        } else if (check == 4) {
            cover5.setVisibility(View.INVISIBLE);
            visible5 = true;
        } else if (check == 5) {
            cover6.setVisibility(View.INVISIBLE);
            visible6 = true;
        } else if (check == 6) {
            cover7.setVisibility(View.INVISIBLE);
            visible7 = true;
        } else if (check == 7) {
            cover8.setVisibility(View.INVISIBLE);
            visible8 = true;
        } else if (check == 8) {
            cover9.setVisibility(View.INVISIBLE);
            visible9 = true;
        } else if (check == 9) {
            cover10.setVisibility(View.INVISIBLE);
            visible10 = true;
        } else if (check == 10) {
            cover11.setVisibility(View.INVISIBLE);
            visible11 = true;
        } else if (check == 11) {
            cover12.setVisibility(View.INVISIBLE);
            visible12 = true;
        } else if (check == 12) {
            cover13.setVisibility(View.INVISIBLE);
            visible13 = true;
        } else if (check == 13) {
            cover14.setVisibility(View.INVISIBLE);
            visible14 = true;
        } else if (check == 14) {
            cover15.setVisibility(View.INVISIBLE);
            visible15 = true;
        } else if (check == 15) {
            cover16.setVisibility(View.INVISIBLE);
            visible16 = true;
        } else if (check == 16) {
            cover17.setVisibility(View.INVISIBLE);
            visible17 = true;
        } else if (check == 17) {
            cover18.setVisibility(View.INVISIBLE);
            visible18 = true;
        } else if (check == 18) {
            cover19.setVisibility(View.INVISIBLE);
            visible19 = true;
        } else if (check == 19) {
            cover20.setVisibility(View.INVISIBLE);
            visible20 = true;

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
        outState.putBoolean("state13", visible13);
        outState.putBoolean("state14", visible14);
        outState.putBoolean("state15", visible15);
        outState.putBoolean("state16", visible16);
        outState.putBoolean("state17", visible17);
        outState.putBoolean("state18", visible18);
        outState.putBoolean("state19", visible19);
        outState.putBoolean("state20", visible20);
        /////////////////////////////////////////////////////////////////////
        outState.putInt("points", playerPoints);
        outState.putIntArray("location1", copy);
        outState.putString("player", playerText);
        outState.putInt("correct" , corectPoint);
        outState.putInt("missed", missedPoint);

    }

    public void Reveal(ImageView cover, int num) {
        if (copy[num] == 101) {
            cover.setImageResource(image101);
        } else if (copy[num] == 102) {
            cover.setImageResource(image102);
        } else if (copy[num] == 103) {
            cover.setImageResource(image103);
        } else if (copy[num] == 104) {
            cover.setImageResource(image104);
        } else if (copy[num] == 105) {
            cover.setImageResource(image105);
        } else if (copy[num] == 106) {
            cover.setImageResource(image106);
        } else if (copy[num] == 107) {
            cover.setImageResource(image107);
        } else if (copy[num] == 108) {
            cover.setImageResource(image108);
        } else if (copy[num] == 109) {
            cover.setImageResource(image109);
        } else if (copy[num] == 110) {
            cover.setImageResource(image110);
        } else if (copy[num] == 201) {
            cover.setImageResource(image201);
        } else if (copy[num] == 202) {
            cover.setImageResource(image202);
        } else if (copy[num] == 203) {
            cover.setImageResource(image203);
        } else if (copy[num] == 204) {
            cover.setImageResource(image204);
        } else if (copy[num] == 205) {
            cover.setImageResource(image205);
        } else if (copy[num] == 206) {
            cover.setImageResource(image206);
        } else if (copy[num] == 207) {
            cover.setImageResource(image207);
        } else if (copy[num] == 208) {
            cover.setImageResource(image208);
        } else if (copy[num] == 209) {
            cover.setImageResource(image209);
        } else if (copy[num] == 210) {
            cover.setImageResource(image210);
        }
    }

    public void Show(ImageView cover) {
        int theCard = Integer.parseInt((String) cover.getTag());
        if (theCard < size) {
            Reveal(cover, theCard);
        }
    }


    ////////////////////////////////////////////////////////////////////////

    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        stopService(music);

    }

    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }
}
