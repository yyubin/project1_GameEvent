package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Table extends AppCompatActivity {

    private TextView tv_board, tv_board_title,edt_table_title,edt_table_text;
    final int[] selectedBoard = {0};
    private Button btn_submit, button_cancle;

    private RequestQueue queue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tv_board = findViewById(R.id.tv_board);
        tv_board_title = findViewById(R.id.tv_board_title);

        edt_table_text=findViewById(R.id.edt_table_text);
        edt_table_title=findViewById(R.id.edt_table_title);
        btn_submit=findViewById(R.id.btn_submit);
        button_cancle=findViewById(R.id.button_cancle);

        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        tv_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] boards = new String[]{"???????????????","Q&A?????????","????????? ?????? ?????? ?????????","?????? ?????????"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Table.this);
                dialog .setTitle("???????????? ???????????????")
                        .setSingleChoiceItems(boards, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedBoard[0]=which;
                            }
                        })
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(selectedBoard[0]==0){
                                    tv_board_title.setText("???????????????");
                                }else if(selectedBoard[0]==1){
                                    tv_board_title.setText("Q&A?????????");
                                }else if(selectedBoard[0]==2){
                                    tv_board_title.setText("????????? ?????? ?????? ?????????");
                                }else{
                                    tv_board_title.setText("?????? ?????????");
                                }
                                Toast.makeText(Table.this,boards[selectedBoard[0]],Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Table.this,"?????? ????????? ???????????????",Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();


            }
        });


    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url= "http://121.147.52.82:3100/";
        if(selectedBoard[0]==0){ //??????????????? 1
            url += "free_board_write";
        }else if(selectedBoard[0]==1){ // QA????????? 3
            url += "qa_board_write";
        }else if(selectedBoard[0]==2){ // ????????? ???????????? 4
            url += "free_board_write";
        }else{ //?????? ????????? 2
            url += "recruit_board_write";
        }
        Log.v("url",url);


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server????????? ???????????? ????????? ???

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Server ????????? Error ???????????? ?????? ???
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Server??? ????????? ?????? ??? ???????????? ???
                Map<String, String> parmas = new HashMap<String, String>();

                String login = PreferenceManager.getString(getApplicationContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(login);
                    String lol_name=jsonObject.getString("lol_name");
                    parmas.put("member_lol_name",lol_name);
                    Log.v("lolname",lol_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                parmas.put("board_text", edt_table_text.getText().toString());
                parmas.put("board_title", edt_table_title.getText().toString());



                return parmas;

            }
        };

        queue.add(stringRequest);


    }
}