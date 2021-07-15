package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class imgChoice extends AppCompatActivity {

    private RadioGroup RadioGruop;
    private Button btn_logo_choice;
    private int[] radio_id = new int[]{R.id.rb_1,R.id.rb_2,R.id.rb_3,R.id.rb_4,R.id.rb_5,R.id.rb_6,R.id.rb_7,R.id.rb_8,R.id.rb_9,
            R.id.rb_10,R.id.rb_11,R.id.rb_12,R.id.rb_13,R.id.rb_14,R.id.rb_15,R.id.rb_16,R.id.rb_17,R.id.rb_18,R.id.rb_19,R.id.rb_20};
    private RadioButton[] radio = new RadioButton[radio_id.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_choice);

        RadioGruop=findViewById(R.id.RadioGroup);
        btn_logo_choice=findViewById(R.id.btn_logo_choice);

        for(int i=0; i<radio_id.length; i++){
            radio[i]=findViewById(radio_id[i]);
        }


        btn_logo_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = null;

                for(int i=0; i<radio.length; i++){
                    if(radio[i].isChecked()){
                        a=i+1+"";
                        Log.v("aaa0",i+"");
                        Intent intent = new Intent(getApplicationContext(),CreateTeam.class);
                        intent.putExtra("img",a);
                        Log.v("bbbb",a);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }



            }
        });


    }
}