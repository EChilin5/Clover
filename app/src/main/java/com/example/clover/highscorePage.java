package com.example.clover;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class highscorePage extends AppCompatActivity {
    TextView scoreOne,scoreTwo,scoreThree,scoreFour,scoreFive,playerOne,playerTwo,playerThree,playerFour,playerFive;

    MediaPlayer mySong;
    HomeListener mHomeWatcher;
    private boolean mIsBound = false;
    private MusicService mServ;

    int stop = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     ///   getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.highscores);
        scoreOne = findViewById(R.id.score1);
        scoreTwo = findViewById(R.id.score2);
        scoreThree = findViewById(R.id.score3);
        scoreFour = findViewById(R.id.score4);
        scoreFive = findViewById(R.id.score5);
        playerOne = findViewById(R.id.player1);
        playerTwo = findViewById(R.id.player2);
        playerThree = findViewById(R.id.player3);
        playerFour = findViewById(R.id.player4);
        playerFive = findViewById(R.id.player5);
        Connection con = new Connection();
        con.execute();

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

    class Connection extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String JSONString="";
            String scores_url = "http://teamclover.gearhostpreview.com/highscores.php";
            URL url = null;
            try
            {
                url = new URL(scores_url);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            try
            {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                while((inputLine = in.readLine()) != null)
                {
                    JSONString+=inputLine;
                }
                in.close();
                httpURLConnection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return JSONString;
        }
        protected void onPostExecute(String JSONString)
        {
            String names[]= new String [5];
            String highscores[]=new String[5];
            try
            {
                JSONObject jsonObject=new JSONObject(JSONString);
                JSONArray test = jsonObject.getJSONArray("users");
                for(int i=0; i<test.length();i++)
                {
                    JSONObject scores =test.getJSONObject(i);
                    names[i]=scores.getString("username");
                    highscores[i]=scores.getString("score");
                }
                playerOne.setText(names[0]);
                playerTwo.setText(names[1]);
                playerThree.setText(names[2]);
                playerFour.setText(names[3]);
                playerFive.setText(names[4]);
                scoreOne.setText(highscores[0]);
                scoreTwo.setText(highscores[1]);
                scoreThree.setText(highscores[2]);
                scoreFour.setText(highscores[3]);
                scoreFive.setText(highscores[4]);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        }
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
