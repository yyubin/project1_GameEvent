package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class joinsuccess extends AppCompatActivity {

    private Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinsuccess);

        btn_main = findViewById(R.id. btn_main);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), join.class);
                startActivity(intent);
            }
        });
    }
}