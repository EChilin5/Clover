package com.example.clover;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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


public class highscoreWorker extends AsyncTask<String,Void,String> {

    Context context;
    highscoreWorker(Context ctx) {context=ctx;}
    @Override
    protected String  doInBackground(String... strings) {
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
                String username=scores.getString("username");
                String score=scores.getString("score");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }





}
