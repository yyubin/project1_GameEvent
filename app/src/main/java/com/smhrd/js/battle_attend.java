package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class battle_attend extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_battle_name, tv_battle_date, tv_battle_top, tv_battle_jug, tv_battle_mid, tv_battle_adc, tv_battle_sup;
    private Button btn_battle_cancel,btn_battle_submit,btn_regulation_go;
    ArrayList<String> member = new ArrayList<>();

    private RequestQueue queue;
    private StringRequest stringRequest;

    private BattleAdapter adapter;
    private ArrayList<BattleDTO> list = new ArrayList<>();

    String team_name;

    public void onClick(View v) {
        String[] mem = new String[member.size()];
        for(int i=0; i<member.size(); i++){
            mem[i]=member.get(i);
        }

        Log.v("dbqls","성공");
        Log.v("member",member+"");
        int[] selectedMem = new int[1];
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
                        Log.v("whick", which+"");

                        if (v.getId() == R.id.tv_battle_top){
                            tv_battle_top.setText(mem[selectedMem[0]]);
                        }else if(v.getId() == R.id.tv_battle_jug){
                            tv_battle_jug.setText(mem[selectedMem[0]]);
                        }else if(v.getId()== R.id.tv_battle_mid){
                            tv_battle_mid.setText(mem[selectedMem[0]]);
                        }else if(v.getId()== R.id.tv_battle_adc){
                            tv_battle_adc.setText(mem[selectedMem[0]]);
                        }else{
                            tv_battle_sup.setText(mem[selectedMem[0]]);
                        }


                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/team_name_temp";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("resultValue", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("member_lol_name");
                    for(int i=0; i<jsonArray.length(); i++){
                        member.add(jsonArray.getString(i));

                        Log.v("mem",member+"");
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Server 통신시 Error 발생하면 오는 곳
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Server로 데이터 보낼 시 넣어주는 곳
                Map<String, String> parmas = new HashMap<String, String>();

                String member = PreferenceManager.getString(getApplicationContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    parmas.put("team_name",jsonObject.getString("team_name"));
                    team_name=jsonObject.getString("team_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return parmas;

            }
        };

        queue.add(stringRequest);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_attend);

        sendRequest();

        btn_regulation_go = findViewById(R.id.btn_regulation_go);

        btn_regulation_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Regulation.class);
                startActivity(intent);
            }
        });

        btn_battle_cancel = findViewById(R.id.btn_battle_cancel);
        btn_battle_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

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

        btn_battle_submit=findViewById(R.id.btn_battle_submit);
        btn_battle_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter=new BattleAdapter();
                String top = tv_battle_top.getText().toString();
                String jug = tv_battle_jug.getText().toString();
                String mid = tv_battle_mid.getText().toString();
                String adc = tv_battle_adc.getText().toString();
                String sup = tv_battle_sup.getText().toString();

                BattleDTO dto = new BattleDTO(team_name,top,jug,mid,adc,sup);

//                adapter.addItem(team_name,top,jug,mid,adc,sup);
                Gson gson = new Gson();
                String battle_team = gson.toJson(dto);

                try {
                    JSONObject jsonObject = new JSONObject(battle_team);

                    Log.v("json",jsonObject.getString("team_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.addItem(team_name);





            }
        });



    }
}