package com.example.clover;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
public class submissionPage extends AppCompatActivity {
    EditText playerUsername;


    String playerScore;
    AlertDialog alertDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoresubmitpage);
        Intent intent = getIntent();
        playerScore = intent.getStringExtra("SCORE");
        int userScore = Integer.parseInt(playerScore);
        TextView playerScoreDisplay;
        playerScoreDisplay = findViewById(R.id.userScore);
        playerScoreDisplay.setText(playerScore);
        playerUsername = findViewById(R.id.userName);


    }

    public void SubmittoDatabase(View view) {
        String username = playerUsername.getText().toString();
        String score = playerScore;
        String type = "submit";
        submissionPage.Connection con = new Connection();
        con.execute(type, username, score);
    }
    public void mainMenu(View view) {
    Intent intent = new Intent(getBaseContext(),MainActivity.class);
    startActivity(intent);
    finish();
    }
    class Connection extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[1];
            String score = params[2];
            String scores_url = "http://teamclover.gearhostpreview.com/scoreSubmit.php";
            try {
                URL url = new URL(scores_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(score, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPreExecute() {
            alertDialog= new AlertDialog.Builder(submissionPage.this).create();
            alertDialog.setTitle("Submission Status");
        }

        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            alertDialog.show();

        }

    }
}
