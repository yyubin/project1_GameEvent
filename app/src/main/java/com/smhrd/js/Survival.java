package com.smhrd.js;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Survival extends AppCompatActivity {

    private TextView tv_team;

    int arr[] = new int[]{R.id.tv_sv_1, R.id.tv_sv_2, R.id.tv_sv_3, R.id.tv_sv_4, R.id.tv_sv_5,
            R.id.tv_sv_6, R.id.tv_sv_7, R.id.tv_sv_8, R.id.tv_sv_7, R.id.tv_sv_8, R.id.tv_sv_9,
            R.id.tv_sv_10, R.id.tv_sv_11};
    TextView arr1[] = new TextView[arr.length];

    private TextView tv_time_1, tv_time_2, tv_time_3, tv_time_4;
    private TextView tv_date_1, tv_date_2, tv_date_3, tv_date_4;
    private Button btn_a, btn_b, btn_c, btn_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival);

        tv_team = findViewById(R.id.tv_team);

        for(int i = 0; i < arr.length; i++){
            arr1[i]=findViewById(arr[i]);
        }

        tv_time_1 = findViewById(R.id.tv_time_1);
        tv_time_2 = findViewById(R.id.tv_time_1);
        tv_time_3 = findViewById(R.id.tv_time_2);
        tv_time_4 = findViewById(R.id.tv_time_3);

        tv_date_1 = findViewById(R.id.tv_date_1);
        tv_date_2 = findViewById(R.id.tv_date_1);
        tv_date_3 = findViewById(R.id.tv_date_2);
        tv_date_4 = findViewById(R.id.tv_date_3);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        btn_c = findViewById(R.id.btn_c);
        btn_d = findViewById(R.id.btn_d);

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}