 package com.example.clover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 public class MainActivity extends AppCompatActivity {
    private Button button4x4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button4x4 = (Button) findViewById(R.id.button_4x4 );
        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.edittext);
                String value = editText.getText().toString();
                if(value.contains("12")) {
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                }
            }
        });
    }
}
