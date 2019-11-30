package com.example.clover;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
