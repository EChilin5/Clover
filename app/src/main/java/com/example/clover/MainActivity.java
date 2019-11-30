 package com.example.clover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
    private Button button4x4;
    EditText userName,userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName= findViewById(R.id.userName);
        userPassword=findViewById(R.id.userPassword);

    }

     public void OnLogin(View view){
         String username= userName.getText().toString();
         String password=userPassword.getText().toString();
         ((userWorker)this.getApplication()).setUsername(username);
         ((userWorker)this.getApplication()).setPassword(password);
         String type="login";
         loginWorker loginWorker= new loginWorker(this);
         loginWorker.execute(type,username,password);
     }
     public void highscoresView(View view){
        Intent intent= new Intent(MainActivity.this, highscorePage.class);
        startActivity(intent);
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
