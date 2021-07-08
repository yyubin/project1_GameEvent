package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class join extends AppCompatActivity {
    EditText edt_join_id, edt_join_pw, edt_join_name, edt_join_phone, edt_join_email, edt_join_nickname;
    Button btn_join_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

    }
}