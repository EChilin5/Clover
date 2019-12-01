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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button4x4;
    MediaPlayer mySong;
    HomeListener mHomeWatcher;
    int stop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
        stop = 1;
        SeekBar seekBar = findViewById(R.id.seekBar);
        final ImageView difficultyDisplay = findViewById(R.id.difficultyDisplay);
        final TextView difficultyDisplayText = findViewById(R.id.difficultyDisplayText);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                String text = Integer.toString(i);
                difficultyDisplayText.setText(text);
                if (progress == 1) {
                    difficultyDisplay.setImageResource(R.drawable.level1);
                } else if (progress == 2) {
                    difficultyDisplay.setImageResource(R.drawable.level2);
                } else if (progress == 3) {
                    difficultyDisplay.setImageResource(R.drawable.level3);
                } else if (progress == 4) {
                    difficultyDisplay.setImageResource(R.drawable.level4);
                } else if (progress == 5) {
                    difficultyDisplay.setImageResource(R.drawable.level5);
                } else if (progress == 6) {
                    difficultyDisplay.setImageResource(R.drawable.level6);
                } else if (progress == 7) {
                    difficultyDisplay.setImageResource(R.drawable.level7);
                } else if (progress == 8) {
                    difficultyDisplay.setImageResource(R.drawable.level8);
                } else if (progress == 9) {
                    difficultyDisplay.setImageResource(R.drawable.level9);
                } else if (progress == 10) {
                    difficultyDisplay.setImageResource(R.drawable.level10);
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
      //  mySong = MediaPlayer.create(MainActivity.this, R.raw.song);

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
                if (mServ != null) {
                    mServ.stopMusic();
                    stop = 1;
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void NextScreen(View view) {
        TextView difficultyDisplayText = findViewById(R.id.difficultyDisplayText);
        int size;
        String value = difficultyDisplayText.getText().toString();
        Log.d("MyApp","I am here");
        if(value != "" && value != " ") {
            Toast.makeText(MainActivity.this, value,
                    Toast.LENGTH_LONG).show();
            try {
                size = Integer.parseInt(value) * 2;
            }catch (Exception e){
                size = 2;
            }
        }else{
            size = 2;
        }
        value = Integer.toString(size);
        Toast.makeText(MainActivity.this, value,
                Toast.LENGTH_LONG).show();

        if (size % 2 == 0 && size <= 20) {
            Intent intent = new Intent(MainActivity.this, Game.class);
            intent.putExtra("size_key", value);
            startActivity(intent);
        }else {

        }
    }

    public void highscoresView(View view) {
        Intent intent = new Intent(MainActivity.this, highscorePage.class);
        startActivity(intent);
    }

    public void end(View view) {
        if (mServ != null) {
            mServ.stopMusic();
            stop = 1;
        }

    }


    private boolean mIsBound = false;
    private MusicService mServ;
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
