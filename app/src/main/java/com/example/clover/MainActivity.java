 package com.example.clover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 public class MainActivity extends AppCompatActivity {
    private Button button4x4;
    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySong = MediaPlayer.create(MainActivity.this, R.raw.song);
    }


    public void playIt(View v){
        mySong.start();
    }
    public void onPause(){
        super.onPause();
        mySong.release();
    }

     public void NextScreen(View view) {
         EditText editText = (EditText) findViewById(R.id.edittext);
         String value = editText.getText().toString();
         value = value.replaceAll("\\s", "");
         int size = Integer.parseInt(value);
         if(size % 2 == 0 && size >= 4 && size <= 20) {
             Intent intent = new Intent(MainActivity.this, Game.class);
             intent.putExtra("size_key", value);
             startActivity(intent);
         }else{

         }
     }
 }
