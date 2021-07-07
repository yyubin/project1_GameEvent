package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class battle_attend extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_battle_name, tv_battle_date, tv_battle_top, tv_battle_jug, tv_battle_mid, tv_battle_adc, tv_battle_sup;
    private Button btn;

    public void onClick(View v) {
        Log.v("result","성공");
        String[] mem = new String[]{"a","b","c","d","e"};
        int[] selectedMem = new int[0];
        AlertDialog.Builder dialog = new AlertDialog.Builder(battle_attend.this);
        dialog .setTitle("해당하는 팀원을 선택해주세요")
                .setSingleChoiceItems(mem, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedMem[0]=which;
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (v.getId() == R.id.tv_battle_top){
                            tv_battle_top.setText(mem[which]);
                        }else if(v.getId() == R.id.tv_battle_jug){
                            tv_battle_jug.setText(mem[which]);
                        }else if(v.getId()==R.id.tv_battle_mid){
                            tv_battle_mid.setText(mem[which]);
                        }else if(v.getId()==R.id.tv_battle_adc){
                            tv_battle_adc.setText(mem[which]);
                        }else{
                            tv_battle_sup.setText(mem[which]);
                        }

                        Toast.makeText(battle_attend.this,mem[selectedMem[0]],Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_attend);

        tv_battle_name = findViewById(R.id.tv_battle_name);
        tv_battle_date = findViewById(R.id.tv_battle_date);
        tv_battle_top = findViewById(R.id.tv_battle_top);
        tv_battle_jug = findViewById(R.id.tv_battle_jug);
        tv_battle_mid = findViewById(R.id.tv_battle_mid);
        tv_battle_adc = findViewById(R.id.tv_battle_adc);
        tv_battle_sup = findViewById(R.id.tv_battle_sup);




        tv_battle_top.setOnClickListener(this);
        tv_battle_jug.setOnClickListener(this);
        tv_battle_mid.setOnClickListener(this);
        tv_battle_adc.setOnClickListener(this);
        tv_battle_sup.setOnClickListener(this);


    }
}