 package com.example.clover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 public class MainActivity extends AppCompatActivity {
    private Button button4x4;
    MediaPlayer mySong;
     HomeListener mHomeWatcher;
     int stop = 0;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySong = MediaPlayer.create(MainActivity.this, R.raw.song);

         MenuItem secondItem = (MenuItem) findViewById(R.id.Show);
//        secondItem.setVisible(false);

         MenuItem thirdItem = (MenuItem) findViewById(R.id.Reset);
 //        thirdItem.setVisible(false);

         mHomeWatcher = new HomeListener(this);
         mHomeWatcher.setOnHomePressedListener(new HomeListener.OnHomePressedListener() {
             @Override
             public void onHomePressed() {
                 if (mServ != null) {
                     mServ.pauseMusic();
                 }
             }
             @Override
             public void onHomeLongPressed() {
                 if (mServ != null) {
                     mServ.pauseMusic();
                 }
             }
         });
         mHomeWatcher.startWatch();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return  true;
    }

public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Play:
                if(stop == 0) {
                    doBindService();
                    Intent music = new Intent();
                    music.setClass(this, MusicService.class);
                    startService(music);
                }else{
                    if (mServ != null) {
                        mServ.startMusic();
                        stop = 0;
                    }
                }
                return true;
            case R.id.Stop:
                if (mServ != null) {
                    mServ.stopMusic();
                    stop=1;
                }
                return true;

        }

         return super.onOptionsItemSelected(item);
}


    public void playIt(View v){
        if(stop == 0) {
            doBindService();
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            startService(music);
        }else{
            if (mServ != null) {
                mServ.startMusic();
                stop = 0;
            }
        }
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

     public void end(View view) {
         if (mServ != null) {
             mServ.stopMusic();
             stop=1;
         }

     }


     private boolean mIsBound = false;
     private MusicService mServ;
     private ServiceConnection Scon =new ServiceConnection(){

         public void onServiceConnected(ComponentName name, IBinder
                 binder) {
             mServ = ((MusicService.ServiceBinder)binder).getService();
         }

         public void onServiceDisconnected(ComponentName name) {
             mServ = null;
         }
     };

     void doBindService(){
         bindService(new Intent(this,MusicService.class),
                 Scon, Context.BIND_AUTO_CREATE);
         mIsBound = true;
     }

     void doUnbindService()
     {
         if(mIsBound)
         {
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
         music.setClass(this,MusicService.class);
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
